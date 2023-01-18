package com.coherent.test.service;

import com.coherent.test.domain.Reservation;
import com.coherent.test.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(Reservation reservation) throws Exception {
        if ( null == reservationRepository.addReservation(reservation) ) {
            throw new Exception("The reservation exist, please update it");
        }
    }

    public Collection<Reservation> readAllReservations() {
        return this.reservationRepository.getAllReservation();
    }

    public void updateReservationById(Integer id, Reservation reservation) throws Exception {
        reservation.setId(id);
        if ( !reservationRepository.updateReservation(id, reservation) ) {
            throw new Exception("The reservation exist, please update it");
        }
    }

    public Reservation findById(Integer id) throws Exception {
        return reservationRepository.findById(id).orElseThrow( () -> new Exception("The reservation "+id+" doesn't exist"));
    }

}
