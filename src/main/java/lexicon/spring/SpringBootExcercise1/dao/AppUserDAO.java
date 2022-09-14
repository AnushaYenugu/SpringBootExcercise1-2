package lexicon.spring.SpringBootExcercise1.dao;

import lexicon.spring.SpringBootExcercise1.model.AppUser;

public interface AppUserDAO {

    public AppUser save(AppUser appUser);
    public AppUser findById(int id);
    public void delete(AppUser appUser);
}
