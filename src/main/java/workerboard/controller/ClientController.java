package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.WrongTypeArguments;
import workerboard.model.Person;
import workerboard.serivce.ClientService;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchPerson( @RequestParam Map<String,
                String> allRequestParams, ModelMap model) throws WrongTypeArguments {

        if (allRequestParams.isEmpty() || allRequestParams.values().equals("")) {
            throw new WrongTypeArguments("Wrong type arguments");
        }


        return ResponseEntity.ok(clientService.findAllByAttributeLike(allRequestParams));


    }

}
