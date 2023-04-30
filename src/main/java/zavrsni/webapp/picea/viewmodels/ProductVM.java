package zavrsni.webapp.picea.viewmodels;

import zavrsni.webapp.picea.database.models.*;

import java.util.ArrayList;
import java.util.List;

public class ProductVM {
    private String id;
    private String sortName;
    private String sowingInfo;
    private String plantingInfo;
    private String harvestInfo;
    private String priceInfo;
    private String productSize;
    private String description;
    String inPot;

    public ProductVM() {
    }

    public ProductVM(String id, String sortName, String sowingInfo, String plantingInfo, String harvestInfo, String priceInfo, String productSize, String description, boolean inPot) {
        this.id = id;
        this.sortName = sortName;
        this.sowingInfo = sowingInfo;
        this.plantingInfo = plantingInfo;
        this.harvestInfo = harvestInfo;
        this.priceInfo = priceInfo;
        this.productSize = productSize;
        this.description = description;
    }

    /*
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
    */
    public static List<ProductVM> from(List<Product> products, List<Sort> sorts, List<Sowing> sowings, List<Planting> plantings, List<Harvest> harvests, List<Pricing> pricings) {
        List<ProductVM> models = new ArrayList<>();

        for (Product product : products){
            ProductVM model = new ProductVM();
            model.id = product.getId();
            model.productSize = product.getProductSize();
            model.description = product.getDescription();
            model.inPot = product.isInPot() ? "Product is in pot" : "Product is not in pot";

            try {
                model.sortName = sorts.stream().filter(s -> s.getId().equals(product.getSortId())).findFirst().get().getSortName();
            } catch (Exception e) {
                model.sortName = "N/A";
            }

            try{
                Sowing sowing = sowings.stream().filter(s -> s.getId().equals(product.getSowingId())).findFirst().get();
                model.sowingInfo =  String.valueOf(sowing.getYear());
            }
            catch (Exception e){
                model.sowingInfo = "N/A";
            }

            try{
                model.plantingInfo = String.valueOf(plantings.stream().filter(p -> p.getId().equals(product.getPlantingId())).findFirst().get().getYear());
            }
            catch (Exception e){
                model.plantingInfo = "N/A";
            }

            try{
                model.harvestInfo = String.valueOf(harvests.stream().filter(h -> h.getId().equals(product.getHarvestId())).findFirst().get().getDate());
            }
            catch (Exception e){
                model.harvestInfo = "N/A";
            }

            try{
                model.priceInfo = String.valueOf(pricings.stream().filter(p -> p.getId().equals(product.getPriceId())).findFirst().get().getPricingPrice());
            }
            catch (Exception e){
                model.priceInfo = "N/A";
            }

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

    public String getSowingInfo() {
        return sowingInfo;
    }

    public void setSowingInfo(String sowingInfo) {
        this.sowingInfo = sowingInfo;
    }

    public String getPlantingInfo() {
        return plantingInfo;
    }

    public void setPlantingInfo(String plantingInfo) {
        this.plantingInfo = plantingInfo;
    }

    public String getHarvestInfo() {
        return harvestInfo;
    }

    public void setHarvestInfo(String harvestInfo) {
        this.harvestInfo = harvestInfo;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInPot() {
        return inPot;
    }

    public void setInPot(boolean inPot) {
        this.inPot = inPot;
    }
}
