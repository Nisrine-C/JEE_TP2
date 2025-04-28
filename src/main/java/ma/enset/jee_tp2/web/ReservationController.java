package ma.enset.jee_tp2.web;

import jakarta.validation.Valid;
import ma.enset.jee_tp2.entities.Entreprise;
import ma.enset.jee_tp2.entities.Reservation;
import ma.enset.jee_tp2.repository.EntrepriseRepository;
import ma.enset.jee_tp2.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping("/reservations")
    public String getReservationsByEntreprise(Long id, Model model) {
        List<Reservation> reservations = reservationRepository.findByEntrepriseId(id);
        model.addAttribute("reservations", reservations);
        model.addAttribute("id", id);
        System.out.println("Generated URL for entreprise: /reservation/" + id); // Log the URL
        return "reservations";
    }


    @GetMapping("/reservations/form")
    public String formEntreprise(Model model, Long id) {
        Reservation reservation = new Reservation();
        reservation.setEntreprise(entrepriseRepository.findById(id).orElse(null));
        model.addAttribute("reservation", new Reservation());
        return "formReservation";
    }

    // Modifier une entreprise existante
    @GetMapping("/reservation/edit")
    public String editEntreprise(Model model,Long id, String keyword, int page) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        if (reservation == null) {
            throw new RuntimeException("Entreprise introuvable");
        }
        model.addAttribute("entreprise", reservation);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editEntreprise";
    }

    // Sauvegarder une entreprise (ajouter ou modifier)
    @PostMapping("/reservation/save")
    public String save(Model model,
                       @Valid Reservation reservation,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "formReservation";
        reservationRepository.save(reservation);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    // Supprimer une entreprise
    @GetMapping("/reservation/delete")
    public String delete(Long id, @RequestParam(name="keyword", defaultValue = "") String keyword, @RequestParam(name="page", defaultValue = "0") int page) {
        reservationRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }
}
