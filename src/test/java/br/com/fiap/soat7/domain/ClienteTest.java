package br.com.fiap.soat7.domain;

import br.com.fiap.soat7.infrastructure.persistence.entity.ClienteEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testClienteConstructorWithParameters() {
        // Arrange & Act
        Cliente cliente = new Cliente(1L, "João Silva", "12345678900", "joao@example.com");
        
        // Assert
        assertEquals(1L, cliente.getId());
        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        assertEquals("joao@example.com", cliente.getEmail());
    }

    @Test
    public void testClienteConstructorWithEntity() {
        // Arrange
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(2L);
        clienteEntity.setNome("Maria Oliveira");
        clienteEntity.setCpf("98765432100");
        clienteEntity.setEmail("maria@example.com");

        // Act
        Cliente cliente = new Cliente(clienteEntity);
        
        // Assert
        assertEquals(2L, cliente.getId());
        assertEquals("Maria Oliveira", cliente.getNome());
        assertEquals("98765432100", cliente.getCpf());
        assertEquals("maria@example.com", cliente.getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Cliente cliente = new Cliente();

        // Act
        cliente.setId(3L);
        cliente.setNome("Carlos Pereira");
        cliente.setCpf("11223344556");
        cliente.setEmail("carlos@example.com");

        // Assert
        assertEquals(3L, cliente.getId());
        assertEquals("Carlos Pereira", cliente.getNome());
        assertEquals("11223344556", cliente.getCpf());
        assertEquals("carlos@example.com", cliente.getEmail());
    }
}
