package com.uberauthservices.uber_auth_services.Dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PassengerSignUpRequestDto {
    // json -> Java object

    private String email;

    private String password;

    private String phoneNumber;

    private String name;

}
