package com.demo.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.Cargo;
import com.demo.domain.Funcionario;
import com.demo.domain.UF;
import com.demo.service.CargoService;
import com.demo.service.FuncionarioService;
import com.demo.validator.FuncionarioValidator;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}
	
	@GetMapping("cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	@GetMapping("listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "funcionario/lista";
	}
	
	@PostMapping("salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors())
			return "funcionario/cadastro";
		
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionario inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "funcionario/cadastro";
	}
	
	@PostMapping("editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors())
			return "funcionario/cadastro";
		
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionario editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> cargos() {
		return cargoService.buscarTodos();
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model, RedirectAttributes attr) {
		List<Funcionario> funcionarios = funcionarioService.buscarPorNome(nome);
		if(!funcionarios.isEmpty()) {
			model.addAttribute("funcionarios", funcionarios);
			return "funcionario/lista";
		} else {
			attr.addFlashAttribute("fail", "Registro não encontrado.");
			return "redirect:/funcionarios/listar";
		}
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attr) {
		List<Funcionario> funcionarios = funcionarioService.buscarPorCargo(id);
		if(!funcionarios.isEmpty()) {
			model.addAttribute("funcionarios", funcionarios);
			return "funcionario/lista";
		} else {
			attr.addFlashAttribute("fail", "Registro não encontrado.");
			return "redirect:/funcionarios/listar";
		}
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("dataEntrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrada, 
			@RequestParam("dataSaida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSaida, 
			ModelMap model, RedirectAttributes attr) {
		List<Funcionario> funcionarios = funcionarioService.buscarPorDatas(dataEntrada, dataSaida);
		if(!funcionarios.isEmpty()) {
			model.addAttribute("funcionarios", funcionarios);
			return "funcionario/lista";
		} else {
			attr.addFlashAttribute("fail", "Registro não encontrado.");
			return "redirect:/funcionarios/listar";
		}
	}
	
	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("success", "Funcionario excluído com sucesso.");
		return "redirect:/funcionarios/listar";
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
}
