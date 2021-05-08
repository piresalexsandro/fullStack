package br.com.alphapires.fullStack.utils;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.dto.CategoriaDTO;
import br.com.alphapires.fullStack.resources.ClienteDTO;

public class ConvertEntityToDTO {

    public static Categoria convertCategoriaToDto(CategoriaDTO dto){
        return new Categoria(dto.getId(), dto.getNome());

    }
    public static Cliente convertClienteToDto(ClienteDTO dto){
        return new Cliente(dto.getId(), dto.getNome(),  null,  dto.getEmail(),null);
    }
}
