package zavrsni.webapp.picea.database;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Service
public class FBInitialize {

    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("./appsettings.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://chatapp-e6e15.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new FBInitialize().initialize();

        addNewProducer();

        printProducers();

    }

    private static void addNewProducer() {
        Firestore db = FirestoreClient.getFirestore();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref = ref.child("Producer").push();


        ApiFuture<WriteResult> collectionsApiFuture = db.collection("Producer").document(ref.getKey()).set(new Producer("huehuehue"));
        while (!collectionsApiFuture.isDone()) {
            System.out.println("Waiting for producer to be added");
        }

    }

    private static void printProducers() {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("Producer").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
            System.out.println(document.getData());
        }
    }


}