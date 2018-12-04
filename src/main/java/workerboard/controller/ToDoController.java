package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.NotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.Role;
import workerboard.model.ToDoCard;
import workerboard.model.dto.ToDoCardCreateDto;
import workerboard.model.dto.ToDoCardUpdateDto;
import workerboard.model.enums.UserRole;
import workerboard.repository.ApplicationUserRepository;
import workerboard.repository.ToDoRepository;
import workerboard.serivce.ToDoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/todo")
public class ToDoController {

    @Autowired
    private ToDoRepository repository;
    private ApplicationUserRepository applicationUserRepository;


    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @PostMapping
    ResponseEntity<ToDoCard> createToDoCard(@RequestBody @Valid ToDoCardCreateDto toDoCard) throws NotFound {
        return ResponseEntity.ok(toDoService.createToDoCard(toDoCard));
    }

    @GetMapping
    ResponseEntity<List<ToDoCard>> getAllToDoCards(){


        return ResponseEntity.ok(toDoService.getAllToDoCards());
    }

    @GetMapping(path = "/byUser/{userId}")
    ResponseEntity<List<ToDoCard>> getAllToDoCardsByUser(@PathVariable("userId") Long id) throws NotFound {
        return ResponseEntity.ok(toDoService.getAllToDoCardsByUser(id));
    }

    @PutMapping
    ResponseEntity<ToDoCard> updateToDoCard(@RequestBody ToDoCardUpdateDto toDoCardUpdateDto){
        return ResponseEntity.ok(toDoService.updateToDoCard(toDoCardUpdateDto));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ToDoCard> getToDoCardById(@PathVariable("id") Long id){
        return ResponseEntity.ok(toDoService.getToDoCardById(id));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ToDoCard> deleteToDoCardById(@PathVariable("id") Long id){
        return ResponseEntity.ok(toDoService.deleteToDoCardById(id));
    }
}
