package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.List;

public class Product extends DatabaseModel {
    private String sortId;
    private String sowingId;
    private String plantingId;
    private String harvestId;
    private String priceId;
    private String picture;
    private String productSize;
    private String description;
    private boolean inPot;

    public Product() {
        super("");
    }

    @Override
    public String getId() {
        return id;
    }

    public Product(String id, String sortId, String sowingId, String plantingId, String harvestId, String priceId,
                   String picture, String productSize, String description, boolean inPot) {
        super(id);
        this.sortId = sortId;
        this.sowingId = sowingId;
        this.plantingId = plantingId;
        this.harvestId = harvestId;
        this.priceId = priceId;
        this.picture = picture;
        this.productSize = productSize;
        this.description = description;
        this.inPot = inPot;
    }

    public Product(String sortId, String sowingId, String plantingId, String harvestId, String priceId,
                   String picture, String productSize, String description, boolean inPot) {
        this("", sortId, sowingId, plantingId, harvestId, priceId, picture, productSize, description, inPot);
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSowingId() {
        return sowingId;
    }

    public void setSowingId(String sowingId) {
        this.sowingId = sowingId;
    }

    public String getPlantingId() {
        return plantingId;
    }

    public void setPlantingId(String plantingId) {
        this.plantingId = plantingId;
    }

    public String getHarvestId() {
        return harvestId;
    }

    public void setHarvestId(String harvestId) {
        this.harvestId = harvestId;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    @Override
    public String toString() {
        return "Product{" +
                "sortId='" + sortId + '\'' +
                ", sowingId='" + sowingId + '\'' +
                ", plantingId='" + plantingId + '\'' +
                ", harvestId='" + harvestId + '\'' +
                ", priceId='" + priceId + '\'' +
                ", picture='" + picture + '\'' +
                ", productSize='" + productSize + '\'' +
                ", description='" + description + '\'' +
                ", inPot=" + inPot +
                ", id='" + id + '\'' +
                '}';
    }

    public static ApiFuture<WriteResult> create(Firestore db, Product product) {
        return DatabaseModel.create(db, product);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Product.class);
    }

    public static Product get(Firestore db, String id) {
        return DatabaseModel.get(db, Product.class, id);
    }

    public static List<Product> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Product.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Product product) {
        return DatabaseModel.update(db, product);
    }

}

