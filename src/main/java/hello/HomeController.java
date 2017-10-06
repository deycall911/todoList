package hello;

import hello.Repository.ToDoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    ToDoEntityRepository toDoEntityRepository;
    
    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
