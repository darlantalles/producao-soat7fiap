package br.com.fiap.soat7.infrastructure.persistence.entity;

import br.com.fiap.soat7.domain.entity.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceClientId")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String cpf;

    public ClienteEntity(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
    }

    public void atualizar(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
    }

    public Cliente toCliente() {
        return new Cliente(this.id, this.nome, this.cpf, this.email);
    }
}
