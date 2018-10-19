package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workerboard.serivce.ApplicationUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ApplicationUserController {

    private ApplicationUserService userService;

    @Autowired
    public ApplicationUserController(ApplicationUserService userService) {
        this.userService = userService;
    }


}
