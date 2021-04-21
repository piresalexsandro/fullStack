package br.com.alphapires.fullStack.resources;

import br.com.alphapires.fullStack.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria>  listar(){

        Categoria c1 = new Categoria(1, "Software");
        Categoria c2 = new Categoria(2, "Artificial Intelligence");

        List<Categoria> list = new ArrayList<>();

        list.add(c1);
        list.add(c2);

        return list;
    }

}
