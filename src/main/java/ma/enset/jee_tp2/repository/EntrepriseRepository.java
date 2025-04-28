package ma.enset.jee_tp2.repository;

import ma.enset.jee_tp2.entities.Entreprise;
import ma.enset.jee_tp2.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
    Page<Entreprise> findByNomContains(String keyword, Pageable pageable);

}
