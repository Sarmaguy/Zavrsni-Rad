package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Pricing;
import zavrsni.webapp.picea.database.models.Sort;
import zavrsni.webapp.picea.viewmodels.PricingVM;

import java.util.List;

@Controller
public class PricingController {

    private final Firestore db;

    @Autowired
    public PricingController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/pricings")
    public String pricings(Model model) {
        List<Pricing> pricings = Pricing.getAll(db);
        List<Sort> sorts = Sort.getAll(db);

        List<PricingVM> models = PricingVM.from(pricings, sorts);

        model.addAttribute("pricings", models);
        return "Pricing/pricings";
    }

    @GetMapping("/admin/new/pricing")
    public String showCreatePricingForm(Model model) {
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("sorts", sorts);
        model.addAttribute("pricing", new Pricing());
        return "Pricing/create-pricing";
    }

    @PostMapping("/admin/save/pricing")
    public String savePricing(@ModelAttribute Pricing pricing) {
        Pricing.create(db, pricing);
        return "redirect:/admin/pricings";
    }

    @PostMapping("/admin/save/pricing/{id}")
    public String savePricing(@PathVariable("id") String id, @RequestParam("pricingSize") String pricingSize, @RequestParam("sortId") String sortId, @RequestParam("pricingPrice") String pricingPrice, @RequestParam("season") String season) {
        Pricing pricing = Pricing.get(db, id);
        pricing.setPricingSize(pricingSize);
        pricing.setSortId(sortId);
        pricing.setPricingPrice(pricingPrice);
        pricing.setSeason(season);
        Pricing.update(db, pricing);
        return "redirect:/admin/pricings";
    }

    @PostMapping("/admin/update/pricing/{id}")
    public String updatePricing(@PathVariable("id") String id, Model model) {
        Pricing pricing = Pricing.get(db, id);
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("sorts", sorts);
        model.addAttribute("pricing", pricing);
        return "Pricing/edit-pricing";
    }

    @PostMapping("/admin/delete/pricing/{id}")
    public String deletePricing(@PathVariable("id") String id) {
        Pricing.delete(db, id);
        return "redirect:/admin/pricings";
    }
}
