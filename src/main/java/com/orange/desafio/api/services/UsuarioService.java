package com.orange.desafio.api.services;

import com.orange.desafio.api.models.Usuario;
import com.orange.desafio.api.repositories.UsuarioRepository;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElse(new Usuario());
    }

    public String erroConstraint(String msg) {
        if (msg.contains("(EMAIL)"))
            return "O e-mail informado já pertence a outro usuário!";
        if (msg.contains("(CPF)"))
            return "O CPF informado já pertence a outro usuário!";
        return "Houve um erro ao tentar salvar!";
    }
}
