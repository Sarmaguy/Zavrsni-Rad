package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.List;

public class Sowing extends DatabaseModel {

    private String producerId;
    private String sortId;
    private int year;

    public Sowing(String id, String producerId, String sortId, int year) {
        super(id);
        this.producerId = producerId;
        this.sortId = sortId;
        this.year = year;
    }

    public Sowing(String producerId, String sortId, int year) {
        this("", producerId, sortId, year);
    }

    public Sowing() {
        this("", "", "", 0);
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Sowing{" +
                "producerId='" + producerId + '\'' +
                ", sortId='" + sortId + '\'' +
                ", year=" + year +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public static ApiFuture<WriteResult> create(Firestore db, Sowing sowing) {
        return DatabaseModel.create(db, sowing);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Sowing.class);
    }

    public static Sowing get(Firestore db, String id) {
        return DatabaseModel.get(db, Sowing.class, id);
    }

    public static List<Sowing> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Sowing.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Sowing sowing) {
        return DatabaseModel.update(db, sowing);
    }
}
