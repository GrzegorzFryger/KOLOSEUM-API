package workerboard.serivce;




import org.springframework.stereotype.Service;
import workerboard.CurrentBeanAccess;
import workerboard.model.ToDoCard;
import workerboard.model.ToDoCardHistory;
import workerboard.model.enums.ToDoCardState;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static workerboard.model.enums.ToDoCardState.*;


@Service
public class ToDoCardListener {

    @PrePersist
    public void prePersist(ToDoCard target) {

        perform(target, NEW);
    }

    @PreUpdate
    public void preUpdate(ToDoCard target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(ToDoCard target) {
        perform(target, DONE);
    }

    @Transactional(MANDATORY)
    void perform(ToDoCard target, ToDoCardState action) {
        EntityManager entityManager = CurrentBeanAccess.getBean(EntityManager.class);
        entityManager.persist(new ToDoCardHistory(target, action));
    }

}
