package lexicon.spring.SpringBootExcercise1;

import lexicon.spring.SpringBootExcercise1.dao.AppUserDAO;
import lexicon.spring.SpringBootExcercise1.dao.ToDoItemDAO;
import lexicon.spring.SpringBootExcercise1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Transactional
@Component
public class MyCommandLine implements CommandLineRunner {
    //@PersistenceContext
   // EntityManager entityManager;

private AppUserDAO appUserDAO;
private ToDoItemDAO toDoItemDAO;
private EntityManager entityManager;
@Autowired
public MyCommandLine(AppUserDAO appUserDAO,ToDoItemDAO toDoItemDAO,EntityManager entityManager){
    this.appUserDAO=appUserDAO;
    this.toDoItemDAO=toDoItemDAO;
    this.entityManager=entityManager;
}

    @Override
    public void run(String... args) throws Exception {
        AppUser appUser1=new AppUser("AnushaYenugu","Anusha","Yenugu", LocalDate.parse("1992-11-28"),"anusha");
       appUserDAO.save(appUser1);

        System.out.println(appUser1);
       AppUser appUser2=new AppUser("HarikaYenugu","Harika","yenugu",LocalDate.parse("1996-01-07"),"harika");
        AppUser appUser3=new AppUser("SreeDivisha","Sree","Divisha",LocalDate.parse("2018-09-30"),"sree");
        AppUser appUser4=new AppUser("RajeshReddy","Rajesh","kasala",LocalDate.parse("1990-02-13"),"sree");
        System.out.println(appUserDAO.save(appUser2));
        System.out.println(appUserDAO.save(appUser3));
         System.out.println(appUserDAO.save(appUser4));
        System.out.println(appUserDAO.findById(appUser1.getUserId()));
        System.out.println(appUserDAO.findById(appUser2.getUserId()));
        System.out.println(appUserDAO.findById(appUser3.getUserId()));


        AppUserAddress addressUser1=new AppUserAddress("Huskvarna","Hagstensgatan 3A","56136");
        AppUserAddress addressUser2=new AppUserAddress("Hyderabad","Hayathnagar","501505");
        AppUserAddress addressUser3=new AppUserAddress("Huskvarna","Hagstensgatan 3A","56136");
        AppUserAddress addressUser4=new AppUserAddress("Test","test1","1234");
      //  entityManager.persist(addressUser1);
       // entityManager.persist(addressUser2);
       // entityManager.persist(addressUser3);
       // entityManager.persist(addressUser4);
        appUser1.setAddress(addressUser1);
        appUser2.setAddress(addressUser2);
        appUser3.setAddress(addressUser3);
        appUser4.setAddress(addressUser4);
        Car appUser1Car=new Car("1234","BMW","X00",LocalDate.now());
        Car appUser1Car2=new Car("23456","Porche","Latest",LocalDate.now());
        Collection<Car> carListUser1=new ArrayList<>(Arrays.asList(appUser1Car,appUser1Car2));

        appUser1.setCollectionOfCars(carListUser1);
        appUser1.addCar(appUser1Car);
        appUser1.addCar(appUser1Car2);

       // entityManager.flush();

        appUser2.addCar(new Car("0170","Tesla","007XX",LocalDate.now()));
        appUser2.addCar(new Car("4679","Mercidis","0990CX",LocalDate.now()));
        appUser3.addCar(new Car("201830","Tesla","00743",LocalDate.now()));
        entityManager.flush();
        appUser1.getCollectionOfCars().forEach(System.out::println);
        appUser2.getCollectionOfCars().forEach(System.out::println);
        appUser3.getCollectionOfCars().forEach(System.out::println);

        System.out.println(appUserDAO.findById(appUser1.getUserId()));
        appUserDAO.delete(appUser4);


        Status statusCar1=new Status("car1 status 1");
        Status status2Car1=new Status("car1 status 2");
     Collection<Status> collectionStatus=new ArrayList<>(Arrays.asList(statusCar1,status2Car1));
     //   Collection<Status> collectionStatus=new ArrayList<>(Arrays.asList(statusCar1));
   //     Collection<Status> collectionStatusCar2=new ArrayList<>(Arrays.asList(status2Car1));


       // appUser1Car.setStatusCodes(collectionStatus);
     //   appUser1Car2.setStatusCodes(collectionStatusCar2);

        appUser1Car.addStatus(statusCar1);
        appUser1Car.addStatus(status2Car1);

       // appUser1Car2.addStatus(status2Car1);







        entityManager.flush();

        ToDoItem toDoItem1=new ToDoItem("Prod Issue","At Database", LocalDateTime.now());
       System.out.println(toDoItemDAO.save(toDoItem1));
        System.out.println(toDoItemDAO.findById(toDoItem1.getToDoId()));

    }
}
