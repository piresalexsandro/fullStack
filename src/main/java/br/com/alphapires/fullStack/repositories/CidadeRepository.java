package br.com.alphapires.fullStack.repositories;

import br.com.alphapires.fullStack.domain.Cidade;
import br.com.alphapires.fullStack.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
