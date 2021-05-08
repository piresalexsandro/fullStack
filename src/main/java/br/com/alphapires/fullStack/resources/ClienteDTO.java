package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

    private Integer id;

    @NotEmpty(message="Preenchimento Obrigatótio")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message="Preenchimento Obrigatótio")
    @Email(message="Email Invalido")
    private String email;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
}
