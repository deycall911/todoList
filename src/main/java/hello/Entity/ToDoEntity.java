package hello.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo_list")
public class ToDoEntity {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String content;

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    private Boolean done = false;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToDoEntity)) {
            return false;
        }
        if (!((ToDoEntity) obj).getContent().equals(this.getContent())) {
            return false;
        }
        if (!((ToDoEntity) obj).getId().equals(this.getId())) {
            return false;
        }
        if (!((ToDoEntity) obj).isDone().equals(this.done)) {
            return false;
        }
        return true;
    }
}
