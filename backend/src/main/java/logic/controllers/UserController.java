package logic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/users/")
@RestController
public class UserController {
    @GetMapping("/info")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
