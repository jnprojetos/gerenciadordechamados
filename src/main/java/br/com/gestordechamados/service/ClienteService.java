package br.com.gestordechamados.service;

import br.com.gestordechamados.exceptions.ObjectBadRequestException;
import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.repository.ClienteRepository;
import lombok.AllArgsConstructor;
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
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if(emailEmUso){
            throw new ObjectBadRequestException("Já existe um cliente cadastrado com esse e-mail.");
        }
        return clienteRepository.save(cliente);
    }
}
