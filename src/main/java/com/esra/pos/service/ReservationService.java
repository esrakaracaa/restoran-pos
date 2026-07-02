package com.esra.pos.service;

import com.esra.pos.model.Reservation;
import com.esra.pos.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus("BEKLEMEDE");
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservationStatus(Long id, String status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı!"));
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }
}