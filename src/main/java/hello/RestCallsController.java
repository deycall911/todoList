package hello;

import hello.Entity.ToDoEntity;
import hello.Repository.ToDoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCallsController {

    @Autowired
    ToDoEntityRepository toDoEntityRepository;

    @RequestMapping("/insert/{job}")
    public Boolean insert(@PathVariable String job) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setContent(job);
        toDoEntityRepository.save(toDoEntity);
        return true;
    }

    @RequestMapping("/delete/{id}")
    public Boolean delete(@PathVariable int id) {
        toDoEntityRepository.delete(id);
        return true;
    }

    @RequestMapping("/data")
    public Iterable<ToDoEntity> requestList() {
        return toDoEntityRepository.findAll();
    }

    @RequestMapping("/toDo")
    public List<ToDoEntity> requestNotDone() {
        return toDoEntityRepository.findByDone(false);
    }

}
