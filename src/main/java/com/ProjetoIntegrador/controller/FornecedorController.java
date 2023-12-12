package com.ProjetoIntegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ProjetoIntegrador.entities.Fornecedor;
import com.ProjetoIntegrador.service.FornecedorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fornecedores") // Changed the path to plural form
public class FornecedorController {
    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza o fornecedor por ID")
    public ResponseEntity<Fornecedor> buscaFornecedorPorId(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.buscaFornecedorId(id);
        if (fornecedor != null) {
            return ResponseEntity.ok(fornecedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os fornecedores!")
    public ResponseEntity<List<Fornecedor>> buscaTodosFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.buscaTodosFornecedores();
        return ResponseEntity.ok(fornecedores);
    }

    @PostMapping("/")
    @Operation(summary = "Cadastra um fornecedor")
    public ResponseEntity<Fornecedor> cadastraFornecedor(@RequestBody @Valid Fornecedor fornecedor) {
        Fornecedor fornecedorSalvo = fornecedorService.salvaFornecedor(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary= "Altera o cadastro do fornecedor")
    public ResponseEntity<Fornecedor> alteraFornecedor(@PathVariable Long id, @RequestBody @Valid Fornecedor fornecedor) {
        Fornecedor fornecedorAlterado = fornecedorService.alterarFornecedor(id, fornecedor);
        if (fornecedorAlterado != null) {
            return ResponseEntity.ok(fornecedorAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta fornecedor")
    public ResponseEntity<Void> apagaFornecedor(@PathVariable Long id) {
        boolean apagou = fornecedorService.apagarFornecedor(id);
        if (apagou) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
