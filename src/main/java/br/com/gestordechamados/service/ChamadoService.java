package br.com.gestordechamados.service;

import br.com.gestordechamados.dto.ChamadoDTO;
import br.com.gestordechamados.enums.SituacaoChamado;
import br.com.gestordechamados.exceptions.ObjectBadRequestException;
import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.repository.ChamdoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class ChamadoService {

    private ChamdoRepository chamdoRepository;
    private ClienteService clienteService;
    private FuncionarioService funcionarioService;

    @Transactional
    public Chamado add(Chamado chamado){
        Cliente cliente = clienteService.findById(chamado.getCliente().getId());
        Funcionario funcionario = funcionarioService.findById(chamado.getFuncionario().getId());

        chamado.setCliente(cliente);
        chamado.setFuncionario(funcionario);
        chamado.setSituacao(SituacaoChamado.Pendente);
        chamado.setDataAbertura(LocalDate.now());

        return chamdoRepository.save(chamado);
    }

    @Transactional
    public Chamado finalize(ChamadoDTO chamadoDTO){
        Chamado chamado = new Chamado();
        chamado = chamdoRepository.findById(chamadoDTO.getNumeroChamado()).get();
        if (chamado.getSituacao().equals(SituacaoChamado.Pendente)){
            if (chamadoDTO.getSituacao().equals(SituacaoChamado.Concluido)){
                chamado.setSituacao(SituacaoChamado.Concluido);
            }
            else {
                chamado.setSituacao(SituacaoChamado.Cancelado);
            }
            chamado.setDataFinalizacao(LocalDate.now());
        }
        else{
            throw new ObjectBadRequestException("Chamado ja finalizado");
        }
        return chamdoRepository.save(chamado);
    }

    public Chamado findById(Long numeroChamado){
        return chamdoRepository.findById(numeroChamado).orElseThrow(()-> new ObjectNotFoundException("Chamado não encontrado!"));
    }

    public List<Chamado> findAll(){
        return chamdoRepository.findAll();
    }

    public Chamado findByCliente(Cliente cliente){
        return chamdoRepository.findByCliente(cliente).get();
    }

    public Chamado findByFuncionario(Funcionario funcionario){
        return chamdoRepository.findByFuncionario(funcionario).get();
    }

}
