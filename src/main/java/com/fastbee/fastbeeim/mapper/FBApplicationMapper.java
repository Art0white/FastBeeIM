package com.fastbee.fastbeeim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastbee.fastbeeim.pojo.FBApplication;
import org.apache.ibatis.annotations.Select;

/**
 * FBApplicationMapper
 * @author Lovsog
 */

public interface FBApplicationMapper extends BaseMapper<FBApplication> {
    @Select("SELECT\n" +
            "    AUTO_INCREMENT\n" +
            "FROM\n" +
            "    INFORMATION_SCHEMA.TABLES\n" +
            "WHERE\n" +
            "    TABLE_NAME = 'f_b_application'")
    Integer getNextAutoIncrementOfApp();
}
