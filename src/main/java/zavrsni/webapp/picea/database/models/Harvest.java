package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.List;

public class Harvest extends DatabaseModel {
    private String sortId;
    private String date;
    private double amount;

    public Harvest(String id, String sortId, String date, double amount) {
        super(id);
        this.sortId = sortId;
        this.date = date;
        this.amount = amount;
    }

    public Harvest(String sortId, String date, double amount) {
        this("", sortId, date, amount);
    }

    public Harvest() {
        this("", "", 0.0);
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Harvest{" +
                "id='" + id + '\'' +
                ", sortId='" + sortId + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public static ApiFuture<WriteResult> create(Firestore db, Harvest harvest) {
        return DatabaseModel.create(db, harvest);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Harvest.class);
    }

    public static Harvest get(Firestore db, String id) {
        return DatabaseModel.get(db, Harvest.class, id);
    }

    public static List<Harvest> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Harvest.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Harvest harvest) {
        return DatabaseModel.update(db, harvest);
    }
}
