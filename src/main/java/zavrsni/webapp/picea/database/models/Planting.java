package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.Date;
import java.util.List;

public class Planting extends DatabaseModel {
    private String producerId;
    private String sortId;
    private int amount;
    private int year;

    public Planting(String id, String producerId, String sortId, int amount, int year) {
        super(id);
        this.producerId = producerId;
        this.sortId = sortId;
        this.amount = amount;
        this.year = year;
    }

    public Planting(String producerId, String sortId, int amount, int year) {
        this("", producerId, sortId, amount, year);
    }

    public Planting() {
        this("", "", "", 0, 0);
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Planting{" +
                "id='" + id + '\'' +
                ", producerId='" + producerId + '\'' +
                ", sortId='" + sortId + '\'' +
                ", amount=" + amount +
                ", year=" + year +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public static ApiFuture<WriteResult> create(Firestore db, Planting planting) {
        return DatabaseModel.create(db, planting);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Planting.class);
    }

    public static Planting get(Firestore db, String id) {
        return DatabaseModel.get(db, Planting.class, id);
    }

    public static List<Planting> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Planting.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Planting planting) {
        return DatabaseModel.update(db, planting);
    }
}
