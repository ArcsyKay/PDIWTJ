package Lab34;

/**
 * Created by Kisuo on 25/11/2016.
 */
public class MyException extends Exception {
    private Integer errorCode;

    public MyException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
