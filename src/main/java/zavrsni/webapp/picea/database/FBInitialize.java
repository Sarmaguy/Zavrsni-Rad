package zavrsni.webapp.picea.database;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
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

        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionRef = firestore.collection("Producer");

        Query query = collectionRef;

        ApiFuture<QuerySnapshot> querySnapshot = query.get();


        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId() + " => " + document.getData().get("producerName"));
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference usersRef = database.getReference("Producer");

        Producer producer = new Producer("Producer 1");



    }

    private static class Producer{
        private String producerName;
        private String producerId;

        public Producer(String producerName, String producerId){
            this.producerName = producerName;
            this.producerId = producerId;
        }

        public Producer(String producerName){
            this.producerName = producerName;
        }
    }
}