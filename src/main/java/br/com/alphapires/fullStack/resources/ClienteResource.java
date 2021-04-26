package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.services.CategoriaService;
import br.com.alphapires.fullStack.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Cliente cliente = null;

        cliente = service.buscar(id);
        return ResponseEntity.ok(cliente);
    }

}
