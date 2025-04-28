package ma.enset.jee_tp2.repository;

import ma.enset.jee_tp2.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByEntrepriseId(Long entrepriseId);
}
