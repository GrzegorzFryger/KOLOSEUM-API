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
import workerboard.repository.ToDoCardHistoryRepository;
import workerboard.repository.ToDoRepository;
import workerboard.serivce.ToDoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/todo")
public class ToDoController {


    private ToDoRepository repository;
    private ApplicationUserRepository applicationUserRepository;
    private ToDoCardHistoryRepository toDoCardHistoryRepository;
    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoRepository repository, ApplicationUserRepository applicationUserRepository,
                          ToDoCardHistoryRepository toDoCardHistoryRepository, ToDoService toDoService) {
        this.repository = repository;
        this.applicationUserRepository = applicationUserRepository;
        this.toDoCardHistoryRepository = toDoCardHistoryRepository;
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
    ResponseEntity<ToDoCard> updateToDoCard(@RequestBody @Valid ToDoCardUpdateDto toDoCardUpdateDto) throws NotFound {
        return ResponseEntity.ok(toDoService.updateToDoCard(toDoCardUpdateDto));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ToDoCard> getToDoCardById(@PathVariable("id") Long id) throws NotFound {
        return ResponseEntity.ok(toDoService.getToDoCardById(id));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deleteToDoCardById(@PathVariable("id") Long id) throws NotFound {

        toDoService.deleteToDoCardById(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(path = "/history/{id}")
    ResponseEntity<?> getHistoryToDoCard(@PathVariable Long id) throws NotFound {
        return ResponseEntity.ok( toDoCardHistoryRepository.findAllByCardId(id));
    }

}
