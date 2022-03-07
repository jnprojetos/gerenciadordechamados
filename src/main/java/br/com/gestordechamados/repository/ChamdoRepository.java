package br.com.gestordechamados.repository;

import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChamdoRepository extends JpaRepository<Chamado, Long> {
    Optional<Chamado> findByCliente(Cliente cliente);
    Optional<Chamado> findByFuncionario(Funcionario funcionario);
}
