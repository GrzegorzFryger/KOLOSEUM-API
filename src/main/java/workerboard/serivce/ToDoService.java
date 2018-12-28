package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.evens.EventProducer;
import workerboard.exception.NotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.ToDoCard;
import workerboard.model.dto.ToDoCardCreateDto;
import workerboard.model.dto.ToDoCardToMoveDto;
import workerboard.model.dto.ToDoCardUpdateDto;
import workerboard.model.enums.ToDoCardState;
import workerboard.repository.ApplicationUserCustomRepository;
import workerboard.repository.ToDoRepository;

import java.util.List;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;
    private ApplicationUserCustomRepository applicationUserRepository;
    private EventProducer eventProducer;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, ApplicationUserCustomRepository applicationUserRepository, EventProducer eventProducer) {
        this.toDoRepository = toDoRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.eventProducer = eventProducer;
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
            if(toDoCardUpdateDto.getState() == ToDoCardState.DONE){
                eventProducer.createToDoNewEvent(toDoCard);
            }
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

    public void moveCardToOtherUser(ToDoCardToMoveDto cardMoveDto) throws NotFound {

        ToDoCard card = this.toDoRepository
                .findById(cardMoveDto.getCardId())
                .orElseThrow(()-> new NotFound("Not found card with : " + cardMoveDto.getCardId()));

        this.applicationUserRepository.findById(cardMoveDto.getIdUserToMove()).ifPresent( x-> {
                        card.setUser(x);
                        this.toDoRepository.save(card);
                }
        );



    }
}
