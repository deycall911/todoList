package hello;

import hello.Entity.ToDoEntity;
import hello.Repository.ToDoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ToDoEntityRepository toDoEntityRepository;
    
    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
