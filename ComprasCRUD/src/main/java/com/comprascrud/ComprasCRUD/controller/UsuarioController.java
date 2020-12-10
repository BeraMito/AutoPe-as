package com.comprascrud.ComprasCRUD.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comprascrud.ComprasCRUD.model.Usuario;
import com.comprascrud.ComprasCRUD.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepository iUR;
	
	@RequestMapping(method=RequestMethod.GET, path="/")
	public String inicio() {
		return "index";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET )
	public String form() {
		return "cadastro";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST )
	public String form(@Valid Usuario user, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarUsuario";
		}
		if (!iUR.existsByLogin(user.getLogin())) {
			iUR.save(user);
			attributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
			return "redirect:/loginUsuario";
		}
		attributes.addFlashAttribute("mensagem", "Esse nome de usuário já existe!");
		return "redirect:/cadastrarUsuario";
	}
	
	@RequestMapping(value="/loginUsuario", method=RequestMethod.GET )
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/loginUsuario", method=RequestMethod.POST )
	public String login(@Valid Usuario user, BindingResult result, RedirectAttributes attributes, HttpSession session) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/loginUsuario";
		}
		if (iUR.existsByLoginAndSenha(user.getLogin(), user.getSenha())) {
			attributes.addFlashAttribute("mensagem", "Login efetuado!");
			Usuario userIUR = (Usuario) iUR.findByLogin(user.getLogin());
			session.setAttribute("usuario", userIUR);
			return "redirect:/listaCompras";
		}
		attributes.addFlashAttribute("mensagem", "Nome de usuário e/ou senha incorretos!");
		return "redirect:/loginUsuario";
	}
	
	@RequestMapping(value="/logoutUsuario", method=RequestMethod.GET )
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

}
