package br.com.alphapires.fullStack.dto;

import br.com.alphapires.fullStack.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private double preco;

    public ProdutoDTO(Produto obj){
        id= obj.getId();
        nome= obj.getNome();
        preco= obj.getPreco();
    }

}
