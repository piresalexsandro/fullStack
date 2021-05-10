package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.domain.Produto;
import br.com.alphapires.fullStack.domain.Produto;
import br.com.alphapires.fullStack.dto.ProdutoDTO;
import br.com.alphapires.fullStack.resources.utils.URL;
import br.com.alphapires.fullStack.services.ProdutoService;
import br.com.alphapires.fullStack.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {

        Produto pedido = null;

        pedido = service.find(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>>

    findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
             @RequestParam(value = "categorias", defaultValue = "") String categorias,
             @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
             @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
             @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
             @RequestParam(value = "direction", defaultValue = "ASC") String direction) throws UnsupportedEncodingException {

        Page<Produto> produtos = service.search(URL.decodeParam(nome), URL.decodeIntList(categorias), pageNumber, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> dtoList = produtos.map(obj -> new ProdutoDTO(obj));

        return ResponseEntity.ok(dtoList);
    }

}
