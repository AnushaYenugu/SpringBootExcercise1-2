package lexicon.spring.SpringBootExcercise1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    private String statusCode;

    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "status_id"),
    inverseJoinColumns = @JoinColumn(name = "car_id"))
    Collection<Car> collectionOfCars=new ArrayList<>();

    public Status() {
    }

    public Status(String statusCode) {

        this.statusCode = statusCode;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Collection<Car> getCollectionOfCars() {
        return collectionOfCars;
    }

    public void setCollectionOfCars(Collection<Car> collectionOfCars) {
        this.collectionOfCars = collectionOfCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId && statusCode.equals(status.statusCode) && collectionOfCars.equals(status.collectionOfCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusCode, collectionOfCars);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusCode='" + statusCode + '\'' +
                ", collectionOfCars=" + collectionOfCars +
                '}';
    }
}
