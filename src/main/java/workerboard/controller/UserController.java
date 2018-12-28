package workerboard.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.NotFound;
import workerboard.exception.WrongTypeArguments;
import workerboard.exception.UserWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.UserPasswordDto;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.UserService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.util.List;
import java.util.Map;

import static workerboard.serivce.BasicAbstractService.andCriteria;
import static workerboard.serivce.BasicAbstractService.likeCriteria;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
    }


    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable @NotNull Long id) throws NotFound {

        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser newApplicationUser,
                                                      @PathVariable @NotNull Long id) throws NotFound {

        return ResponseEntity.ok(userService.updateUser(id, newApplicationUser));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ApplicationUser> updatePassword( @RequestBody UserPasswordDto password,
                                                           @PathVariable Long id) throws UserWrongPassword, NotFound {

        return ResponseEntity.ok(userService.updatePassword(id, password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable @NotNull Long id) throws NotFound {

        return  ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping("/email/{email}")
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    public ResponseEntity<ApplicationUser> getUserByEmail(@PathVariable @NotNull String email) throws NotFound {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/users")
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    public ResponseEntity<List<ApplicationUser>> getAllUsers() throws NotFound {

        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/search/{typeSearch}")
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    public ResponseEntity<?> search(@PathVariable @DefaultValue("where") String typeSearch, @RequestParam Map<String,
            String> allRequestParams, ModelMap model) throws WrongTypeArguments {

        if(allRequestParams.isEmpty() || allRequestParams.values().equals("")) {
            throw new WrongTypeArguments("Wrong type arguments");
        }


        switch (typeSearch){
            case "where":
                return this.searchUserWhere(allRequestParams);
            case "like":
                return  this.searchUserLike(allRequestParams);
             default:
                 throw new WrongTypeArguments("Not supported type search");
        }

    }

    protected ResponseEntity<?> searchUserWhere( Map<String,String> allRequestParams)  {

        return ResponseEntity.ok(userService.findAllByCriteria(andCriteria(),
                allRequestParams));
    }


    protected ResponseEntity<?> searchUserLike( Map<String,String> allRequestParams) throws WrongTypeArguments {

        if(allRequestParams.size() > 1) {
            throw new WrongTypeArguments("Only one method supported");
        }

        return ResponseEntity.ok(userService.findAllByCriteria(likeCriteria(),
                allRequestParams));
    }

}
