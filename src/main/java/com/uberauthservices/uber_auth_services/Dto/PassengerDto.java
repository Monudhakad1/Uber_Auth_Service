package com.uberauthservices.uber_auth_services.Dto;

import com.uberauthservices.uber_auth_services.models.Passenger;
import lombok.*;

import java.util.Date;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Data
@Builder
public class PassengerDto {

    private Long Id;

    private String name;

    private String email;

    private String password; // Encrypted password

    private String phoneNumber;

    private Date createdAt;

    private Long id;

    public static PassengerDto from(Passenger p) {
        PassengerDto result = PassengerDto.builder()
                .id(p.getId())
                .createdAt(p.getCreatedAt())
                .email(p.getEmail())
                .password(p.getPassword())
                .phoneNumber(p.getPhoneNumber())
                .name(p.getName())
                .build();
        return result;
    }
}
