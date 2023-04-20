package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.List;

public class Sort extends DatabaseModel {

    private String sortName;
    private String latin;

    public Sort(String id, String sortName, String latin) {
        super(id);
        this.sortName = sortName;
        this.latin = latin;
    }

    public Sort(String sortName, String latin) {
        this("", sortName, latin);
    }

    public Sort() {
        this("", "", "");
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "sortName='" + sortName + '\'' +
                ", latin='" + latin + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public static ApiFuture<WriteResult> create(Firestore db, Sort sort) {
        return DatabaseModel.create(db, sort);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Sort.class);
    }

    public static Sort get(Firestore db, String id) {
        return DatabaseModel.get(db, Sort.class, id);
    }

    public static List<Sort> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Sort.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Sort sort) {
        return DatabaseModel.update(db, sort);
    }
}
