package br.com.gestordechamados.converter;

import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.model.input.ChamadoInput;
import br.com.gestordechamados.dto.ClienteDTO;
import br.com.gestordechamados.dto.FuncionarioDTO;
import br.com.gestordechamados.dto.ChamadoDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ConverterClasses {

    private ModelMapper modelMapper;


    //Converte ClienteDTO em Cliente
    public Cliente toModelCliente(ClienteDTO clienteDTO){
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    //Converter um Cliente em ClienteDTO
    public ClienteDTO toModelClienteDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    // Converter um lista de Cliente em uma ClienteDTO
    public List<ClienteDTO> toCollectionModelClienteDTO(List<Cliente> clientes){
        return clientes.stream().map(this::toModelClienteDTO).collect(Collectors.toList());
    }

    // Converte um FuncionarioDTO em um Funcionario
    public Funcionario toModelFuncionarioDTO(FuncionarioDTO funcionarioDTO){
        return modelMapper.map(funcionarioDTO, Funcionario.class);
    }

    // Converter um Funcionario em FuncionarioDTO
    public FuncionarioDTO toModelFuncionarioDTO(Funcionario funcionario){
        return modelMapper.map(funcionario, FuncionarioDTO.class);
    }

    // Converte uma lista de Funcionario em uma de FuncionarioDTO
    public List<FuncionarioDTO> toCollectionModelFuncionarioDTO(List<Funcionario> funcionarios){
        return funcionarios.stream().map(this::toModelFuncionarioDTO).collect(Collectors.toList());
    }

    // Converte um ChamadoInput em um Chamado
    public Chamado toModelChamado(ChamadoInput chamadoInput){
        return modelMapper.map(chamadoInput, Chamado.class);
    }

    // Converte um Chamado em um ChamaDTO
    public ChamadoDTO toModelChamadoDTO(Chamado chamado){
        return modelMapper.map(chamado, ChamadoDTO.class);
    }

    // Converte uma lista de Chamado em uma de ChamadoDTO
    public List<ChamadoDTO> toCollectionModelChamadoDTO(List<Chamado> chamados){
        return chamados.stream().map(this::toModelChamadoDTO).collect(Collectors.toList());
    }
}
