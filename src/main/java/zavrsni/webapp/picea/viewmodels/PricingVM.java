package zavrsni.webapp.picea.viewmodels;

import zavrsni.webapp.picea.database.models.Pricing;
import zavrsni.webapp.picea.database.models.Sort;

import java.util.ArrayList;
import java.util.List;

public class PricingVM {
    private String Id;
    private String sortName;
    private String size;
    private String price;
    private String season;

    public PricingVM(String Id, String sortName, String size, String price, String season) {
        this.Id = Id;
        this.sortName = sortName;
        this.size = size;
        this.price = price;
        this.season = season;
    }

    public PricingVM() {
        this("", "", "", "", "");
    }

    public static List<PricingVM> from(List<Pricing> pricings, List<Sort> sorts) {
        List<PricingVM> models = new ArrayList<>();

        for (Pricing pricing : pricings) {
            PricingVM model = new PricingVM();
            model.Id = pricing.getId();
            model.size = pricing.getPricingSize();
            model.price = pricing.getPricingPrice();
            model.season = pricing.getSeason();
            try {
                model.sortName = sorts.stream().filter(s -> s.getId().equals(pricing.getSortId())).findFirst().get().getSortName();
            } catch (Exception e) {
                model.sortName = "N/A";
            }
            models.add(model);
        }
        return models;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
