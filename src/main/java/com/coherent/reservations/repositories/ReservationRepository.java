package com.coherent.reservations.repositories;

import com.coherent.reservations.domain.Reservation;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ReservationRepository {

    private Integer lastId;
    private Set<Reservation> collection;

    public ReservationRepository() {
        collection = new HashSet<>();
        lastId = 0;
    }

    public Reservation addReservation(Reservation reservation) {
        reservation.setId(++lastId);
        if(collection.add(reservation)) {
            return reservation;
        }
        return null;
    }

    public Collection<Reservation> getAllReservation() {
        return collection;
    }

    public boolean updateReservation(Integer id, Reservation reservation) {
        if (collection.removeIf(r -> r.getId().equals(id))) {
            return collection.add(reservation);
        }
        return false;
    }

    public Optional<Reservation> findById(Integer id) {
        return collection.stream().filter(r->r.getId().equals(id)).findFirst();
    }
}
