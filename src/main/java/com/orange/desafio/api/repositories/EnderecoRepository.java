package com.orange.desafio.api.repositories;

import com.orange.desafio.api.models.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
    
}
