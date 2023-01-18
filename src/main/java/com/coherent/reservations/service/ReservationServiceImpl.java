package com.coherent.reservations.service;

import com.coherent.reservations.domain.Reservation;
import com.coherent.reservations.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) throws Exception {
        if ( null == reservationRepository.addReservation(reservation) ) {
            throw new Exception("The reservation exist, please update it");
        }
        return reservation;
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
