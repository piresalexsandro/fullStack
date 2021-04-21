package br.com.alphapires.fullStack.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Categoria implements Serializable {

//    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;


}
