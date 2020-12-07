package com.demo.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.Cargo;
import com.demo.domain.Departamento;
import com.demo.service.CargoService;
import com.demo.service.DepartamentoService;
import com.demo.util.PaginacaoUtil;

import java.util.Optional;

@Controller
@RequestMapping("cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}
	
	@GetMapping("listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("dir") Optional<String> dir) {
		
		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("ASC");
		PaginacaoUtil<Cargo> pageCargo = cargoService.buscaPorPagina(paginaAtual, ordem);
		
		model.addAttribute("pageCargo", pageCargo);
		return "cargo/lista";
	}
	
	@PostMapping("salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors())
			return "cargo/cadastro";
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamentos() {
		return departamentoService.buscarTodos();
	}
	
	@GetMapping("editar/{id}")
	public String preEdicao(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}
	
	@PostMapping("editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors())
			return "cargo/cadastro";
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo editado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if(!cargoService.cargoTemFuncionario(id)) {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		} else
			attr.addFlashAttribute("fail", "O cargo não excluído. Tem funcionario(s) vinculado(s).");
		
		return "redirect:/cargos/listar";
	}
}
