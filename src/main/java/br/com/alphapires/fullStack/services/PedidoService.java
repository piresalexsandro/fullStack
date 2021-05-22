package br.com.alphapires.fullStack.services;

import br.com.alphapires.fullStack.domain.ItemPedido;
import br.com.alphapires.fullStack.domain.PagamentoComBoleto;
import br.com.alphapires.fullStack.domain.Pedido;
import br.com.alphapires.fullStack.domain.Produto;
import br.com.alphapires.fullStack.domain.enums.EstadoPagamento;
import br.com.alphapires.fullStack.repositories.ItemPedidoRepository;
import br.com.alphapires.fullStack.repositories.PagamentoRepository;
import br.com.alphapires.fullStack.repositories.PedidoRepository;
import br.com.alphapires.fullStack.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public Pedido find(Integer id) {
        Optional<Pedido> optinal = repository.findById(id);
        return optinal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
//    public Pedido insert(Pedido pedido) {
//        pedido.setId(null);
//        pedido.setInstante(new Date());
//        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
//        pedido.getPagamento().setPedido(pedido);
//
//        if(pedido.getPagamento() instanceof PagamentoComBoleto){
//            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
//            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, pedido.getInstante());
//        }
//
//        repository.save(pedido);
//        pagamentoRepository.save(pedido.getPagamento());
//
//        for (ItemPedido item : pedido.getItens()){
//
////            Integer idProduto = item.getProduto().getId();
////            Produto produto = produtoService.find(idProduto);
////            item.setProduto(produto);
//
//            item.setDesconto(0.0);
//            item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
//            item.setPedido(pedido);
//        }
//        itemPedidoRepository.saveAll(pedido.getItens());
//
//        return pedido;
//    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
}
