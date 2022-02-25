package br.com.gestordechamados.service;

import br.com.gestordechamados.enums.SituacaoChamado;
import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.repository.ChamdoRepository;
import br.com.gestordechamados.repository.ClienteRepository;
import br.com.gestordechamados.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChamadoService {

    private ChamdoRepository chamdoRepository;
    private ClienteRepository clienteRepository;
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Chamado add(Chamado chamado){
        Optional<Cliente> cliente = clienteRepository.findById(chamado.getCliente().getId_cliente());
        Optional<Funcionario> funcionario = funcionarioRepository.findById(chamado.getFuncionario().getId_func());

        chamado.setCliente(cliente.get());
        chamado.setFuncionario(funcionario.get());
        chamado.setSituacao(SituacaoChamado.Pendente);
        chamado.setDataAbertura(LocalDate.now());

        return chamdoRepository.save(chamado);
    }

    public Chamado updateSituation(Chamado chamado){
        chamado.setSituacao(SituacaoChamado.Concluido);
        chamado.setDataFinalizacao(LocalDate.now());
        return chamdoRepository.save(chamado);
    }

    public Chamado findById(Long numeroChamado){
        Optional<Chamado> chamado = chamdoRepository.findById(numeroChamado);
        return chamado.get();
    }

    public List<Chamado> findAll(){
        return chamdoRepository.findAll();
    }

}
