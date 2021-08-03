package com.orange.desafio.api.services;

import com.orange.desafio.api.models.Endereco;
import com.orange.desafio.api.repositories.EnderecoRepository;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void save(Endereco endereco) {
        this.enderecoRepository.save(endereco);
    }

}
