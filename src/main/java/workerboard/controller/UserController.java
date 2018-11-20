package workerboard.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.UserNotFound;
import workerboard.exception.ToMuchArguments;
import workerboard.exception.UserWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.UserPasswordDto;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable @NotNull Long id) throws UserNotFound {

        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser newApplicationUser, @PathVariable @NotNull Long id) throws UserNotFound {

        return ResponseEntity.ok(userService.updateUser(id, newApplicationUser));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ApplicationUser> updatePassword( @RequestBody UserPasswordDto password, @PathVariable Long id) throws UserWrongPassword, UserNotFound {

        return ResponseEntity.ok(userService.updatePassword(id, password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable @NotNull Long id) throws UserNotFound {

        return  ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping("/email/{email}")
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    public ResponseEntity<ApplicationUser> getUserByEmail(@PathVariable @NotNull String email) throws UserNotFound {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/parameters")
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    public ResponseEntity<List> getUserByParameters( @RequestParam Map<String,String> allRequestParams, ModelMap model) throws UserNotFound, ToMuchArguments {

       return ResponseEntity.ok(userService.getUserByParameters(allRequestParams));
    }
    @GetMapping("/users")
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    public ResponseEntity<List<ApplicationUser>> getAllUsers() throws UserNotFound {

        return ResponseEntity.ok(userService.findAllUsers());
    }



}
