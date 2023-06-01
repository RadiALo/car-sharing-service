package com.carsharing.service;

import com.carsharing.model.Rental;

public interface NotificationService {
    void sendCreationNotification(Rental rental);

    void sendReturnConformation(Rental rental);
}
