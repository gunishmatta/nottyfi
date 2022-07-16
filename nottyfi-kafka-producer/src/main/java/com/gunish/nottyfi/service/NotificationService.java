package com.gunish.nottyfi.service;

import com.gunish.nottyfi.model.Notification;

public interface NotificationService {

    /**
     * Send Notification
     * @param notification model
     */
    void send(Notification notification);
}
