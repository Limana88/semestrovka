package ru.itis.Makhsotova.Enclave.models;
import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    protected String firstName;
    protected String secondName;
    protected String email;
    protected String password;
    protected Integer id;
    protected UUID uuid;
    private List<Integer> reservation;

}
