package zavrsni.webapp.picea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanel {

    @GetMapping("/admin/panel")
    public String adminPanel(){
        return "admin";
    }
}
