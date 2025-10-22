package com.generation.crm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_oportunidades")
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id autoincrementado
    private Long id;

    @NotBlank(message = "A descrição é obrigatória!")
    private String descricao;

    @NotNull(message = "O valor é obrigatório!")
    private BigDecimal valor;

    @NotNull(message = "A data de criação é obrigatória!")
    private LocalDate dataCriacao;

    // Relacionamento ManyToOne com Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("oportunidades")
    private Cliente cliente;

    // Relacionamento ManyToOne com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("oportunidades")
    private Usuario usuario;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
