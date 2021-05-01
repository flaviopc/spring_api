package com.orange.desafio.api.models.dto;

import javax.validation.constraints.NotBlank;

import com.orange.desafio.api.models.Endereco;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public EnderecoDTO(Endereco endereco){  
        this.id = endereco.getId();  
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.cep = endereco.getCep();
        
    }

    public static EnderecoDTO converteEmDTO(Endereco endereco){
        return new EnderecoDTO(endereco);
    }

    public Endereco converteEmObjeto(){
        return new Endereco(id,logradouro,numero, complemento, bairro,cidade,estado,cep,null);
    }
}
