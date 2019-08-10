package com.fengjx.hello.springcloud.auth.config;

import com.fengjx.hello.springcloud.user.api.UserApi;

/**
 * @author fengjianxin
 */
public class ApiConfig {


    public UserApi userApi(){
        return new UserApi();
    }


}
