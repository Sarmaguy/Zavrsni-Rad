package zavrsni.webapp.picea.viewmodels;

import zavrsni.webapp.picea.database.models.Harvest;
import zavrsni.webapp.picea.database.models.Sort;

import java.util.ArrayList;
import java.util.List;

public class HarvestVM {
    private String id;
    private String sortName;
    private String date;
    private int amount;

    public HarvestVM(String id, String sortName, String date, int amount) {
        this.id = id;
        this.sortName = sortName;
        this.date = date;
        this.amount = amount;
    }

    public HarvestVM() {
        this("", "", "", 0);
    }

    public static List<HarvestVM> from(List<Harvest> harvests, List<Sort> sorts) {
        List<HarvestVM> models = new ArrayList<>();

        for (Harvest harvest : harvests) {
            HarvestVM model = new HarvestVM();
            model.id = harvest.getId();
            model.amount = harvest.getAmount();
            model.date = harvest.getDate();
            try {
                model.sortName = sorts.stream().filter(s -> s.getId().equals(harvest.getSortId())).findFirst().get().getSortName();
            } catch (Exception e) {
                model.sortName = "N/A";
            }
            models.add(model);
        }
        return models;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
