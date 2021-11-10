package ru.itis.Makhsotova.Enclave.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private User user;
    private Hotel hotel;
    private Date checkInDate;
    private Date checkOutDate;
    private String reservationStatus;
}
