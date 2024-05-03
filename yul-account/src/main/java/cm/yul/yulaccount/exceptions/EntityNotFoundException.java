package cm.yul.yulaccount.exceptions;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String msg)
    {
        super(msg);
    }
}
