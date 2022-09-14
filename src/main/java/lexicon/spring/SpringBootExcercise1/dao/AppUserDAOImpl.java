package lexicon.spring.SpringBootExcercise1.dao;

import lexicon.spring.SpringBootExcercise1.model.AppUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public AppUser save(AppUser appUser) {
        if(appUser==null) throw new IllegalArgumentException("AppUser is null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser findById(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid Id");
        AppUser appUser =  entityManager.find(AppUser.class,id);
        return appUser;
    }

    @Transactional
    @Override
    public void delete(AppUser appUser) {
        if(appUser.getUserId()<=0) throw new IllegalArgumentException("Invalid AppUser");
        entityManager.remove(appUser);
    }


}
