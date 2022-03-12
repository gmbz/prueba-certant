package certantPrueba.vtv.routes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("titulo", "Inicio");
        return "index";
    }
}
