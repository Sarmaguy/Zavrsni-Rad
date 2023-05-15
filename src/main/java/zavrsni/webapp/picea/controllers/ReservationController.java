package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Producer;
import zavrsni.webapp.picea.database.models.Product;
import zavrsni.webapp.picea.database.models.Reservation;

import java.util.List;

@Controller
public class ReservationController {

    private final Firestore db;

    @Autowired
    public ReservationController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/reservations")
    public String reservations(Model model) {
        List<Reservation> reservations = Reservation.getAll(db);
        model.addAttribute("reservations", reservations);
        return "Reservation/reservations";
    }

    @GetMapping("/admin/new/reservation")
    public String showCreateReservationForm(Model model) {
        List<Product> products = Product.getAll(db);
        model.addAttribute("products", products);
        model.addAttribute("reservation", new Reservation());
        return "Reservation/create-reservation";
    }

    @PostMapping("/admin/save/reservation")
    public String saveReservation(@RequestParam("productId") String productId,
                                  @RequestParam("reservationName") String reservationName,
                                  @RequestParam("reservationSurname") String reservationSurname,
                                  @RequestParam("phoneNumber") String phoneNumber,
                                  @RequestParam("info") String info) {
        Reservation reservation = new Reservation(productId, reservationName, reservationSurname, phoneNumber, info);
        Reservation.create(db, reservation);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/admin/save/reservation/{id}")
    public String saveReservation(@PathVariable("id") String id,
                                  @RequestParam("productId") String productId,
                                  @RequestParam("reservationName") String reservationName,
                                  @RequestParam("reservationSurname") String reservationSurname,
                                  @RequestParam("phoneNumber") String phoneNumber,
                                  @RequestParam("info") String info) {
        Reservation reservation = Reservation.get(db, id);
        reservation.setProductId(productId);
        reservation.setReservationName(reservationName);
        reservation.setReservationSurname(reservationSurname);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setInfo(info);
        Reservation.update(db, reservation);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/admin/update/reservation/{id}")
    public String editReservation(@PathVariable String id, Model model) {
        Reservation reservation = Reservation.get(db, id);
        List<Product> products = Product.getAll(db);
        model.addAttribute("products", products);
        model.addAttribute("reservation", reservation);
        return "/Reservation/edit-reservation";
    }

    @PostMapping("/admin/delete/reservation/{id}")
    public String deleteReservation(@PathVariable("id") String id) {
        Reservation.delete(db, id);

        return "redirect:/admin/reservations";
    }

}