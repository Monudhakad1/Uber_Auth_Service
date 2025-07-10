package com.uberauthservices.uber_auth_services.Repositories;

import com.uberauthservices.uber_auth_services.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {



}
