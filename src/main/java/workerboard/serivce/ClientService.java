package workerboard.serivce;

import org.springframework.stereotype.Service;
import workerboard.model.Person;
import workerboard.repository.ClientRepository;

import java.util.List;
import java.util.Map;

@Service
public class ClientService extends BasicAbstractService<Person> {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        super.setBasicRepository(clientRepository);
        this.clientRepository = clientRepository;
    }


    public List<Person> findAllByAttributeLike(Map<String,String> attribute) {

        return super.findAllByCriteria(likeCriteria(), attribute);

    }
}


