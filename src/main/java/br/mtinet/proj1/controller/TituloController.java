package br.mtinet.proj1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.mtinet.proj1.model.StatusTitulo;
import br.mtinet.proj1.model.Titulo;
import br.mtinet.proj1.repository.Titulos;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos;
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	private static final String LISTA_VIEW = "ListaTitulos";
	
	@ModelAttribute
	public List<StatusTitulo> listaTodosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
	@RequestMapping()
	public ModelAndView pesquisar() {
		ModelAndView result = new ModelAndView(LISTA_VIEW);
		List<Titulo> listaTitulos = this.titulos.findAll();
		result.addObject("titulos",listaTitulos);
		return result;
	}
	
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView result = new ModelAndView(CADASTRO_VIEW);
		Optional titulo =   this.titulos.findById(id);
		result.addObject("titulo",titulo);
		return result;
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView result = new ModelAndView(LISTA_VIEW);
		this.titulos.deleteById(id);
		result.addObject("mensagem","Titulo excluido com sucesso!");
		return result;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView result = new ModelAndView(CADASTRO_VIEW);
		result.addObject(new Titulo());
		return result;
	}
	
	@RequestMapping( method=RequestMethod.POST )
	public ModelAndView salvar(@Valid Titulo titulo, BindingResult results, Model model) {
		
		if (results.hasErrors()) {
			System.out.println("ERROR: " + results.toString());
			ModelAndView result = new ModelAndView(CADASTRO_VIEW);
			result.addObject("mensagem","Erro ao salvar o Titulo!");
			return result;
		}
		
		ModelAndView result = new ModelAndView(LISTA_VIEW);
		
		try {
			titulos.save(titulo);
			result.addObject("mensagem","Titulo salvo com sucesso!");
			List<Titulo> listaTitulos = this.titulos.findAll();
			result.addObject("titulos",listaTitulos);
//			attributes.addFlashAttribute(mensagem","Titulo salvo com sucesso!")
			titulo.clean();
		} catch (Exception e) {
			result.addObject("mensagem","Erro ao salvar o Titulo!!!");
		}
		return result;
	}
}
