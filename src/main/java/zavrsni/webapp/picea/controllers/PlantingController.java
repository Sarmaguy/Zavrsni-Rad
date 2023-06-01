package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Planting;
import zavrsni.webapp.picea.database.models.Producer;
import zavrsni.webapp.picea.database.models.Sort;
import zavrsni.webapp.picea.database.models.Sowing;
import zavrsni.webapp.picea.viewmodels.PlantingVM;

import java.util.List;

@Controller
public class PlantingController {

    private final Firestore db;

    @Autowired
    public PlantingController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/plantings")
    public String plantings(Model model) {
        List<Planting> plantings = Planting.getAll(db);
        List<Producer> producers = Producer.getAll(db);
        List<Sort> sorts = Sort.getAll(db);

        List<PlantingVM> models = PlantingVM.from(plantings, producers, sorts);

        model.addAttribute("plantings", models);
        return "Planting/plantings";
    }

    @GetMapping("/admin/new/planting")
    public String showCreatePlantingForm(Model model) {
        model.addAttribute("planting", new Planting());
        List<Producer> producers = Producer.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("producers", producers);
        model.addAttribute("sorts", sorts);
        return "Planting/create-planting";
    }

    @PostMapping("/admin/save/planting")
    public String savePlanting(@ModelAttribute Planting planting) {
        Planting.create(db, planting);
        return "redirect:/admin/plantings";
    }

    @PostMapping("/admin/save/planting/{id}")
    public String savePlanting(@PathVariable("id") String id, @RequestParam("producerId") String producerId, @RequestParam("sortId") String sortId, @RequestParam("year") int year, @RequestParam("amount") int amount, @RequestParam String location) {
        Planting planting = Planting.get(db, id);
        planting.setProducerId(producerId);
        planting.setSortId(sortId);
        planting.setYear(year);
        planting.setAmount(amount);
        planting.setLocation(location);
        Planting.update(db, planting);
        return "redirect:/admin/plantings";
    }

    @PostMapping("/admin/update/planting/{id}")
    public String updatePlanting(@PathVariable("id") String id, Model model) {
        Planting planting = Planting.get(db, id);
        List<Producer> producers = Producer.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("producers", producers);
        model.addAttribute("sorts", sorts);
        model.addAttribute("planting", planting);
        return "Planting/edit-planting";
    }

    @PostMapping("/admin/delete/planting/{id}")
    public String deletePlanting(@PathVariable("id") String id) {
        Planting.delete(db, id);
        return "redirect:/admin/plantings";
    }
}
