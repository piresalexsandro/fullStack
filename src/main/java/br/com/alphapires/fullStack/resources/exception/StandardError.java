package br.com.alphapires.fullStack.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

    private Integer status;
    private String message;
    private Long timestamp;

}
