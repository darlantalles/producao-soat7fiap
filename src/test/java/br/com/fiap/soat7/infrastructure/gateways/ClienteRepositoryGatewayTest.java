package br.com.fiap.soat7.infrastructure.gateways;

import br.com.fiap.soat7.domain.Cliente;
import br.com.fiap.soat7.infrastructure.persistence.IClienteRepository;
import br.com.fiap.soat7.infrastructure.persistence.entity.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteRepositoryGatewayTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteRepositoryGateway clienteGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCliente_NewCliente() {
        // Arrange
        Cliente cliente = new Cliente(null, "João Silva", "12345678900", "joao@example.com");
        ClienteEntity clienteEntity = new ClienteEntity(cliente);
        ClienteEntity savedEntity = new ClienteEntity();
        savedEntity.setId(1L);

        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(savedEntity);

        // Act
        Cliente result = clienteGateway.createCliente(cliente);

        // Assert
        assertEquals(1L, result.getId());
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void testCreateCliente_ExistingCliente() {
        // Arrange
        Cliente cliente = new Cliente(1L, "Maria Oliveira", "98765432100", "maria@example.com");
        ClienteEntity existingEntity = new ClienteEntity();
        existingEntity.setId(1L);
        existingEntity.setCpf("98765432100");

        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(existingEntity));
        when(clienteRepository.save(existingEntity)).thenReturn(existingEntity);

        // Act
        Cliente result = clienteGateway.createCliente(cliente);

        // Assert
        assertEquals(1L, result.getId());
        verify(clienteRepository, times(1)).findByCpf(cliente.getCpf());
        verify(clienteRepository, times(1)).save(existingEntity);
    }

    @Test
    void testFindByCpf_ClienteFound() {
        // Arrange
        String cpf = "12345678900";
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setCpf(cpf);
        clienteEntity.setNome("João Silva");

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteEntity));

        // Act
        Cliente result = clienteGateway.findByCpf(cpf);

        // Assert
        assertEquals("João Silva", result.getNome());
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void testFindByCpf_ClienteNotFound() {
        // Arrange
        String cpf = "00000000000";
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> clienteGateway.findByCpf(cpf));
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void testFindAll() {
        // Arrange
        ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setId(1L);
        clienteEntity1.setNome("João Silva");

        ClienteEntity clienteEntity2 = new ClienteEntity();
        clienteEntity2.setId(2L);
        clienteEntity2.setNome("Maria Oliveira");

        when(clienteRepository.findAll()).thenReturn(List.of(clienteEntity1, clienteEntity2));

        // Act
        List<Cliente> result = clienteGateway.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
    }
}
