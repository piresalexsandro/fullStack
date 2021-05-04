package br.com.alphapires.fullStack.utils;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.dto.CategoriaDTO;

public class ConvertDomain {

    public static Categoria convertCategoriaToDto(CategoriaDTO dto){
        return new Categoria(dto.getId(), dto.getNome());

    }
}
