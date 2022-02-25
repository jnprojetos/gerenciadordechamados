package br.com.gestordechamados.repository;

import br.com.gestordechamados.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamdoRepository extends JpaRepository<Chamado, Long> {
}
