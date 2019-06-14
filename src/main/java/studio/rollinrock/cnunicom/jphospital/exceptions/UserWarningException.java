package studio.rollinrock.cnunicom.jphospital.exceptions;

public class UserWarningException extends RuntimeException {

    protected String code;
    protected String desc;


    public UserWarningException(String code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }
    public UserWarningException(String code, String desc, Throwable cause) {
        super(desc, cause);
        this.code = code;
        this.desc = desc;
    }
}
