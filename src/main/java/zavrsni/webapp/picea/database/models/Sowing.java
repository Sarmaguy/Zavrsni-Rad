package zavrsni.webapp.picea.database.models;

import com.google.cloud.firestore.Firestore;

import java.util.List;

public class Sowing extends DatabaseModel {
    private int year;
    private String producerId;
    private String sortId;
    private String sowingId;

    public Sowing(int year, String producerId, String sortId, String sowingId) {
        this.year = year;
        producerId = producerId;
        sortId = sortId;
        this.sowingId = sowingId;
    }

    public Sowing() {
        this(0, "", "","");
    }

    public int getYear() {
        return year;
    }

    public String getProducerId() {
        return producerId;
    }

    public String getSortId() {
        return sortId;
    }

    @Override
    public String toString() {
        return "Sowing{" +
                "year=" + year +
                ", ProducerId='" + producerId + '\'' +
                ", SortId='" + sortId + '\'' +
                ", SowingId='" + sowingId + '\'' +
                '}';
    }

    public void setYear(int i) {
        this.year = i;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public void setSowingId(String sowingId) {
        this.sowingId = sowingId;
    }

    public String getSowingId() {
        return sowingId;
    }

    public static List<Sowing> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Sowing.class);
    }

    public static Sowing get(Firestore db, String id) {
        return DatabaseModel.get(db, Sowing.class, id);
    }

    public static void update(Firestore db, Sowing sowing) {
        DatabaseModel.update(db, sowing);
    }

    @Override
    public String getId() {
        return sowingId;
    }
}
