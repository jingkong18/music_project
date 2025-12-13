package com.zfq.db.type;

/**
 * @ClassName KeyType
 * @Author zfq
 * @Date 2025/12/14
 */
public enum KeyType {
    /**
     * 不生成类型（由程序自行设置主键）
     */
    NULL,
    /**
     * 自动增长
     */
    AUTOINCREMENT,
    /**
     * 唯一UUID
     */
    STR_UUID,
    /**
     * 短日期字符
     */
    STR_DATE
    ,
    /**
     * 长时间字符
     */
    STR_LONG_DATE
}
