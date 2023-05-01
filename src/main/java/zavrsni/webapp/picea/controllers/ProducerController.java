package zavrsni.webapp.picea.controllers;


import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Producer;

import java.util.List;

@Controller
public class ProducerController {

    private final Firestore db;
    @Autowired
    public ProducerController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/producers")
    public String  producers(Model model){
        List<Producer> producers = Producer.getAll(db);
        model.addAttribute("producers", producers);
        return "Producer/producers";
    }

    @GetMapping("/admin/new/producer")
    public String showCreateProducerForm(Model model) {
        model.addAttribute("producer", new Producer());
        return "Producer/create-producer";
    }

    @PostMapping("/admin/save/producer")
    public String saveProducer(@RequestParam("producerName") String producerName) {
        Producer producer = new Producer();
        producer.setProducerName(producerName);

        Producer.create(db, producer);

        return "redirect:/admin/producers";
    }

    @PostMapping("/admin/save/producer/{id}")
    public String saveProducer(@PathVariable("id") String id, @RequestParam("producerName") String producerName) {
        Producer producer = Producer.get(db, id);
        producer.setProducerName(producerName);

        Producer.update(db, producer);

        return "redirect:/admin/producers";
    }


    @PostMapping("/admin/delete/producer/{id}")
    public String deleteProducer(@PathVariable("id") String id) {
        Producer.delete(db, id);

        return "redirect:/admin/producers";
    }

    @PostMapping("/admin/update/producer/{id}")
    public String updateProducer(@PathVariable("id") String id, Model model) {
        Producer producer = Producer.get(db, id);
        model.addAttribute("producer", producer);
        return "Producer/edit-producer";
    }

}
