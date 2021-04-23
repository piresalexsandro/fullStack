package br.com.alphapires.fullStack.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer codigo;
    private String descricao;


    private TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoCliente toEnum(Integer codigo){
        if (!Objects.nonNull(codigo)){
            return null;
        }

        for (TipoCliente tipoCliente : TipoCliente.values()){
            if(codigo.equals(tipoCliente.getCodigo())){
                return tipoCliente;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + codigo);
    }
}
