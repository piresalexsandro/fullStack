package br.com.alphapires.fullStack.domain;

import br.com.alphapires.fullStack.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PagamentoComBoleto extends Pagamento {

    private Date dataDeVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataDePagamento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataDeVencimento, Date dataDePagamento) {
        super(id, estado, pedido);
        this.dataDeVencimento = dataDeVencimento;
        this.dataDePagamento = dataDePagamento;
    }

}
