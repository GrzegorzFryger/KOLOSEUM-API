package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.exception.NotFound;
import workerboard.exception.WrongTypeArguments;
import workerboard.model.ApplicationUser;
import workerboard.model.ServiceMessage;
import workerboard.model.ToDoCard;
import workerboard.model.dto.ToDoCardCreateDto;
import workerboard.model.dto.ToDoCardUpdateDto;
import workerboard.model.enums.ServiceMessageType;
import workerboard.repository.ApplicationUserRepository;
import workerboard.repository.ToDoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;
    private ApplicationUserRepository applicationUserRepository;


    @Autowired
    public ToDoService(ToDoRepository toDoRepository, ApplicationUserRepository applicationUserRepository) {
        this.toDoRepository = toDoRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    public ToDoCard createToDoCard(ToDoCardCreateDto toDoCardCreateDto) throws NotFound {
        ToDoCard toDoCard = new ToDoCard();

        ApplicationUser applicationUser = applicationUserRepository
                .findById(toDoCardCreateDto.getUserId())
                .orElseThrow(() -> new NotFound("User not Found"));

        return toDoRepository.save(ToDoCard.createToDoCard(toDoCardCreateDto, applicationUser));
    }

    public List<ToDoCard> getAllToDoCards() {
        return toDoRepository.findAll();
    }

    public ToDoCard updateToDoCard(ToDoCardUpdateDto toDoCardUpdateDto) throws NotFound {

        ToDoCard toDoCard = toDoRepository.findById(toDoCardUpdateDto.getCardToUpdateId())
                .orElseThrow(() -> new NotFound("Card with: " + toDoCardUpdateDto.getCardToUpdateId() + "not found"));

        toDoCard.setUser(applicationUserRepository
                 .findById(toDoCardUpdateDto.getUserId())
                .orElseThrow(() -> new NotFound("User with: " + toDoCardUpdateDto.getUserId() + "not found"))
        );

        if (toDoCardUpdateDto.getText() != null) {
            toDoCard.setText(toDoCardUpdateDto.getText());
        }
        if (toDoCardUpdateDto.getTitle() != null) {
            toDoCard.setTitle(toDoCardUpdateDto.getTitle());
        }
        if (toDoCardUpdateDto.getState() != null) {
            toDoCard.setState(toDoCardUpdateDto.getState());
        }

        return toDoRepository.save(toDoCard);
    }

    public ToDoCard getToDoCardById(Long id) throws NotFound {
       return toDoRepository.findById(id)
               .orElseThrow(() -> new NotFound("Card with id: " + id + "not found"));
    }

    public void deleteToDoCardById(Long id) throws NotFound {

         toDoRepository.findById(id)
                 .orElseThrow(() -> new NotFound("Card with id: " + id + "not found"));

          toDoRepository.deleteById(id);
    }

    public List<ToDoCard> getAllToDoCardsByUser(Long userId) throws NotFound {

        return toDoRepository.findAllByUser(applicationUserRepository
                    .findById(userId)
                    .orElseThrow(() -> new NotFound("Card with id: " + userId + "not found")));

    }
}
