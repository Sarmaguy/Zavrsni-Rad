package zavrsni.webapp.picea.database.models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.List;

public class Reservation extends DatabaseModel {
    public String productId;
    public String reservationName;
    public String reservationSurname;
    public String phoneNumber;
    public String info;

    public Reservation(String id, String productId, String reservationName, String reservationSurname, String phoneNumber, String info) {
        super(id);
        this.productId = productId;
        this.reservationName = reservationName;
        this.reservationSurname = reservationSurname;
        this.phoneNumber = phoneNumber;
        this.info = info;
    }

    public Reservation(String productId, String reservationName, String reservationSurname, String phoneNumber, String info) {
        this("", productId, reservationName, reservationSurname, phoneNumber, info);
    }

    public Reservation() {
        this("", "", "", "", "", "");
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public String getReservationSurname() {
        return reservationSurname;
    }

    public void setReservationSurname(String reservationSurname) {
        this.reservationSurname = reservationSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "productId='" + productId + '\'' +
                ", reservationName='" + reservationName + '\'' +
                ", reservationSurname='" + reservationSurname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", info='" + info + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static ApiFuture<WriteResult> create(Firestore db, Reservation reservation) {
        return DatabaseModel.create(db, reservation);
    }

    public static ApiFuture<WriteResult> delete(Firestore db, String id) {
        return DatabaseModel.delete(db, id, Reservation.class);
    }

    public static Reservation get(Firestore db, String id) {
        return DatabaseModel.get(db, Reservation.class, id);
    }

    public static List<Reservation> getAll(Firestore db) {
        return DatabaseModel.getAll(db, Reservation.class);
    }

    public static ApiFuture<WriteResult> update(Firestore db, Reservation reservation) {
        return DatabaseModel.update(db, reservation);
    }
}
