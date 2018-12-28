package com.xds.userservice.service;

public interface UserService {
    long saveEvent(Long orderId);

    void newUserSaveEventHandle();
}
