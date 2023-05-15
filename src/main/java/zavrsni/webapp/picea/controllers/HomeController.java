package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zavrsni.webapp.picea.database.models.*;
import zavrsni.webapp.picea.viewmodels.ProductVM;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private final Firestore db;

    @Autowired
    public HomeController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = Product.getAll(db);
        List<Sort> sorts = Sort.getAll(db);
        List<Sowing> sowings = Sowing.getAll(db);
        List<Planting> plantings = Planting.getAll(db);
        List<Harvest> harvests = Harvest.getAll(db);
        List<Pricing> pricings = Pricing.getAll(db);
        List<Reservation> reservations = Reservation.getAll(db);

        List<ProductVM> models = ProductVM.fromHome(products, sorts, sowings, plantings, harvests, pricings, reservations);

        model.addAttribute("products", models);
        return "home";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable("id") String id, Model model){
        Product product = Product.get(db, id);
        ProductVM modelProduct = ProductVM.from(product, db);

        model.addAttribute("product", modelProduct);

        return "product";
    }

    @PostMapping("/reserve/{id}")
    public String reserve(@PathVariable("id") String id, Model model){
        Reservation reservation = new Reservation();
        reservation.setProductId(id);
        model.addAttribute("reservation", reservation);
        return "reserve";
    }

    @PostMapping("/reservation")
    public String saveReservation(Reservation reservation) {

        //check if in database is reservation with same productId
        List<Reservation> reservations = Reservation.getAll(db);
        for (Reservation res : reservations) {
            if (res.getProductId().equals(reservation.getProductId())) {
                return "redirect:/";
            }
        }

        Reservation.create(db, reservation);
        return "redirect:/";
    }
/*
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
            // Get the Firebase Storage reference
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            // Create a reference to the image file in Firebase Storage
            StorageReference imageRef = storageRef.child(file.getOriginalFilename());

            // Upload the image file to Firebase Storage
            InputStream stream = new ByteArrayInputStream(file.getBytes());
            imageRef.putStream(stream);
        } catch (Exception e) {
            // Handle the error
            e.printStackTrace();
        }

        // Redirect to the home page
        return "redirect:/";
    }

 */




}
