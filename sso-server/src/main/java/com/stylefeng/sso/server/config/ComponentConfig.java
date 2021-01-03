package com.stylefeng.sso.server.config;

import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.guns.sys.modular.system.service.DictTypeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({
        "cn.stylefeng.guns.sys.core",
        "cn.stylefeng.guns.sys.modular.system.service.impl"
})
@Import({DictService.class, DictTypeService.class})
public class ComponentConfig {
}
