package br.com.alphapires.fullStack.services;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.domain.Produto;
import br.com.alphapires.fullStack.repositories.CategoriaRepository;
import br.com.alphapires.fullStack.repositories.ProdutoRepository;
import br.com.alphapires.fullStack.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> optinal = produtoRepository.findById(id);
        return optinal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer pageNumber, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(pageNumber, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
//        return produtoRepository.search(nome, categorias, pageRequest);
    }
}
