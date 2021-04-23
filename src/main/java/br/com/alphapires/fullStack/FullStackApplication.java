package br.com.alphapires.fullStack;

import br.com.alphapires.fullStack.domain.Categoria;
import br.com.alphapires.fullStack.domain.Cidade;
import br.com.alphapires.fullStack.domain.Estado;
import br.com.alphapires.fullStack.domain.Produto;
import br.com.alphapires.fullStack.repositories.CategoriaRepository;
import br.com.alphapires.fullStack.repositories.CidadeRepository;
import br.com.alphapires.fullStack.repositories.EstadoRepository;
import br.com.alphapires.fullStack.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FullStackApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(FullStackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

//		associando o produto a categoria
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

//		associando a categoria ao produto
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlandia", est1);
		Cidade cidade2 = new Cidade(null, "São Paulo", est2);
		Cidade cidade3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().add(cidade1);
		est2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

	}
}
