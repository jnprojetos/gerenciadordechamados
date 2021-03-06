package br.com.gestordechamados.repository;

import br.com.gestordechamados.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmailOrCpf(String email, String cpf);
}
