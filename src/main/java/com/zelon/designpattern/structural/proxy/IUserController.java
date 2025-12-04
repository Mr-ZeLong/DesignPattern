package com.zelon.designpattern.structural.proxy;

public interface IUserController {
    UserVo login(String telephone, String password);
    UserVo register(String telephone, String password);
}
