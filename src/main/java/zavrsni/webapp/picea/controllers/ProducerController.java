package zavrsni.webapp.picea.controllers;


import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import zavrsni.webapp.picea.database.models.Producer;

import java.util.List;

@Controller
public class ProducerController {

    private final Firestore db;
    @Autowired
    public ProducerController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/producers")
    public String  producers(Model model){
        List<Producer> producers = Producer.getAll(db);
        model.addAttribute("producers", producers);
        return "producers";
    }
}
