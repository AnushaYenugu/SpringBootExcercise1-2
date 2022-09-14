package lexicon.spring.SpringBootExcercise1.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    @Column(nullable = false)
    private String regNumber;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private LocalDate regDate;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="owned_car")
    private AppUser owner;

    @ManyToMany(mappedBy = "collectionOfCars",cascade ={ CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    private Collection<Status> statusCodes;

    public Car() {
    }

    public Car(String regNumber, String brand, String model, LocalDate regDate) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
          }


    public void  addStatus(Status status){
        if(status==null) throw new IllegalArgumentException("Status is null");
        if(statusCodes==null) statusCodes =new ArrayList<>();
        statusCodes.add(status);
        status.getCollectionOfCars().add(this);
    }

    public void remove(Status status) {
        if (status == null) throw new IllegalArgumentException("Status is null");
        if (statusCodes != null) {
            if (statusCodes.contains(status)) {
                statusCodes.remove(status);
                status.getCollectionOfCars().remove(this);
            }
        }
    }



        public Collection<Status> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Collection<Status> statusCodes) {
        this.statusCodes = statusCodes;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && regNumber.equals(car.regNumber) && brand.equals(car.brand) && model.equals(car.model) && regDate.equals(car.regDate) && owner.equals(car.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, regNumber, brand, model, regDate, owner);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regDate=" + regDate +
                ", owner=" + owner +
                '}';
    }
}


