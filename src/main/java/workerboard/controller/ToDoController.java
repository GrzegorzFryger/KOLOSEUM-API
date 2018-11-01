package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.model.ToDoCard;
import workerboard.model.dto.ToDoCardCreateDto;
import workerboard.model.dto.ToDoCardUpdateDto;
import workerboard.serivce.ToDoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/todo")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @PostMapping
    ResponseEntity<ToDoCard> createToDoCard(@RequestBody ToDoCardCreateDto toDoCard){
        return ResponseEntity.ok(toDoService.createToDoCard(toDoCard));
    }

    @GetMapping
    ResponseEntity<List<ToDoCard>> getAllToDoCards(){
        return ResponseEntity.ok(toDoService.getAllToDoCards());
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
