package com.coherent.reservations.controller;

import com.coherent.reservations.domain.Reservation;
import com.coherent.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public Collection<Reservation> finall() {
        return reservationService.readAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable("id") Integer id) {
        return reservationService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Reservation reservation, @PathVariable("id") Integer id) {
        reservationService.updateReservationById(id, reservation);
    }
}
