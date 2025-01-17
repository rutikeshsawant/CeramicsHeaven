package com.ceramicsheaven.services;

import com.ceramicsheaven.model.Address;

import java.time.LocalDateTime;
import java.util.Map;

public interface EmailService {
    public  void registrationEmail(String email);

    public String capitalizeFirstLetter(String str);

    public void order(String fullName, String email, Long orderId,LocalDateTime orderDate, LocalDateTime deliveryDate, Integer totalPrice, Integer discount, String paymentMethod,String paymentStatus, Address address);

}
