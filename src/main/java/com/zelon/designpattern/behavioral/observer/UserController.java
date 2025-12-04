package com.zelon.designpattern.behavioral.observer;

import com.zelon.designpattern.structural.proxy.UserVo;

public class UserController {
    private UserService userService;
    private PromotionService promotionService;
    private NotificationService notificationService;

    public void createUser(UserVo user) {
        Long userId = userService.createUser(user);
        promotionService.issueNewUserExperienceCash(userId);
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}
class UserService {
    public Long createUser(UserVo user) {
        //...
        return 0L;
    }
}
class PromotionService {
    public void issueNewUserExperienceCash(long userId) {
        //...
    }
}
class NotificationService {
    public void sendInboxMessage(long userId, String message) {
        //...
    }
}