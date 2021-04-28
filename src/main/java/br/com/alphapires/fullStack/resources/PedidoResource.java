package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.domain.Pedido;
import br.com.alphapires.fullStack.repositories.PedidoRepository;
import br.com.alphapires.fullStack.services.ClienteService;
import br.com.alphapires.fullStack.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Pedido pedido = null;

        pedido = service.buscar(id);
        return ResponseEntity.ok(pedido);
    }

}
