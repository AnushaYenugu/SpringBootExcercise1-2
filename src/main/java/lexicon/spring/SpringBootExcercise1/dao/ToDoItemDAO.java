package lexicon.spring.SpringBootExcercise1.dao;

import lexicon.spring.SpringBootExcercise1.model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface ToDoItemDAO {


        Optional<ToDoItem> findById(int id);
        ToDoItem save(ToDoItem toDoItem);
        ToDoItem update(ToDoItem toDoItem);
        void delete(ToDoItem toDoItem);
       // List<ToDoItem> findByDeadLineBetween(LocalDateTime start, LocalDateTime end);
        //ToDoItem findByDeadLineBefore();


}
