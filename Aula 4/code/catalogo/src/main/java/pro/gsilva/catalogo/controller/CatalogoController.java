package pro.gsilva.catalogo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pro.gsilva.catalogo.model.Categoria;
import pro.gsilva.catalogo.model.Musica;
import pro.gsilva.catalogo.service.CatalogoService;
import pro.gsilva.catalogo.service.CategoriaService;

@Controller
public class CatalogoController {

	@Autowired
	private CatalogoService catalogoService;

	@Autowired
	private CategoriaService categoriaService;

	private List<Categoria> categorias = null;

	@PostConstruct
	public void setUp() {
		categorias = categoriaService.findAll();
	}

	@RequestMapping(value = "/musicas", method = RequestMethod.GET)
	public ModelAndView getMusicas() {
		ModelAndView mv = new ModelAndView("musicas");
		List<Musica> musicas = catalogoService.findAll();
		mv.addObject("musicas", musicas);

		mv.addObject("categorias", categorias);

		return mv;
	}

	@RequestMapping(value = "/musicas/{id}", method = RequestMethod.GET)
	public ModelAndView getMusicaDetalhes(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("musicaDetalhes");
		Musica musica = catalogoService.findById(id);
		mv.addObject("musica", musica);
		return mv;
	}

	@RequestMapping(value = "/musicas/edit/{id}", method = RequestMethod.GET)
	public ModelAndView getFormEdit(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("musicaForm");
		Musica musica = catalogoService.findById(id);
		mv.addObject("musica", musica);
		mv.addObject("categorias", categorias);
		return mv;
	}

	@RequestMapping(value = "/addMusica", method = RequestMethod.GET)
	public ModelAndView getMusicaForm(Musica musica) {
		ModelAndView mv = new ModelAndView("musicaForm");
		mv.addObject("categorias", categorias);
		return mv;
	}

	@RequestMapping(value = "/addMusica", method = RequestMethod.POST)
	public ModelAndView salvarMusica(@Valid @ModelAttribute("musica") Musica musica, BindingResult result, Model model) {
		if (result.hasErrors()) {
			ModelAndView musicaForm = new ModelAndView("musicaForm");
			musicaForm.addObject("mensagem", "Verifique os errors do formul??rio");
			musicaForm.addObject("categorias", categorias);
			return musicaForm;
		}
		musica.setData(LocalDate.now());
		catalogoService.save(musica);
		return new ModelAndView("redirect:/musicas");
	}

	@GetMapping("/musicas/pesquisar")
	public ModelAndView pesquisar(@RequestParam(defaultValue = "", name = "titulo") String titulo) {
		ModelAndView mv = new ModelAndView("musicas");
		List<Musica> musicas = null;
		if (!titulo.isEmpty()) {
			musicas = catalogoService.findByTitulo(titulo);
		} else {
			musicas = catalogoService.findAll();
		}
		mv.addObject("musicas", musicas);
		mv.addObject("categorias", categorias);
		return mv;
	}

	@GetMapping("/musicas/pesquisarPorCategoria")
	public ModelAndView pesquisarPorCategoria(@RequestParam(defaultValue = "0", name = "idCategoria") Long idCategoria) {
		ModelAndView mv = new ModelAndView("musicas");
		List<Musica> musicas = null;
		if (idCategoria != 0) {
			musicas = catalogoService.findByIdCategoria(idCategoria);
		} else {
			musicas = catalogoService.findAll();
		}
		mv.addObject("musicas", musicas);
		mv.addObject("categorias", categorias);
		return mv;
	}

	@RequestMapping(value = "/delMusica/{id}", method = RequestMethod.GET)
	public String delMusica(@PathVariable("id") long id) {
		catalogoService.excluir(id);
		return "redirect:/musicas";
	}
}
