package com.orange.desafio.api.controllers;

import javax.validation.Valid;

import com.orange.desafio.api.models.Endereco;
import com.orange.desafio.api.models.Usuario;
import com.orange.desafio.api.models.dto.EnderecoDTO;
import com.orange.desafio.api.models.dto.UsuarioDTO;
import com.orange.desafio.api.repositories.EnderecoRepository;
import com.orange.desafio.api.repositories.UsuarioRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private EnderecoRepository enderecoRepository;

    UsuarioController(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionar(@RequestBody @Valid UsuarioDTO usuarioDTO) {             
        try {
            Usuario usuario = usuarioRepository.save(usuarioDTO.converteEmObjeto());
            return new ResponseEntity<>(UsuarioDTO.converteEmDTO(usuario), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException cve) {
            return new ResponseEntity(erroConstraint(cve.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity adicionar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElse(new Usuario());
        if (usuario.getId() == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        Endereco endereco = enderecoDTO.converteEmObjeto();
        endereco.setUsuario(usuario);
        enderecoRepository.save(endereco);
        return new ResponseEntity<>(UsuarioDTO.converteEmDTO(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity exibe(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(new Usuario());
        if (usuario.getId() == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        return ResponseEntity.ok().body(UsuarioDTO.converteEmDTO(usuario));
    }

    private String erroConstraint(String msg) {
        if (msg.contains("(EMAIL)"))
            return "O e-mail informado já pertence a outro usuário!";
        if (msg.contains("(CPF)"))
            return "O CPF informado já pertence a outro usuário!";
        return "Houve um erro ao tentar salvar!";
    }
}