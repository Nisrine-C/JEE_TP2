package ma.enset.jee_tp2.web;

import jakarta.validation.Valid;
import ma.enset.jee_tp2.entities.Entreprise;
import ma.enset.jee_tp2.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Entreprise> pageEntreprises = entrepriseRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listEntreprises", pageEntreprises.getContent());
        model.addAttribute("pages", new int[pageEntreprises.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "entreprises";
    }

    @GetMapping("/form")
    public String formEntreprise(Model model) {
        model.addAttribute("entreprise", new Entreprise());
        return "formEntreprise";
    }

    // Modifier une entreprise existante
    @GetMapping("/edit")
    public String editEntreprise(Model model, Long id, String keyword, int page) {
        Entreprise entreprise = entrepriseRepository.findById(id).orElse(null);
        if (entreprise == null) {
            throw new RuntimeException("Entreprise introuvable");
        }
        model.addAttribute("entreprise", entreprise);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editEntreprise";
    }

    // Sauvegarder une entreprise (ajouter ou modifier)
    @PostMapping("/save")
    public String save(Model model,
                       @Valid Entreprise entreprise,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "formEntreprise";
        entrepriseRepository.save(entreprise);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    // Supprimer une entreprise
    @GetMapping("/delete")
    public String delete(Long id, @RequestParam(name="keyword", defaultValue = "") String keyword, @RequestParam(name="page", defaultValue = "0") int page) {
        entrepriseRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }
}
