package br.com.alphapires.fullStack.sevices.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String msg) {
        super(msg);
    }


    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
