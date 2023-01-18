package com.coherent.reservations;

import com.coherent.reservations.domain.Reservation;
import com.coherent.reservations.repositories.ReservationRepository;
import com.coherent.reservations.service.ReservationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {

    @Test
    public void createReservationTest () {
        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService = new ReservationService(reservationRepository);
        Reservation reservation = new Reservation();
        reservation.setClientFullName("Juan");
        reservation.setRoomNumber(5);
        try {
            reservationService.createReservation(reservation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(reservationService.readAllReservations().size(),1);
    }

    @Test
    public void updateReservationById() {
        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService = new ReservationService(reservationRepository);
        Reservation reservation = new Reservation();
        reservation.setClientFullName("Juan");
        reservation.setRoomNumber(5);
        try {
            reservationService.createReservation(reservation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        reservation = new Reservation();
        reservation.setClientFullName("Juan Manuel");
        reservation.setRoomNumber(1);

        try {
            reservationService.updateReservationById(1, reservation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            reservation = reservationService.findById(1);
            assertEquals(reservation.getId(), 1 );
            assertEquals(reservation.getClientFullName(), "Juan Manuel" );
            assertEquals(reservation.getRoomNumber(), 1 );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readAllReservationsTest () {
        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService = new ReservationService(reservationRepository);

        try {
            Reservation reservation = new Reservation();
            reservation.setClientFullName("Juan");
            reservation.setRoomNumber(5);
            reservationService.createReservation(reservation);

            reservation = new Reservation();
            reservation.setClientFullName("Juan 2");
            reservation.setRoomNumber(1);
            reservationService.createReservation(reservation);

            reservation = new Reservation();
            reservation.setClientFullName("Juan 3");
            reservation.setRoomNumber(2);
            reservationService.createReservation(reservation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(reservationService.readAllReservations().size(),3);
    }
}
