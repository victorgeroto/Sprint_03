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

import com.ProjetoIntegrador.entities.Funcionario;
import com.ProjetoIntegrador.service.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name= "Funcionario", description = "API REST DE GERENCIAMENTO  DE CADASTRO DE FUNCIONARIOS")
@RestController
@RequestMapping("/Funcionarios") // Changed the path to plural form
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{id}")
	@Operation(summary = "Localiza o Funcionario por ID")
    public ResponseEntity<Funcionario> buscaFuncionarioPorId(@PathVariable Long id) {
    	Funcionario funcionario = funcionarioService.buscaFuncionarioId(id);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os Funcionarios")
    public ResponseEntity<List<Funcionario>> buscaTodosFuncionario() {
        List<Funcionario> funcionario = funcionarioService.buscaTodosFuncionarios();
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping("/")
    @Operation(summary = "Cadastra um Funcionario")
    public ResponseEntity<Funcionario> cadastraFuncionario(@RequestBody @Valid Funcionario funcionario) {
    	Funcionario funcionarioSalvo = funcionarioService.salvaFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary= "Altera o cadastro do Funcionario")
    public ResponseEntity<Funcionario> alteraFuncionario(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
    	Funcionario funcionarioAlterado = funcionarioService.alterarFuncionario(id, funcionario);
        if (funcionarioAlterado != null) {
            return ResponseEntity.ok(funcionarioAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta Funcionario")
    public ResponseEntity<Void> apagaFuncionario(@PathVariable Long id) {
        boolean apagou = funcionarioService.apagarFuncionario(id);
        if (apagou) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

