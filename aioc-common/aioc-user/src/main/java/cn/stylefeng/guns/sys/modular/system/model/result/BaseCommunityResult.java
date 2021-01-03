package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author ln
 * @since 2020-01-08
 */
@Data
public class BaseCommunityResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String code;

    private String name;

    private String parentCode;

    /**
     * 类型：1：河西区 2：片区 3：街道 4：社区
     */
    private Integer type;

    /**
     * 坐标
     */
    private String coord;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

}
