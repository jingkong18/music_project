package com.zfq.db.annotation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.zfq.db.type.KeyType;

import java.lang.annotation.*;

/**
 * @ClassName Id
 * @Author zfq
 * @Date 2025/12/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Id {
    /**
     * 生成主键前缀
     * @return 前缀字符串
     */
    String prefix() default "";

    IdType type() default IdType.AUTO;
}