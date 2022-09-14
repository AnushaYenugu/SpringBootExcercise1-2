package lexicon.spring.SpringBootExcercise1.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ToDoItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int toDoId;
        @Column(length = 250,nullable = false)
        private String title;
        @Column(length = 1000,nullable = false)
        private String description;
        @Column(nullable = false)
       // @CreationTimestamp
        private LocalDateTime deadLine ;
        private boolean done;

        public ToDoItem(String title, String description, LocalDateTime deadLine) {
            this.title = title;
            this.description = description;
            this.deadLine = deadLine;
        }

        public ToDoItem() {
        }

        public ToDoItem(int toDoId, String title, String description, LocalDateTime deadLine, boolean done) {
            this.toDoId = toDoId;
            this.title = title;
            this.description = description;
            this.deadLine = deadLine;
            this.done = done;
        }

        public int getToDoId() {
            return toDoId;
        }

        public void setToDoId(int toDoId) {
            this.toDoId = toDoId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDateTime getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(LocalDateTime deadLine) {
            this.deadLine = deadLine;
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ToDoItem toDoItem = (ToDoItem) o;
            return toDoId == toDoItem.toDoId && done == toDoItem.done && title.equals(toDoItem.title) && description.equals(toDoItem.description) && deadLine.equals(toDoItem.deadLine);
        }

        @Override
        public int hashCode() {
            return Objects.hash(toDoId, title, description, deadLine, done);
        }

        @Override
        public String toString() {
            return "ToDoItem{" +
                    "toDoId=" + toDoId +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", deadLine=" + deadLine +
                    ", done=" + done +
                    '}';
        }


}
