package br.com.fiap.soat7.infrastructure.gateways;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.entity.Pedido;
import br.com.fiap.soat7.infrastructure.persistence.PedidoRepository;

import java.util.List;

public class PedidoRepositoryGateway implements PedidoGateway {

    private final PedidoRepository repository;

    public PedidoRepositoryGateway(PedidoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Pedido adicionarPedido(Pedido pedido) {
        return this.repository.save(pedido);
    }

    @Override
    public List<Pedido> buscarPedidos() {
        return this.repository.findAll();
    }

    @Override
    public Pedido buscarPorId(Long id) {
     return this.repository.findById(id);
    }



}
