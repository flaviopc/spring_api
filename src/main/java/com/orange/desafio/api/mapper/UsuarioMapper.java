package com.orange.desafio.api.mapper;

import com.orange.desafio.api.models.Usuario;
import com.orange.desafio.api.models.dto.UsuarioDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UsuarioMapper {
    final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDTO usuarioDTO);
}
