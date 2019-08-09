package com.fengjx.hello.springcloud.user.web;


import com.fengjx.hello.springcloud.commons.web.BaseController;
import com.fengjx.hello.springcloud.user.entity.User;
import com.fengjx.hello.springcloud.user.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fengjianxin
 */
@RestController
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping("findById")
    public User findById(Long id) {
        return userService.getById(id);
    }


}
