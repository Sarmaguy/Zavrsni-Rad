package zavrsni.webapp.picea.database.models;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Abstract class that represents a model in the database and provides default implementations for CRUD operations.
 * All models that extend this class must implement getId method.
 *
 * @author Jura
 * @version 1.0
 */
public abstract class DatabaseModel {
    /**
     * Method that returns the id of the model
     * @return Id of the model
     */

    public abstract String getId();

    /**
     * Default implementation of getAll method for all models that extend DatabaseModel
     * @param db Firestore instance
     * @param t Class of the model
     * @return Collection of models from the database
     * @param <T> Type of the model that extends DatabaseModel
     */
    public static  <T extends DatabaseModel> List<T> getAll(Firestore db, Class t) {
        String collectionName = t.getSimpleName();
        CollectionReference ref = db.collection(collectionName);
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

    /**
     * Default implementation of get method for all models that extend DatabaseModel
     * @param db Firestore instance
     * @param t Class of the model
     * @param id Id of the model
     * @return Model from the database
     * @param <T> Type of the model that extends DatabaseModel
     */
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

    /**
     * Default implementation of update method for all models that extend DatabaseModel
     * @param db Firestore instance
     * @param model Model to be updated
     * @param <T> Type of the model that extends DatabaseModel
     */
    public static <T extends DatabaseModel> void update(Firestore db, T model) {
        String collectionName = model.getClass().getSimpleName();
        String path = collectionName + "/" + model.getId();

        db.document(path).set(model);
    }



}
