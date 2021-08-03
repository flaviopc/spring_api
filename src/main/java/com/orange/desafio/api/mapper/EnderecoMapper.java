package com.orange.desafio.api.mapper;

import com.orange.desafio.api.models.Endereco;
import com.orange.desafio.api.models.dto.EnderecoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface EnderecoMapper {
    final EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);
    EnderecoDTO enderecoToEnderecoDTO(Endereco endereco);
    Endereco enderecoDtoToEndereco(EnderecoDTO enderecoDTO);
}
