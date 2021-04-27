package br.com.alphapires.fullStack.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    PARCELADO(3, "Parcelado");

    private Integer codigo;
    private String descricao;


    public static EstadoPagamento toEnum(Integer codigo){
        if (!Objects.nonNull(codigo)){
            return null;
        }

        for (EstadoPagamento estado : EstadoPagamento.values()){
            if(codigo.equals(estado.getCodigo())){
                return estado;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + codigo);
    }
}
