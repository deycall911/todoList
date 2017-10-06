package hello;

import hello.Entity.ToDoEntity;
import hello.Repository.ToDoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RestCallsController {

    @Autowired
    ToDoEntityRepository toDoEntityRepository;

    @RequestMapping(method = POST, value = "/api/insert/{job}")
    public ToDoEntity insert(@PathVariable String job) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setContent(job);
        return toDoEntityRepository.save(toDoEntity);
    }

    @RequestMapping(method = POST,value = "/api/delete/{id}")
    public Boolean delete(@PathVariable int id) {
        toDoEntityRepository.delete(id);
        return true;
    }

    @RequestMapping("/api/data")
    public Iterable<ToDoEntity> requestList(@RequestParam(required = false) Boolean done) {
        if (done == null) {
            return toDoEntityRepository.findAll();
        }
        return toDoEntityRepository.findByDone(done);
    }

    @RequestMapping("/api/markDone/{id}/{done}")
    public ToDoEntity markAsDone(@PathVariable int id, @PathVariable Boolean done) {
        ToDoEntity toDoEntity = toDoEntityRepository.findById(id);
        if (toDoEntity == null) {
            return null;
        }
        toDoEntity.setDone(done);
        return toDoEntityRepository.save(toDoEntity);
    }

    @RequestMapping("/a")
    public String a() {
        throw new NullPointerException("e");
    }

}
