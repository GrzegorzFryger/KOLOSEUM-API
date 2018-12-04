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


    public ToDoCard createToDoCard(ToDoCardCreateDto toDoCardCreateDto) throws  NotFound {
        ToDoCard toDoCard = new ToDoCard();

       ApplicationUser applicationUser = applicationUserRepository
               .findById(toDoCardCreateDto.getUserId()).orElseThrow(() -> new NotFound("not Found"));


       return toDoRepository.save(ToDoCard.createToDoCard(toDoCardCreateDto, applicationUser));


    }

    public List<ToDoCard> getAllToDoCards() {
        return toDoRepository.findAll();
    }

    public ToDoCard updateToDoCard(ToDoCardUpdateDto toDoCardUpdateDto) {
        ToDoCard toDoCard = new ToDoCard();

        Optional<ToDoCard> toDoCardOptional = toDoRepository.findById(toDoCardUpdateDto.getCardToUpdateId());
        if(toDoCardOptional.isPresent()){
            toDoCard = toDoCardOptional.get();

            if(toDoCardUpdateDto.getUserId() != null) {
                Optional<ApplicationUser> applicationUserOptional = applicationUserRepository.findById(toDoCardUpdateDto.getUserId());
                if (applicationUserOptional.isPresent()) {
                    toDoCard.setUser(applicationUserOptional.get());
                } else {
                    ToDoCard card = new ToDoCard();
                    //todo
//                    card.addMessage(new ServiceMessage(ServiceMessageType.ERROR, "User doesn't exist"));
                    return card;
                }
            }

            if(toDoCardUpdateDto.getText() != null) {
                toDoCard.setText(toDoCardUpdateDto.getText());
            }
            if(toDoCardUpdateDto.getTitle() != null) {
                toDoCard.setTitle(toDoCardUpdateDto.getTitle());
            }
            if(toDoCardUpdateDto.getState() != null) {
                toDoCard.setState(toDoCardUpdateDto.getState());
            }
           // toDoCard.setLastModification(LocalDateTime.now());
            toDoRepository.save(toDoCard);
        }else {
            //todo
//            toDoCard.addMessage(new ServiceMessage(ServiceMessageType.ERROR, "Card doesn't exist"));
            return toDoCard;
        }
        return toDoCard;
    }

    public ToDoCard getToDoCardById(Long id) {
        Optional<ToDoCard> toDoCardOptional = toDoRepository.findById(id);
        if(toDoCardOptional.isPresent()){
            return toDoCardOptional.get();
        }
        ToDoCard card = new ToDoCard();
//        card.addMessage(new ServiceMessage(ServiceMessageType.ERROR, "Card doesn't exist"));
        return card;
    }

    public ToDoCard deleteToDoCardById(Long id) {
        ToDoCard card = new ToDoCard();
        Optional<ToDoCard> toDoCardOptional = toDoRepository.findById(id);
        if(toDoCardOptional.isPresent()){
            toDoRepository.deleteById(id);
            //todo
//            card.addMessage(new ServiceMessage(ServiceMessageType.INFO, "Card removed correctly"));
            return card;
        }
        //todo
//        card.addMessage(new ServiceMessage(ServiceMessageType.ERROR, "Card doesn't exist"));
        return card;
    }

    public List<ToDoCard> getAllToDoCardsByUser(Long id) throws NotFound {

        Optional<ApplicationUser> optionlUser = applicationUserRepository.findById(id);
        if(optionlUser.isPresent()) {
            return toDoRepository.findAllByUser(optionlUser.get());
        }

        throw new NotFound("User with ID: " + id + " not found");

    }
}
