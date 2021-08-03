package com.orange.desafio.api.models.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    @NotBlank(message = "Por favor, informe o logradouro!")
    private String logradouro;
    @NotBlank(message = "Por favor, informe o número")
    private String numero;
    private String complemento;
    @NotBlank(message = "Por favor, informe o bairro")
    private String bairro;
    @NotBlank(message = "Por favor, informe sua cidade")
    private String cidade;
    @NotBlank(message = "Por favor, informe seu estado ")
    private String estado;
    @NotBlank(message = "Por favor, informe o CEP ")
    private String cep;

}
