package exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class DDLException extends RuntimeException{
    private String msg;
    public DDLException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
