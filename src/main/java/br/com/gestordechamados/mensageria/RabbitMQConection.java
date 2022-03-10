package br.com.gestordechamados.mensageria;

import br.com.gestordechamados.mensageria.constants.RabbitConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private static final String NAME_EXCHANGE = "amq.direct" ;

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nameQueue){
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange exchange(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add(){
        Queue filaChamados = this.queue(RabbitConstants.FILA_CHAMADO);
        Queue filaFuncionarios = this.queue(RabbitConstants.FILA_FUNCIONARIO);


        DirectExchange exchange = this.exchange();

        Binding exchangeChamado = this.binding(filaChamados, exchange);
        Binding exchangeFuncionario = this.binding(filaFuncionarios, exchange);


        //Criando as filas
        this.amqpAdmin.declareQueue(filaChamados);
        this.amqpAdmin.declareQueue(filaFuncionarios);
    }
}
