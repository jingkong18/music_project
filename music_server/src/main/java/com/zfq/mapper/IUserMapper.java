package com.zfq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfq.model.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName IUserMapper
 * @Author zfq
 * @Date 2025/12/7
 */
@Mapper
public interface IUserMapper extends BaseMapper<User> {
}
