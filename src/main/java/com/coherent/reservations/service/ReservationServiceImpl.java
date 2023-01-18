package com.coherent.reservations.service;

import com.coherent.reservations.domain.Reservation;
import com.coherent.reservations.exception.ServiceException;
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

    public Reservation createReservation(Reservation reservation) {
        if ( null == reservationRepository.addReservation(reservation) ) {
            throw new ServiceException("The reservation exist, please update it");
        }
        return reservation;
    }

    public Collection<Reservation> readAllReservations() {
        return this.reservationRepository.getAllReservation();
    }

    public void updateReservationById(Integer id, Reservation reservation) {
        reservation.setId(id);
        if ( !reservationRepository.updateReservation(id, reservation) ) {
            throw new ServiceException("The reservation "+id+" doesn't exist");
        }
    }

    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).orElseThrow( () -> new ServiceException("The reservation "+id+" doesn't exist"));
    }
}
