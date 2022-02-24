package br.com.gestordechamados.service;

import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElseThrow(()-> new ObjectNotFoundException("Funcionário não encontrado!"));
    }

    @Transactional
    public Funcionario add(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }
}
