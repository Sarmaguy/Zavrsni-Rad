package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.*;
import zavrsni.webapp.picea.viewmodels.ProductVM;

import java.util.List;

@Controller
public class ProductController {

    private final Firestore db;

    @Autowired
    public ProductController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/products")
    public String products(Model model) {
        List<Product> products = Product.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        List<Sowing> sowings = Sowing.getAll(db);
        List<Planting> plantings = Planting.getAll(db);
        List<Harvest> harvests = Harvest.getAll(db);
        List<Pricing> pricings = Pricing.getAll(db);

        List<ProductVM> models = ProductVM.from(products, sorts, sowings, plantings, harvests, pricings);

        model.addAttribute("products", models);
        return "Product/products";
    }

    @GetMapping("/admin/new/product/sort")
    public String showCreateSortForm(Model model) {
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("sorts", sorts);
        model.addAttribute("sort", new Sort());
        return "Product/choose-sort";
    }

    @PostMapping("/admin/new/product")
    public String showCreateProductForm(@RequestParam("sortId") String sortId, Model model) {
        Sort sort = Sort.get(db, sortId);
        List<Sowing> sowings = Sowing.getAll(db).stream().filter(s -> s.getSortId().equals(sort.getId())).toList();
        List<Planting> plantings = Planting.getAll(db).stream().filter(s -> s.getSortId().equals(sort.getId())).toList();
        List<Harvest> harvests = Harvest.getAll(db).stream().filter(s -> s.getSortId().equals(sort.getId())).toList();
        List<Pricing> pricings = Pricing.getAll(db).stream().filter(s -> s.getSortId().equals(sort.getId())).toList();
        Product product = new Product();
        product.setSortId(sort.getId());
        model.addAttribute("sowings", sowings);
        model.addAttribute("plantings", plantings);
        model.addAttribute("harvests", harvests);
        model.addAttribute("pricings", pricings);
        model.addAttribute("product", product);
        model.addAttribute("sort", sort);
        return "Product/create-product";
    }

    @PostMapping("/admin/save/product")
    public String saveProduct(@ModelAttribute Product product) {
        Product.create(db, product);
        return "redirect:/admin/products";
    }


    @PostMapping("/admin/save/product/{id}")
    public String saveProduct(@PathVariable("id") String id, @RequestParam("sortId") String sortId,
                           @RequestParam("sowingId") String sowingId,
                           @RequestParam("plantingId") String plantingId,
                           @RequestParam("harvestId") String harvestId,
                           @RequestParam("priceId") String priceId,
                           @RequestParam("productSize") String productSize,
                           @RequestParam("description") String description,
                           @RequestParam("inPot") boolean inPot,
                            @RequestParam("picture") String picture) {
        Product product = Product.get(db, id);
        product.setSortId(sortId);
        product.setSowingId(sowingId);
        product.setPlantingId(plantingId);
        product.setHarvestId(harvestId);
        product.setPriceId(priceId);
        product.setProductSize(productSize);
        product.setDescription(description);
        product.setInPot(inPot);
        product.setPicture(picture);
        Product.update(db, product);
        return "redirect:/admin/products";
    }


    @PostMapping("/admin/update/product/{id}")
    public String updateProduct(@PathVariable("id") String id, Model model) {
        Product product = Product.get(db, id);
        List<Sort> sorts = Sort.getAll(db);
        List<Sowing> sowings = Sowing.getAll(db);
        List<Planting> plantings = Planting.getAll(db);
        List<Harvest> harvests = Harvest.getAll(db);
        List<Pricing> pricings = Pricing.getAll(db);
        model.addAttribute("sowings", sowings);
        model.addAttribute("plantings", plantings);
        model.addAttribute("harvests", harvests);
        model.addAttribute("pricings", pricings);
        model.addAttribute("sorts", sorts);
        model.addAttribute("product", product);
        return "Product/edit-product";
    }

    @PostMapping("/admin/delete/product/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        Product.delete(db, id);
        return "redirect:/admin/products";
    }
}
