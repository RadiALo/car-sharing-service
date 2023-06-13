package com.carsharing.service;

import com.carsharing.model.Rental;

public interface NotificationService {
    void sendNotificationAboutPossibleRental(Rental rental);

    void sendNotificationAboutImpossibleRental(Rental rental);

    void sentNotificationAboutFailedDeadLine();

    void sentNotificationAboutReturnedCar(Rental rental);

    void sentNotificationAboutSuccessfulPayment(Rental rental);
}
