package com.orange.desafio.api.controllers;

import javax.validation.Valid;

import com.orange.desafio.api.mapper.EnderecoMapper;
import com.orange.desafio.api.mapper.UsuarioMapper;
import com.orange.desafio.api.models.Endereco;
import com.orange.desafio.api.models.Usuario;
import com.orange.desafio.api.models.dto.EnderecoDTO;
import com.orange.desafio.api.models.dto.UsuarioDTO;

import com.orange.desafio.api.services.EnderecoService;
import com.orange.desafio.api.services.UsuarioService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private UsuarioService usuarioService;
    private EnderecoService enderecoService;

    UsuarioController(UsuarioService usuarioService, EnderecoService enderecoService) {
        this.usuarioService = usuarioService;
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.save(UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDTO));
            return new ResponseEntity<>(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException cve) {
            return new ResponseEntity(usuarioService.erroConstraint(cve.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity adicionar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario.getId() == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usu??rio n??o encontrado!");
        Endereco endereco = EnderecoMapper.INSTANCE.enderecoDtoToEndereco(enderecoDTO);
        endereco.setUsuario(usuario);
        enderecoService.save(endereco);
        return new ResponseEntity<>(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity exibe(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario.getId() == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usu??rio n??o encontrado! Tente novamente");
        return ResponseEntity.ok().body(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario));
    }

}