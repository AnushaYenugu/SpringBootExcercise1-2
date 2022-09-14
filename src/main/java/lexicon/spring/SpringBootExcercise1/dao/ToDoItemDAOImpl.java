package lexicon.spring.SpringBootExcercise1.dao;

import lexicon.spring.SpringBootExcercise1.model.ToDoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public class ToDoItemDAOImpl implements ToDoItemDAO{

@PersistenceContext
private EntityManager entityManager;

    @Override
    public Optional<ToDoItem> findById(int id) {
    if(id<=0) throw new IllegalArgumentException("Invalid Id");
    ToDoItem toDoItem=entityManager.find(ToDoItem.class,id);
    return Optional.ofNullable(toDoItem);
    }

    @Transactional
    @Override
    public ToDoItem save(ToDoItem toDoItem) {
        if(toDoItem==null) throw new IllegalArgumentException("ToDoItem is null");
        entityManager.persist(toDoItem);
        return toDoItem;
    }

    @Override
    public ToDoItem update(ToDoItem toDoItem) {
      return  entityManager.merge(toDoItem);

    }

    @Override
    public void delete(ToDoItem toDoItem) {
        findById(toDoItem.getToDoId()).orElseThrow(()-> new IllegalArgumentException("Invalid ToDoItem"));
        entityManager.remove(toDoItem);

    }


}
