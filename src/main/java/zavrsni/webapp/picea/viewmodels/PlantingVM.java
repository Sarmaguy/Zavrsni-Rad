package zavrsni.webapp.picea.viewmodels;

import zavrsni.webapp.picea.database.models.Planting;
import zavrsni.webapp.picea.database.models.Producer;
import zavrsni.webapp.picea.database.models.Sort;

import java.util.List;

public class PlantingVM {
    private String id;
    private String producerName;
    private String sortName;
    private int amount;
    private int year;
    private String location;

    public PlantingVM(String id, String producerName, String sortName, int amount, int year, String location) {
        this.id = id;
        this.producerName = producerName;
        this.sortName = sortName;
        this.amount = amount;
        this.year = year;
        this.location = location;
    }

    public PlantingVM() {
        this("", "", "", 0, 0, "");
    }

    public static List<PlantingVM> from(List<Planting> plantings, List<Producer> producers, List<Sort> sorts) {
        List<PlantingVM> models = new java.util.ArrayList<>();

       for (Planting planting : plantings) {
            PlantingVM model = new PlantingVM();
            model.year =  planting.getYear();
            model.id = planting.getId();
            model.amount =  planting.getAmount();
            model.location = planting.getLocation();
            try {
                model.producerName = producers.stream().filter(p -> p.getId().equals(planting.getProducerId())).findFirst().get().getProducerName();
            } catch (Exception e) {
                model.producerName = "N/A";
            }
            try {
                model.sortName = sorts.stream().filter(s -> s.getId().equals(planting.getSortId())).findFirst().get().getSortName();
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

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = (int) amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = (int) year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
