package br.com.alphapires.fullStack.services;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.repositories.CategoriaRepository;
import br.com.alphapires.fullStack.sevices.exception.DataIntegrityException;
import br.com.alphapires.fullStack.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria find(Integer id) {
        Optional<Categoria> optinal = repository.findById(id);
        return optinal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Page<Categoria> findPage(Integer pageNumber, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(pageNumber, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }
        public Categoria update(Categoria objetoEnviado) {
        Categoria objetoRecuperado = find(objetoEnviado.getId());
        updateData(objetoRecuperado, objetoEnviado);
        return repository.save(objetoRecuperado);
    }

    private void updateData(Categoria objetoRecuperado, Categoria objetoEnviado) {
        objetoRecuperado.setNome(objetoEnviado.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Nao e possivel excluir uma categoria que possua produtos");
        }
    }
}