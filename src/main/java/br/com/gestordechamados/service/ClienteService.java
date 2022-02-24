package br.com.gestordechamados.service;

import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado!"));
    }

    @Transactional
    public Cliente add(Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
