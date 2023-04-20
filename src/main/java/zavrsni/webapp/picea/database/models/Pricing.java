package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Pricing extends DatabaseModel {
    public String pricingSize;
    public String sortId;
    public String pricingPrice;
    public String season;

    public Pricing(String id, String pricingSize, String sortId, String pricingPrice, String season) {
        super(id);
        this.pricingSize = pricingSize;
        this.sortId = sortId;
        this.pricingPrice = pricingPrice;
        this.season = season;
    }

    public Pricing(String pricingSize, String sortId, String pricingPrice, String season) {
        this("", pricingSize, sortId, pricingPrice, season);
    }

    public Pricing() {
        this("", "", "", "", "");
    }

    public String getPricingSize() {
        return pricingSize;
    }

    public void setPricingSize(String pricingSize) {
        this.pricingSize = pricingSize;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getPricingPrice() {
        return pricingPrice;
    }

    public void setPricingPrice(String pricingPrice) {
        this.pricingPrice = pricingPrice;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "pricingSize='" + pricingSize + '\'' +
                ", sortId='" + sortId + '\'' +
                ", pricingPrice='" + pricingPrice + '\'' +
                ", season='" + season + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public static ApiFuture<WriteResult> create(Firestore db, Pricing pricing) {
        return DatabaseModel.create(db, pricing);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Pricing.class);
    }

    public static Pricing get(Firestore db, String id) {
        return DatabaseModel.get(db, Pricing.class, id);
    }

    public static List<Pricing> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Pricing.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Pricing pricing) {
        return DatabaseModel.update(db, pricing);
    }
}