package ru.itis.Makhsotova.Enclave.models;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Integer id;
    private String name;
    private String locality;
    private String street;
    private String buildingNumber;
    private String imagePath;
    private Integer numberRooms;
    private Integer numberFreeRooms;

}
