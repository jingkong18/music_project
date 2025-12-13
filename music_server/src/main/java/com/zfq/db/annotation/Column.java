package com.zfq.db.annotation;

import org.apache.ibatis.type.JdbcType;

import java.lang.annotation.*;

/**
 * @ClassName Column
 * @Author zfq
 * @Date 2025/12/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Column {
    /**
     * 列名
     * @return 列名
     */
    String name() default "";

    /**
     * 列类型
     * @return 列类型
     */
    JdbcType jdbcType() default JdbcType.NULL;

}
