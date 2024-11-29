package br.com.fiap.soat7.infrastructure.controllers.cliente;

import br.com.fiap.soat7.application.usecases.cliente.AdicionarClienteUsecase;
import br.com.fiap.soat7.application.usecases.cliente.BuscarClientePorCpfUsecase;
import br.com.fiap.soat7.application.usecases.cliente.BuscarTodosClientesUsecase;
import br.com.fiap.soat7.domain.entity.Cliente;
import br.com.fiap.soat7.infrastructure.controllers.cliente.request.ClienteRequest;
import br.com.fiap.soat7.infrastructure.controllers.cliente.response.ClienteResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/clientes")
public class ClienteController {

    private final AdicionarClienteUsecase adicionarClienteUsecase;
    private final BuscarClientePorCpfUsecase buscarClientePorCpfUsecase;
    private final BuscarTodosClientesUsecase buscarTodosClientesUsecase;
    private final ModelMapper modelMapper;

    public ClienteController(AdicionarClienteUsecase adicionarClienteUsecase,
                             BuscarClientePorCpfUsecase buscarClientePorCpfUsecase,
                             BuscarTodosClientesUsecase buscarTodosClientesUsecase, ModelMapper modelMapper) {
        this.adicionarClienteUsecase = adicionarClienteUsecase;
        this.buscarClientePorCpfUsecase = buscarClientePorCpfUsecase;
        this.buscarTodosClientesUsecase = buscarTodosClientesUsecase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> adicionarCliente(@RequestBody ClienteRequest request) {
        Cliente clienteRequest = modelMapper.map(request, Cliente.class);
        ClienteResponse response = modelMapper.map(adicionarClienteUsecase.criarCliente(clienteRequest), ClienteResponse.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = buscarClientePorCpfUsecase.buscarClientePorCpf(cpf);
        return ResponseEntity.ok().body(modelMapper.map(cliente, ClienteResponse.class));
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> clientes = buscarTodosClientesUsecase.buscarTodosClientes();
        return ResponseEntity.ok().body(clientes);
    }

}
