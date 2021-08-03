package com.orange.desafio.api.models.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

}
