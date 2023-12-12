package com.ProjetoIntegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoIntegrador.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long>{

}