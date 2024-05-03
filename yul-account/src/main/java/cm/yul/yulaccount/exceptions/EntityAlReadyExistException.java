package cm.yul.yulaccount.exceptions;

public class EntityAlReadyExistException extends RuntimeException{
    
    public EntityAlReadyExistException(String msg)
    {
        super(msg);
    }
}
