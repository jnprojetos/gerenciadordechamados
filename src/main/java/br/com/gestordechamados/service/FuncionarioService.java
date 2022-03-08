package br.com.gestordechamados.service;

import br.com.gestordechamados.exceptions.ObjectBadRequestException;
import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Funcionário não encontrado!"));
    }

    @Transactional
    public Funcionario add(Funcionario funcionario){
        boolean emailEmUso = funcionarioRepository.findByEmail(funcionario.getEmail()).stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(funcionario));
        if(emailEmUso){
            throw new ObjectBadRequestException("Já existe um funcionário cadastrado com esse e-mail.");
        }
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario update(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Funcionario funcionario){
        funcionarioRepository.delete(funcionario);
    }
}
