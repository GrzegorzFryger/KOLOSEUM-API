package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.RegistrationNotAddException;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.serivce.ApplicationUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserService userService;

    @Autowired
    public ApplicationUserController(ApplicationUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<ApplicationUserDto> findUser(@PathVariable String userEmail) throws ApplicationNotFound
    {


        if (userService.getUser(userEmail).mapToDao() != null) {
            return ResponseEntity.ok(userService.getUser(userEmail).mapToDao());
        }


        throw new ApplicationNotFound("Not added user");



    }


}
