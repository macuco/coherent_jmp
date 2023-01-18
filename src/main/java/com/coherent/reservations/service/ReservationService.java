package com.coherent.reservations.service;

import com.coherent.reservations.domain.Reservation;
import com.coherent.reservations.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    Collection<Reservation> readAllReservations();

    void updateReservationById(Integer id, Reservation reservation);

    Reservation findById(Integer id);

}
