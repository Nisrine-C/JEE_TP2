package ma.enset.jee_tp2;

import ma.enset.jee_tp2.entities.Entreprise;
import ma.enset.jee_tp2.entities.Patient;
import ma.enset.jee_tp2.repository.EntrepriseRepository;
import ma.enset.jee_tp2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JeeTp2Application implements CommandLineRunner {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public static void main(String[] args) {
        SpringApplication.run(JeeTp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* Entreprise entreprise = Entreprise.builder()
                .nom("test")
                .email("test@test.com")
                .domaineActivite("IT")
                .username("testest")
                .build();

        entrepriseRepository.save(entreprise);*/
    }
}
