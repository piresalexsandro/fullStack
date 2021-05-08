package br.com.alphapires.fullStack.services;

import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente find(Integer id) {
        Optional<Cliente> optinal = repository.findById(id);
        return optinal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert(Cliente categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Cliente update(Cliente objetoEnviado) {
        Cliente objetoRecuperado = find(objetoEnviado.getId());
        updateData(objetoRecuperado, objetoEnviado);
        return repository.save(objetoRecuperado);
    }

    private void updateData(Cliente objetoRecuperado, Cliente objetoEnviado) {
        objetoRecuperado.setNome(objetoEnviado.getNome());
        objetoRecuperado.setEmail(objetoEnviado.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao e possivel excluir pois ha entidades relacionadas");
        }
    }

    public Page<Cliente> findPage(Integer pageNumber, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(pageNumber, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
