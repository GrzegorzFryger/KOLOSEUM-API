package workerboard.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.ApplicationToMuchArguments;
import workerboard.exception.ApplicationWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.VievsForApplicationUser;
import workerboard.model.dto.UserPasswordDto;
import workerboard.model.enums.UserRole;
import workerboard.serivce.ApplicationUserService;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;

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


    @JsonView(VievsForApplicationUser.Public.class)
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable @NotNull Long id) throws ApplicationNotFound {

        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser newApplicationUser, @PathVariable @NotNull Long id) throws ApplicationNotFound {

        return ResponseEntity.ok(userService.updateUser(id, newApplicationUser));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ApplicationUser> updatePassword( @RequestBody UserPasswordDto password, @PathVariable Long id) throws ApplicationWrongPassword, ApplicationNotFound {

        return ResponseEntity.ok(userService.updatePassword(id, password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable @NotNull Long id) throws ApplicationNotFound {

        return  ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping("/email/{email}")
    @JsonView(VievsForApplicationUser.Public.class)
    public ResponseEntity<ApplicationUser> getUserByEmail(@PathVariable @NotNull String email) throws ApplicationNotFound{
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/parameters")
    @JsonView(VievsForApplicationUser.Public.class)
    public ResponseEntity<List> getUserByParameters( @RequestParam Map<String,String> allRequestParams, ModelMap model) throws ApplicationNotFound, ApplicationToMuchArguments {

       return ResponseEntity.ok(userService.getUserByParameters(allRequestParams));
    }
    @GetMapping("/users")
    @JsonView(VievsForApplicationUser.Public.class)
    public ResponseEntity<List<ApplicationUser>> getAllUsers() throws ApplicationNotFound {

        return ResponseEntity.ok(userService.findAllUsers());
    }
    
}
