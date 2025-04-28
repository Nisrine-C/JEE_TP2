package ma.enset.jee_tp2.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.jee_tp2.entities.Patient;
import ma.enset.jee_tp2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "10") int size,
                        @RequestParam(name="keyword",defaultValue = "") String keyword){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Long id,
                         @RequestParam(name="keyword",defaultValue = "")String keyword,
                         @RequestParam(name="page",defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, String keyword,int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null ) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }

    @PostMapping("/save")
    public  String save(Model model,
                        @Valid Patient patient,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "") String keyword,
                        @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        if(patient.isMalade())
        {
            System.out.println("checkbox is checked");
        }
        else
        {
            System.out.println("checkbox is not checked");
        }
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

}
*/