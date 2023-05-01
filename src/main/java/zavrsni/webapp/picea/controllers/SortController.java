package zavrsni.webapp.picea.controllers;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zavrsni.webapp.picea.database.models.Sort;

import java.util.List;

@Controller
public class SortController {

    private final Firestore db;
    @Autowired
    public SortController(Firestore db) {
        this.db = db;
    }

    @GetMapping("/admin/sorts")
    public String sorts(Model model){
        List<Sort> sorts = Sort.getAll(db);
        model.addAttribute("sorts", sorts);
        return "Sort/sorts";
    }

    @GetMapping("/admin/new/sort")
    public String showCreateSortForm(Model model) {
        model.addAttribute("sort", new Sort());
        return "Sort/create-sort";
    }

    @PostMapping("/admin/save/sort")
    public String saveSort(@RequestParam("sortName") String sortName, @RequestParam("latin") String latin) {
        Sort sort = new Sort();
        sort.setSortName(sortName);
        sort.setLatin(latin);

        Sort.create(db, sort);

        return "redirect:/admin/sorts";
    }

    @PostMapping("/admin/save/sort/{id}")
    public String saveSort(@PathVariable("id") String id, @RequestParam("sortName") String sortName, @RequestParam("latin") String latin) {
        Sort sort = Sort.get(db, id);
        sort.setSortName(sortName);
        sort.setLatin(latin);

        Sort.update(db, sort);

        return "redirect:/admin/sorts";
    }

    @PostMapping("/admin/delete/sort/{id}")
    public String deleteSort(@PathVariable("id") String id) {
        Sort.delete(db, id);

        return "redirect:/admin/sorts";
    }

    @PostMapping("/admin/update/sort/{id}")
    public String updateSort(@PathVariable("id") String id, Model model) {
        Sort sort = Sort.get(db, id);
        model.addAttribute("sort", sort);
        return "Sort/edit-sort";
    }
}
