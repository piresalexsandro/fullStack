package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.dto.CategoriaDTO;
import br.com.alphapires.fullStack.services.CategoriaService;
import br.com.alphapires.fullStack.utils.ConvertDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO> > findAll() {
        List<Categoria> categorias = service.findAll();
        List<CategoriaDTO> dtoList =
                categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>>
        findPage(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                 @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                 @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Categoria> categorias = service.findPage(pageNumber,linesPerPage,orderBy, direction);
        Page<CategoriaDTO> dtoList = categorias.map(obj -> new CategoriaDTO(obj));

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria categoria = null;
        categoria = service.find(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){

        Categoria categoria = ConvertDomain.convertCategoriaToDto(objDto);
        categoria = service.insert(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){

        Categoria categoria = ConvertDomain.convertCategoriaToDto(objDto);
        categoria.setId(id);
        categoria = service.update(categoria);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
