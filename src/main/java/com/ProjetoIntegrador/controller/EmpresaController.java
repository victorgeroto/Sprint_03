package com.ProjetoIntegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.entities.Empresa;
import com.ProjetoIntegrador.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name= "Empresa", description = "API REST DE GERENCIAMENTO  DE CADASTRO DE EMPRESAS")
@RestController
@RequestMapping("/empresas") // Changed the path to plural form
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{id}")
	@Operation(summary = "Localiza a Empresa por ID")
    public ResponseEntity<Empresa> buscaEmpresaPorId(@PathVariable Long id) {
    	Empresa empresa = empresaService.buscaEmpresaId(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todas as Empresas")
    public ResponseEntity<List<Empresa>> buscaTodosEmpresas() {
        List<Empresa> empresa = empresaService.buscaTodosEmpresas();
        return ResponseEntity.ok(empresa);
    }

    @PostMapping("/")
    @Operation(summary = "Cadastra uma Empresa")
    public ResponseEntity<Empresa> cadastraEmpresa(@RequestBody @Valid Empresa empresa) {
    	Empresa empresaSalvo = empresaService.salvaEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary= "Altera o cadastro da Empresa")
    public ResponseEntity<Empresa> alteraEmpresa(@PathVariable Long id, @RequestBody @Valid Empresa empresa) {
    	Empresa empresaAlterado = empresaService.alterarEmpresa(id, empresa);
        if (empresaAlterado != null) {
            return ResponseEntity.ok(empresaAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta Empresa")
    public ResponseEntity<Void> apagaEmpresa(@PathVariable Long id) {
        boolean apagou = empresaService.apagarEmpresa(id);
        if (apagou) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

