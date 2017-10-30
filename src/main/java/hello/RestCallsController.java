package hello;

import hello.Entity.ToDoEntity;
import hello.Repository.ToDoEntityRepository;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Iterable<ToDoEntity> requestList(@RequestParam(required = false) Boolean done, @RequestBody(required = false) List<Integer> ids) {
        if (done == null) {
            if (ids != null) {
                return toDoEntityRepository.findAll(ids);
            }
            return toDoEntityRepository.findAll();
        }
        if (ids == null) {
            return toDoEntityRepository.findByDone(done);
        }
        return StreamSupport.stream(toDoEntityRepository.findAll(ids).spliterator(), false)
                .filter(x -> x.isDone() == done).collect(Collectors.toList());
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
