package com.coherent.reservations;

import com.coherent.reservations.domain.Reservation;
import com.coherent.reservations.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReservationsApplicationTests {

	@Autowired
	ReservationService reservationService;
	@Test
	void contextLoads() {
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

}
