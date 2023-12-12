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

import com.ProjetoIntegrador.entities.Departamento;
import com.ProjetoIntegrador.service.DepartamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name= "Departamento", description = "API REST DE GERENCIAMENTO  DE CADASTRO DE DEPARTAMENTOS")
@RestController
@RequestMapping("/departamentos") // Changed the path to plural form
public class DepartamentoController {
	private final DepartamentoService departamentoService;

	@Autowired
	public DepartamentoController(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza o Departamento por ID")
	public ResponseEntity<Departamento> buscaDepartamentoPorId(@PathVariable Long id) {
		Departamento departamento = departamentoService.buscaDepartamentoId(id);
		if (departamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os Departamento")
	public ResponseEntity<List<Departamento>> buscaTodosDepartamento() {
		List<Departamento> departamento = departamentoService.buscaTodosDepartamentos();
		return ResponseEntity.ok(departamento);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um Departamento")
	public ResponseEntity<Departamento> cadastraDepartamento(@RequestBody @Valid Departamento departamento) {
		Departamento departamentoSalvo = departamentoService.salvaDepartamento(departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoSalvo);
	}

	@PutMapping("/{id}")
	@Operation(summary= "Altera o cadastro do Departamento")
	public ResponseEntity<Departamento> alteraDepartamento(@PathVariable Long id, @RequestBody @Valid Departamento departamento) {
		Departamento departamentoAlterado = departamentoService.alterarDepartamento(id, departamento);
		if (departamentoAlterado != null) {
			return ResponseEntity.ok(departamentoAlterado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation (summary = "Deleta Departamento")
	public ResponseEntity<Void> apagaDepartamento(@PathVariable Long id) {
		boolean apagou = departamentoService.apagarDepartamento(id);
		if (apagou) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}