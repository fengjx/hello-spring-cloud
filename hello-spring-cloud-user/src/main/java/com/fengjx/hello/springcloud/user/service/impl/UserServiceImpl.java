package com.fengjx.hello.springcloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengjx.hello.springcloud.user.entity.User;
import com.fengjx.hello.springcloud.user.mapper.UserMapper;
import com.fengjx.hello.springcloud.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengjianxin
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
