package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Harvest;
import zavrsni.webapp.picea.database.models.Producer;
import zavrsni.webapp.picea.database.models.Sort;
import zavrsni.webapp.picea.viewmodels.HarvestVM;

import java.util.List;

@Controller
public class HarvestController {

    private final Firestore db;

    @Autowired
    public HarvestController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/harvests")
    public String harvests(Model model) {
        List<Harvest> harvests = Harvest.getAll(db);
        List<Sort> sorts = Sort.getAll(db);

        List<HarvestVM> models = HarvestVM.from(harvests, sorts);

        model.addAttribute("harvests", models);
        return "Harvest/harvests";
    }

    @GetMapping("/admin/new/harvest")
    public String showCreateHarvestForm(Model model) {
        model.addAttribute("harvest", new Harvest());
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("sorts", sorts);
        return "Harvest/create-harvest";
    }

    @PostMapping("/admin/save/harvest")
    public String saveHarvest(@ModelAttribute Harvest harvest) {
        Harvest.create(db, harvest);
        return "redirect:/admin/harvests";
    }

    @PostMapping("/admin/save/harvest/{id}")
    public String saveHarvest(@PathVariable("id") String id, @RequestParam("sortId") String sortId, @RequestParam("date") String date, @RequestParam("amount") int amount) {
        Harvest harvest = Harvest.get(db, id);
        harvest.setSortId(sortId);
        harvest.setDate(date);
        harvest.setAmount(amount);
        Harvest.update(db, harvest);
        return "redirect:/admin/harvests";
    }

    @PostMapping("/admin/update/harvest/{id}")
    public String updateHarvest(@PathVariable("id") String id, Model model) {
        Harvest harvest = Harvest.get(db, id);
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("sorts", sorts);
        model.addAttribute("harvest", harvest);
        return "Harvest/edit-harvest";
    }

    @PostMapping("/admin/delete/harvest/{id}")
    public String deleteHarvest(@PathVariable("id") String id) {
        Harvest.delete(db, id);
        return "redirect:/admin/harvests";
    }
}
