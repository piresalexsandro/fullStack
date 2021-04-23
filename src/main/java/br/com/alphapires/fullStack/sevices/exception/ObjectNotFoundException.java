package br.com.alphapires.fullStack.sevices.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String msg) {
        super(msg);
    }


    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
