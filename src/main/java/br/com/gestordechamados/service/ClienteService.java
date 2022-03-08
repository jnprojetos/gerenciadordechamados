package br.com.gestordechamados.service;

import br.com.gestordechamados.exceptions.ObjectBadRequestException;
import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;


    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado!"));
    }


    @Transactional
    public Cliente add(Cliente cliente){
        boolean clienteJaCadastrado = clienteRepository.findByEmailOrCpf(cliente.getEmail(), cliente.getCpf()).stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if(clienteJaCadastrado){
            throw new ObjectBadRequestException("Já existe um cliente cadastrado com essas informações");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente){
       return clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente){
        clienteRepository.delete(cliente);
    }
}
