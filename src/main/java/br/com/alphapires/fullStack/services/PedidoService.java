package br.com.alphapires.fullStack.services;

import br.com.alphapires.fullStack.domain.Pedido;
import br.com.alphapires.fullStack.repositories.PedidoRepository;
import br.com.alphapires.fullStack.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido buscar(Integer id) {
        Optional<Pedido> optinal = repository.findById(id);
        return optinal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
