package com.generation.crm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank(message = "O atributo nome é obrigatório!")
    private String nome;

    @Schema(example = "contato@crmfy.com.br")
    @Column(length = 100)
    @NotBlank(message = "O atributo usuário é obrigatório!")
    @Email(message = "O atributo usuário deve ser um email válido!")
    private String usuario;

    @Column(length = 1000)
    @NotBlank(message = "O atributo foto é obrigatório!")
    private String foto;

    @Column(length = 100)
    @NotBlank(message = "O atributo senha é obrigatório!")
    private String senha;

    @Column(length = 100)
    @NotBlank(message = "O atributo cargo é obrigatório!")
    private String cargo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Oportunidade> oportunidades;

    // GETTERS/SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public List<Oportunidade> getOportunidades() { return oportunidades; }
    public void setOportunidades(List<Oportunidade> oportunidades) { this.oportunidades = oportunidades; }
}

