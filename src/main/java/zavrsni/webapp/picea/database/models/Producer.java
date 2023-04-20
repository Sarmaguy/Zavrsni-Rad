package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.List;

public class Producer extends DatabaseModel{
    public String producerName;

    public Producer(String id, String producerName) {
        super(id);
        this.producerName = producerName;

    }
    public Producer(String producerName) {
        this("", producerName);
    }
    public Producer() {
        this("", "");
    }

    public String getProducerName() {
        return producerName;
    }
    public void setProducerName(String s) {
        this.producerName = s;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "producerName='" + producerName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public static ApiFuture<WriteResult> create(Firestore db, Producer producer) {
        return DatabaseModel.create(db,producer);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
       return DatabaseModel.delete(db, id, Producer.class);
    }

    public static Producer get(Firestore db, String id) {
        return DatabaseModel.get(db, Producer.class, id);
    }

    public static List<Producer> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Producer.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Producer producer) {
        return DatabaseModel.update(db, producer);
    }
}
