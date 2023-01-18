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

    @PostMapping("/")
    public Reservation create(@RequestBody Reservation reservation) throws Exception {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public Collection<Reservation> finall() {
        return reservationService.readAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathParam("id") Integer id) throws Exception {
        return reservationService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Reservation reservation, @PathParam("id") Integer id) throws Exception {
        reservationService.updateReservationById(id, reservation);
    }
}
