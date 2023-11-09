package hostfully.technical.interview.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String msg){
        super(msg);
    }

    public ForbiddenException(){
        super("The user does not have the necessary permissions for this request");
    }
}
