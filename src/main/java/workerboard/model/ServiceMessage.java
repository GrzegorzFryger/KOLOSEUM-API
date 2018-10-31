package workerboard.model;


import workerboard.model.enums.ServiceMessageType;

public class ServiceMessage {

    private ServiceMessageType type;
    private String text;

    public ServiceMessage() {
    }

    public ServiceMessage(ServiceMessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    public ServiceMessageType getType() {
        return type;
    }

    public void setType(ServiceMessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
