package br.com.alphapires.fullStack.services;

import br.com.alphapires.fullStack.domain.Cidade;
import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.domain.Endereco;
import br.com.alphapires.fullStack.domain.enums.TipoCliente;
import br.com.alphapires.fullStack.dto.ClienteNewDTO;
import br.com.alphapires.fullStack.repositories.ClienteRepository;
import br.com.alphapires.fullStack.repositories.EnderecoRepository;
import br.com.alphapires.fullStack.resources.ClienteDTO;
import br.com.alphapires.fullStack.sevices.exception.DataIntegrityException;
import br.com.alphapires.fullStack.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente find(Integer id) {
        Optional<Cliente> optinal = repository.findById(id);
        return optinal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = repository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
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
            throw new DataIntegrityException("Nao e possivel excluir pois ha pedidos relacionadas");
        }
    }

    public Page<Cliente> findPage(Integer pageNumber, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(pageNumber, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente convertClienteToDto(ClienteDTO dto){
        return new Cliente(dto.getId(), dto.getNome(),  null,  dto.getEmail(),null);
    }

    public Cliente convertClienteToDto(ClienteNewDTO dto){
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getCpfOuCnpj(), dto.getEmail(), TipoCliente.toEnum(dto.getTipo()));
        Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(dto.getTelefone1());

        if (Objects.nonNull(dto.getTelefone2())) {
            cliente.getTelefones().add(dto.getTelefone2());
        }

        if (Objects.nonNull(dto.getTelefone3())) {
            cliente.getTelefones().add(dto.getTelefone3());
        }
        return cliente;
    }

}
