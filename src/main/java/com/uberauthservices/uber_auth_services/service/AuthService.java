package com.uberauthservices.uber_auth_services.service;

import com.uberauthservices.uber_auth_services.Dto.PassengerDto;
import com.uberauthservices.uber_auth_services.Dto.PassengerSignUpRequestDto;
import com.uberauthservices.uber_auth_services.Repositories.PassengerRepository;
import com.uberauthservices.uber_auth_services.models.Passenger;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Builder
@Service //  Mark as service so Spring can detect it
public class AuthService {
    // Auth Service Impl of Business Logic Layer for signup

    private final PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder x) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = x;
    }

    public PassengerDto signUpPassenger(PassengerSignUpRequestDto passengerSignUpRequestDto) {
        // 1️ Create new Passenger entity
        Passenger passenger = Passenger.builder()
                .email(passengerSignUpRequestDto.getEmail())
                .name(passengerSignUpRequestDto.getName())
                .password(bCryptPasswordEncoder.encode( passengerSignUpRequestDto.getPassword())) // TODO:: make password encrypted
                .phoneNumber(passengerSignUpRequestDto.getPhoneNumber())
                .build();

        // 2️ Save to DB
        Passenger newPassenger = passengerRepository.save(passenger);

        // 3️ Convert to DTO and return
        return PassengerDto.from(newPassenger);
    }
}
