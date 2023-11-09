package hostfully.technical.interview.exceptions;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String msg) {
        super(msg);
    }

    public EntityNotFound() {
        super("Entity not found!");
    }
}