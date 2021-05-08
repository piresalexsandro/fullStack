package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.services.ClienteService;
import br.com.alphapires.fullStack.utils.ConvertEntityToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = null;
        cliente = service.find(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> clientes = service.findAll();
        List<ClienteDTO> dtoList =
                clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>>
    findPage(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
             @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
             @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
             @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> clientes = service.findPage(pageNumber, linesPerPage, orderBy, direction);
        Page<ClienteDTO> dtoList = clientes.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto) {

        Cliente cliente = ConvertEntityToDTO.convertClienteToDto(objDto);
        cliente = service.insert(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {

        Cliente cliente = ConvertEntityToDTO.convertClienteToDto(objDto);
        cliente.setId(id);
        cliente = service.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
