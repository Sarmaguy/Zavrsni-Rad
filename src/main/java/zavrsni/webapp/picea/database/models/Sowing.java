package zavrsni.webapp.picea.database.models;

public class Sowing {
    private int year;
    private String producerId;
    private String sortId;

    public Sowing(int year, String producerId, String sortId) {
        this.year = year;
        producerId = producerId;
        sortId = sortId;
    }

    public Sowing() {
        this(0, "", "");
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
                '}';
    }
}
