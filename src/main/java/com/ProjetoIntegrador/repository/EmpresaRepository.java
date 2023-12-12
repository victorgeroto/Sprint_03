package com.ProjetoIntegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoIntegrador.entities.Empresa;

public interface EmpresaRepository extends JpaRepository <Empresa, Long>{

}
