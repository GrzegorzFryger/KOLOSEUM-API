package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.model.dto.DatesDto;
import workerboard.model.enums.UserRole;
import workerboard.serivce.ToolsService;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tools")
public class ToolsController {

    private ToolsService toolsService;


    @Autowired
    public ToolsController(ToolsService toolsService) {
        this.toolsService = toolsService;

    }

    @GetMapping("/dates")
    ResponseEntity<DatesDto> getDates() {
        return ResponseEntity.ok(toolsService.getDates());
    }

    @PostMapping("/roles")
    ResponseEntity<List<UserRole>> getAllRoles() {
        return ResponseEntity.ok(Arrays.asList(UserRole.values()));
    }



}
