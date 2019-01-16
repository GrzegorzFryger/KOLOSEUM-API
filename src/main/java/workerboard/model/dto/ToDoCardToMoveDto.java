package workerboard.model.dto;

public class ToDoCardToMoveDto {

    private Long cardId;
    private Long idUserToMove;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getIdUserToMove() {
        return idUserToMove;
    }

    public void setIdUserToMove(Long idUserToMove) {
        this.idUserToMove = idUserToMove;
    }
}
