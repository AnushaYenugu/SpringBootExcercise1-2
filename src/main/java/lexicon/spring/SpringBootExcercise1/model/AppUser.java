package lexicon.spring.SpringBootExcercise1.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name ="AppUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
@Column(length = 250,nullable = false,unique = true)
    private String userName;
@Column(length = 100, nullable = false)
    private String firstName;
@Column(length = 100)
    private String lastName;
@Column(nullable = false)
    private LocalDate birthDay;
    private boolean active;
@Column(length = 250,nullable = false)
    private String password;
//Collection<Car> ownedCars;

@OneToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name="my_address",referencedColumnName = "address_id")
private AppUserAddress address;

@OneToMany(mappedBy = "owner",cascade = CascadeType.PERSIST)
private Collection<Car> collectionOfCars;

    protected AppUser() {
    }

    public AppUser(String userName, String firstName, String lastName, LocalDate birthDay, String password, AppUserAddress address,Collection<Car> car) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.password = password;
        this.address=address;
        this.collectionOfCars =car;
    }

    public AppUser(String userName, String firstName, String lastName, LocalDate birthDay, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.password = password;
    }

    public AppUser(int userId, String userName, String firstName, String lastName, LocalDate birthDay, boolean active, String password) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.active = active;
        this.password = password;
    }

    //Custom methods
public void  addCar(Car car){
    if(car==null) throw new IllegalArgumentException("Car is null");
    if(collectionOfCars==null) collectionOfCars=new ArrayList<>();
    collectionOfCars.add(car);
    car.setOwner(this);

}
public void remove(Car car){
    if(car==null) throw new IllegalArgumentException("Car is null");
    if(collectionOfCars!=null){
        if(collectionOfCars.contains(car)){
            collectionOfCars.remove(car);
            car.setOwner(null);
        }
    }

}

    public Collection<Car> getCollectionOfCars() {
        return collectionOfCars;
    }

    public void setCollectionOfCars(Collection<Car> collectionOfCars) {
        this.collectionOfCars = collectionOfCars;
    }

    public AppUserAddress getAddress() {
        return address;
    }

    public void setAddress(AppUserAddress address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return userId == appUser.userId && active == appUser.active && userName.equals(appUser.userName) && firstName.equals(appUser.firstName) && lastName.equals(appUser.lastName) && birthDay.equals(appUser.birthDay) && password.equals(appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, firstName, lastName, birthDay, active, password);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", active=" + active +
                ", password='" + password + '\'' +
                '}';
    }
}
