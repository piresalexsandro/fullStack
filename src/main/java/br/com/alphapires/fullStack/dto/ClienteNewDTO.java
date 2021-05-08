package br.com.alphapires.fullStack.dto;

import br.com.alphapires.fullStack.services.validation.ClienteInsert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    @NotEmpty(message="Preenchimento Obrigatótio")
    private String nome;
    @NotEmpty(message="Preenchimento Obrigatótio")
    private String cpfOuCnpj;
    @NotEmpty(message="Preenchimento Obrigatótio")
    @Email(message="Email Invalido")
    private String email;
    private Integer tipo;

    @NotEmpty(message="Preenchimento Obrigatótio")
    private String logradouro;
    @NotEmpty(message="Preenchimento Obrigatótio")
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty(message="Preenchimento Obrigatótio")
    private String cep;

    @NotEmpty(message="Preenchimento Obrigatótio")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;
}
