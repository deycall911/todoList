package hello.Repository;

import hello.Entity.ToDoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ToDoEntityRepository extends CrudRepository<ToDoEntity, Integer> {
    public ToDoEntity findById(Integer id);
    public List<ToDoEntity> findByDone(Boolean done);
}
