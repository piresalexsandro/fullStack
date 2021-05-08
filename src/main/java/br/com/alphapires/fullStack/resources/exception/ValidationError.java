package br.com.alphapires.fullStack.resources.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationError extends StandardError{

    List<FieldMessege> errors =  new ArrayList<>();

    public ValidationError(Integer status, String message, Long timestamp) {
        super(status, message, timestamp);
    }

    public void addError(String fieldName, String mensagem){
        errors.add(new FieldMessege(fieldName, mensagem));
    }
}
