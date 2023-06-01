package zavrsni.webapp.picea.viewmodels;

import zavrsni.webapp.picea.database.models.Producer;
import zavrsni.webapp.picea.database.models.Sort;
import zavrsni.webapp.picea.database.models.Sowing;

import java.util.ArrayList;
import java.util.List;

public class SowingVM {
    private String sortName;
    private String producerName;
    private int year;
    private String id;
    private String location;

    public SowingVM() {
    }

    public SowingVM(String sortName, String producerName, int year, String id, String location) {
        this.sortName = sortName;
        this.producerName = producerName;
        this.year = year;
        this.id = id;
        this.location = location;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public static List<SowingVM> from(List<Sowing> sowings, List<Producer> producers, List<Sort> sorts) {
        List<SowingVM> models = new ArrayList<>();

        for (Sowing sowing : sowings) {
            SowingVM model = new SowingVM();
            model.year = sowing.getYear();
            model.id = sowing.getId();
            model.location = sowing.getLocation();
            try {
                model.producerName = producers.stream().filter(p -> p.getId().equals(sowing.getProducerId())).findFirst().get().getProducerName();
            } catch (Exception e) {
                model.producerName = "N/A";
            }
            try {
                model.sortName = sorts.stream().filter(s -> s.getId().equals(sowing.getSortId())).findFirst().get().getSortName();
            } catch (Exception e) {
                model.sortName = "N/A";
            }
            models.add(model);
        }

        return models;
    }
}
