package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Producer;
import zavrsni.webapp.picea.database.models.Sowing;
import zavrsni.webapp.picea.database.models.Sort;
import zavrsni.webapp.picea.viewmodels.SowingVM;

import java.util.List;

@Controller
public class SowingController {

    private final Firestore db;

    @Autowired
    public SowingController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/sowings")
    public String sowings(Model model) {
        List<Sowing> sowings = Sowing.getAll(db);
        List<Producer> producers = Producer.getAll(db);
        List<Sort> sorts = Sort.getAll(db);

        List<SowingVM> models = SowingVM.from(sowings, producers, sorts);
        model.addAttribute("sowings", models);
        return "Sowing/sowings";
    }

    @GetMapping("/admin/new/sowing")
    public String showCreateSowingForm(Model model) {
        model.addAttribute("sowing", new Sowing());
        List<Producer> producers = Producer.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("producers", producers);
        model.addAttribute("sorts", sorts);
        return "Sowing/create-sowing";
    }

    @PostMapping("/admin/save/sowing")
    public String saveSowing(@RequestParam("producerId") String producerId, @RequestParam("sortId") String sortId, @RequestParam("year") int year, @RequestParam String location) {
        Sowing sowing = new Sowing(producerId, sortId, year, location);
        Sowing.create(db, sowing);
        return "redirect:/admin/sowings";
    }

    @PostMapping("/admin/save/sowing/{id}")
    public String saveSowing(@PathVariable("id") String id, @RequestParam("producerId") String producerId, @RequestParam("sortId") String sortId, @RequestParam("year") int year, @RequestParam String location) {
        Sowing sowing = Sowing.get(db, id);
        sowing.setProducerId(producerId);
        sowing.setSortId(sortId);
        sowing.setYear(year);
        sowing.setLocation(location);
        Sowing.update(db, sowing);
        return "redirect:/admin/sowings";
    }

    @PostMapping("/admin/delete/sowing/{id}")
    public String deleteSowing(@PathVariable("id") String id) {
        Sowing.delete(db, id);
        return "redirect:/admin/sowings";
    }

    @PostMapping("/admin/update/sowing/{id}")
    public String updateSowing(@PathVariable("id") String id, Model model) {
        Sowing sowing = Sowing.get(db, id);
        List<Producer> producers = Producer.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("producers", producers);
        model.addAttribute("sorts", sorts);
        model.addAttribute("sowing", sowing);
        return "Sowing/edit-sowing";
    }

}
