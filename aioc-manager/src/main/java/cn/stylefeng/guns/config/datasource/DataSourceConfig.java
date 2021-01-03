/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.config.datasource;

import cn.stylefeng.roses.core.config.properties.DruidProperties;
import cn.stylefeng.roses.core.mutidatasource.aop.MultiSourceExAop;
import com.atomikos.icatch.provider.ConfigProperties;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Properties;

import static cn.stylefeng.guns.base.db.context.DataSourceContext.MASTER_DATASOURCE_NAME;
import static com.atomikos.icatch.provider.ConfigProperties.LOG_BASE_DIR_PROPERTY_NAME;
import static com.atomikos.icatch.provider.ConfigProperties.LOG_BASE_NAME_PROPERTY_NAME;

/**
 * 多数据源配置<br/>
 * <p>
 * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
public class DataSourceConfig {

    /**
     * 默认主数据源配置
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }
    /**
     * 默认主数据源配置
     */
    @Bean
    @Primary
    public ConfigProperties configProperties() {
        Properties properties =new Properties();
//        public static final String LOG_BASE_DIR_PROPERTY_NAME = "com.atomikos.icatch.log_base_dir";
//        public static final String LOG_BASE_NAME_PROPERTY_NAME = "com.atomikos.icatch.log_base_name";
        properties.setProperty(LOG_BASE_DIR_PROPERTY_NAME,"../manager-log");
        properties.setProperty(LOG_BASE_NAME_PROPERTY_NAME,"manager.log");
        properties.setProperty("com.atomikos.icatch.console_file_name", "manager.out");
//        properties.setProperty("com.atomikos.icatch.log_base_name","rmlog.log");
        return new ConfigProperties(properties);
    }

    /**
     * 主数据源实例
     */
    @Primary
    @Bean
    public DataSource dataSourcePrimary(@Qualifier("druidProperties") DruidProperties druidProperties) {

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        atomikosDataSourceBean.setUniqueResourceName(MASTER_DATASOURCE_NAME);
        atomikosDataSourceBean.setMaxPoolSize(100);
        atomikosDataSourceBean.setBorrowConnectionTimeout(60);

        Properties properties = druidProperties.createProperties();

        //解决oracle数据库包connection holder is null
        if (druidProperties.getUrl().contains("oracle")) {
            properties.setProperty("removeAbandoned", "true");
            properties.setProperty("removeAbandonedTimeoutMillis", "10000");
            properties.setProperty("poolPreparedStatements", "false");
        }
//        public static final String LOG_BASE_DIR_PROPERTY_NAME = "com.atomikos.icatch.log_base_dir";
//        public static final String LOG_BASE_NAME_PROPERTY_NAME = "com.atomikos.icatch.log_base_name";
//        properties.setProperty(LOG_BASE_DIR_PROPERTY_NAME,"");
      //  properties.setProperty(LOG_BASE_NAME_PROPERTY_NAME,"manager");
        atomikosDataSourceBean.setXaProperties(properties);

        return atomikosDataSourceBean;
    }

    /**
     * 多数据源切换的aop
     */
    @Bean
    public MultiSourceExAop multiSourceExAop() {
        return new MultiSourceExAop();
    }

}