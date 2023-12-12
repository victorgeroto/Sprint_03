package com.ProjetoIntegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.entities.Cliente;
import com.ProjetoIntegrador.entities.Departamento;
import com.ProjetoIntegrador.entities.Empresa;
import com.ProjetoIntegrador.entities.Fornecedor;
import com.ProjetoIntegrador.entities.Funcionario;
import com.ProjetoIntegrador.entities.Projeto;
import com.ProjetoIntegrador.service.ClienteService;
import com.ProjetoIntegrador.service.DepartamentoService;
import com.ProjetoIntegrador.service.EmpresaService;
import com.ProjetoIntegrador.service.FornecedorService;
import com.ProjetoIntegrador.service.FuncionarioService;
import com.ProjetoIntegrador.service.ProjetoService;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/ApiExterna")
@CrossOrigin(origins="*")
public class ApiExterna {
	private final ClienteService clienteService;
	private final DepartamentoService departamentoService;
	private final EmpresaService empresaService;
	private final FornecedorService fornecedorService;
	private final FuncionarioService funcionarioService;
	private final ProjetoService projetoService;
	
	 @Autowired
	    public ApiExterna(ClienteService clienteService, DepartamentoService departamentoService,EmpresaService empresaService,FornecedorService fornecedorService,FuncionarioService funcionarioService, ProjetoService projetoService) {
	        this.clienteService = clienteService;
	        this.departamentoService = departamentoService;
			this.empresaService = empresaService;
			this.fornecedorService = fornecedorService;
			this.funcionarioService = funcionarioService;
			this.projetoService = projetoService;
	 }
	        
			@GetMapping("/clientes")
		    @Operation(summary = "Apresenta todos os clientes!")
		    public ResponseEntity<List<Cliente>> buscaTodosClientes() {
		        List<Cliente> cliente = clienteService.buscaTodosClientes();
		        return ResponseEntity.ok(cliente);
		    }
			@GetMapping("/departamento")
			@Operation(summary = "Apresenta todos os Departamento")
			public ResponseEntity<List<Departamento>> buscaTodosDepartamento() {
				List<Departamento> departamento = departamentoService.buscaTodosDepartamentos();
				return ResponseEntity.ok(departamento);
			}
			 @GetMapping("/empresa")
			    @Operation(summary = "Apresenta todas as Empresas")
			    public ResponseEntity<List<Empresa>> buscaTodosEmpresas() {
			        List<Empresa> empresa = empresaService.buscaTodosEmpresas();
			        return ResponseEntity.ok(empresa);
			    }
			 @GetMapping("/fornecedores")
			    @Operation(summary = "Apresenta todos os fornecedores!")
			    public ResponseEntity<List<Fornecedor>> buscaTodosFornecedores() {
			        List<Fornecedor> fornecedores = fornecedorService.buscaTodosFornecedores();
			        return ResponseEntity.ok(fornecedores);
			    }
			  @GetMapping("/funcionario")
			    @Operation(summary = "Apresenta todos os Funcionarios")
			    public ResponseEntity<List<Funcionario>> buscaTodosFuncionario() {
			        List<Funcionario> funcionario = funcionarioService.buscaTodosFuncionarios();
			        return ResponseEntity.ok(funcionario);
			    }
			  @GetMapping("/projeto")
			    @Operation(summary = "Apresenta todos os projetos!")
			    public ResponseEntity<List<Projeto>> buscaTodosProjetos() {
			        List<Projeto> projeto = projetoService.buscaTodosProjetos();
			        return ResponseEntity.ok(projeto);
			    }
	    }

	
	
	

