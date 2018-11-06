package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.RegistrationNotAddException;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.model.dto.UserPasswordDto;
import workerboard.serivce.ApplicationUserService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserController(ApplicationUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //    @GetMapping("/{userEmail}")
//    public ResponseEntity<ApplicationUser> getUserByEmail(@PathVariable @NotNull String userEmail) throws ApplicationNotFound {
//
//        if (userService.findUserByEmail(userEmail).isPresent()) {
//            return ResponseEntity.ok(userService.findUserByEmail(userEmail).get());
//        }
//
//        throw new ApplicationNotFound("Not added user");
//    }


    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable @NotNull Long id) throws ApplicationNotFound {
        if (userService.findUserById(id).isPresent()) {
            return ResponseEntity.ok(userService.findUserById(id).get());
        }


        throw new ApplicationNotFound("Not added user");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser newApplicationUser,@PathVariable @NotNull Long id) throws ApplicationNotFound {


        if(userService.update(id) != null) {

            return ResponseEntity.ok(userService.update(id));
        }

        throw new ApplicationNotFound("Can't update user, not found");

    }
    @PutMapping("/{id}/password")
    public ResponseEntity updatePassword(@RequestBody @NotNull UserPasswordDto password, @PathVariable Long id  )
    {
        //toDo
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable @NotNull Long id) throws ApplicationNotFound {

            if(this.userService.deleteUserById(id))
            {
                ResponseEntity.ok(HttpStatus.OK);
            }


        throw new ApplicationNotFound("No deleted");

    }

    @GetMapping("/users")
    public ResponseEntity<List<ApplicationUser>> getAllUsers() throws ApplicationNotFound {
        if(!userService.findAllUsers().isEmpty())
        {
            return ResponseEntity.ok(userService.findAllUsers());
        }

        throw new ApplicationNotFound("not found any users ");

    }










}
