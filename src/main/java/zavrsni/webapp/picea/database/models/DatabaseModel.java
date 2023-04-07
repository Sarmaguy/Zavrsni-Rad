package zavrsni.webapp.picea.database.models;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class DatabaseModel {

    public abstract String getId();


    public static  <T extends DatabaseModel> List<T> getAll(Firestore db, Class t) {
        String collectionName = t.getSimpleName();
        CollectionReference ref = db.collection(collectionName);

        try {
            Sowing s = db.document("Sowing/43mBCOsx9rb8tKQojKgz").get().get().toObject(Sowing.class);
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ref.getId());
        System.out.println(ref.getPath());

        List<T> list = null;

        try {
            list =  ref.get().get().toObjects(t);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static  <T extends DatabaseModel> T get(Firestore db, Class t, String id) {
        String collectionName = t.getSimpleName();
        String path = collectionName + "/" + id;

        T model = null;

        try {
            model = (T) db.document(path).get().get().toObject(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return model;

    }

    public static <T extends DatabaseModel> void update(Firestore db, T model) {
        String collectionName = model.getClass().getSimpleName();
        String path = collectionName + "/" + model.getId();

        db.document(path).set(model);
    }



}
