package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workerboard.serivce.RegistrationUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/registration")
public class ApplicationUserRegistration {


    private RegistrationUserService registrationUserService;

    @Autowired
    public ApplicationUserRegistration(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @PostMapping
    public String addNewUSer()
    {
        //ToDo
        return null;
    }
}
