package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.DatesDto;
import workerboard.security.jwt.CurrentUser;
import workerboard.security.jwt.model.JwtUserPrincipal;
import workerboard.serivce.InsuranceService;
import workerboard.serivce.ToolsService;

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



}
