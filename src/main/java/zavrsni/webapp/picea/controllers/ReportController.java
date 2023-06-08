package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import zavrsni.webapp.picea.database.models.*;
import zavrsni.webapp.picea.viewmodels.ProductVM;

import java.util.List;

@Controller
public class ReportController {
    private final Firestore db;

    @Autowired
    public ReportController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/report")
    public String report(Model model) {
        List<Product> products = Product.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        List<Sowing> sowings = Sowing.getAll(db);
        List<Planting> plantings = Planting.getAll(db);
        List<Harvest> harvests = Harvest.getAll(db);
        List<Pricing> pricings = Pricing.getAll(db);



        List<Reservation> reservations = Reservation.getAll(db);

        List<ProductVM> models = ProductVM.from(products, sorts, sowings, plantings, harvests, pricings, reservations);

        model.addAttribute("products", models);
        model.addAttribute("sorts", sorts);
        return "report";
    }
}
