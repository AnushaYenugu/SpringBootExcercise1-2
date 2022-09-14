package lexicon.spring.SpringBootExcercise1.dao;

import lexicon.spring.SpringBootExcercise1.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class AppUserDAOImplTest {
@Autowired
AppUserDAO appUserDAO;
@Autowired
    TestEntityManager testEntityManager;
AppUser testAppUser;
List<AppUser> appUserListTest(){return Arrays.asList(
        new AppUser("MartinChilling","Martin","Chilling",LocalDate.now(),"martin"),
        new AppUser("CharlesChilling","Charles","Chilling",LocalDate.parse("2000-05-05"),"charles"),
        new AppUser("MikaChilling","Mika","Chilling",LocalDate.parse("1972-04-04"),"mika"),
        new AppUser("MillaChilling","Milla","Chilling",LocalDate.parse("2003-06-06"),"milla")

);}
@BeforeEach
void setUp(){
    List<AppUser> appUserList=appUserListTest().stream()
            .map(testEntityManager::persist)
            .collect(Collectors.toList());
    testAppUser=appUserList.get(0);
}


    @Test
    void save() {
        AppUser appUser1=new AppUser("AnushaYenuguTest","Anusha","Yenugu", LocalDate.parse("1992-11-28"),"anusha");

       appUserDAO.save(appUser1);

        assertNotEquals(0,appUser1.getUserId());
        assertEquals("AnushaYenuguTest",appUser1.getUserName());

    }

    @Test
    void findById() {
        AppUser appUser1=new AppUser("AnushaYenuguTest","Anusha","Yenugu", LocalDate.parse("1992-11-28"),"anusha");
        Integer id=testEntityManager.persistAndGetId(appUser1, Integer.class);
        testEntityManager.clear();

        AppUser appUserById=appUserDAO.findById(id);
       // AppUser appUserById1=appUserDAO.findById(testAppUser.getUserId());
        assertEquals(id,appUserById.getUserId());

    }

    @Test
    void delete() {
        AppUser appUser1=new AppUser("AnushaYenuguTest","Anusha","Yenugu", LocalDate.parse("1992-11-28"),"anusha");
         //  appUserDAO.save(appUser1);
        Integer id=testEntityManager.persistAndGetId(appUser1, Integer.class);

        //AppUser appUserById=appUserDAO.findById(id);
            appUserDAO.delete(appUser1);
        AppUser appUserById=appUserDAO.findById(id);
        //   assertNotEquals("AnushaYenuguTest",appUser1.getUserName());
        testEntityManager.flush();
      //  assertNotEquals(id,appUserById.getUserId());
        assertNull(appUserById);

    }

}