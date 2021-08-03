package com.orange.desafio.api.models.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.orange.desafio.api.models.Usuario;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    @NotBlank(message = "Você precisa preencher o nome!")
    private String nome;
    @NotBlank(message = "Você precisa fornecer um e-mail!")
    @Email(message = "O e-mail informado é inválido!")
    private String email;       
    @CPF(message = "O CPF informado é inválido")
    private String cpf;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    private List<EnderecoDTO> enderecos;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
        this.dataNascimento = usuario.getDataNascimento();
        this.enderecos = usuario.getEnderecos().stream().map(EnderecoDTO::converteEmDTO).collect(Collectors.toList());
    }

    public static UsuarioDTO converteEmDTO(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }

    public Usuario converteEmObjeto() {
        return new Usuario(id, nome, email, cpf, dataNascimento,
                Optional.ofNullable(enderecos).orElseGet(Collections::emptyList).stream().map(v -> v.converteEmObjeto())
                        .collect(Collectors.toList()));
    }

}
