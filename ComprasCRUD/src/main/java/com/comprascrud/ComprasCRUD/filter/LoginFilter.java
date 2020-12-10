package com.comprascrud.ComprasCRUD.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.comprascrud.ComprasCRUD.model.Usuario;

@Component
public class LoginFilter implements Filter {
	private HttpServletRequest httpRequest;
	private static final String[] loginRequiredURLs = {
	        "/compra", "listaCompras", "cadastrarCompra", "/0", "/1", "/2", "/3", "/4", "/5", "/6", "/7", "/8", "/9"
	};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	      FilterChain chain) throws IOException, ServletException {
		httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		String indexURI = httpRequest.getContextPath() + "/";
        String indexFileURI = httpRequest.getContextPath() + "/index.html";
        String cadastroURI = httpRequest.getContextPath() + "/cadastrarUsuario";
        String cadastroFileURI = httpRequest.getContextPath() + "/cadastro.html";
        String loginURI = httpRequest.getContextPath() + "/loginUsuario";
        String loginFileURI = httpRequest.getContextPath() + "/login.html";
        
        Usuario u = (Usuario) httpRequest.getSession().getAttribute("usuario");
        boolean loggedIn = session != null && u != null;
        boolean indexRequest = httpRequest.getRequestURI().equals(indexURI);
        boolean indexFileRequest = httpRequest.getRequestURI().equals(indexFileURI);
        boolean cadastroRequest = httpRequest.getRequestURI().equals(cadastroURI);
        boolean cadastroFileRequest = httpRequest.getRequestURI().equals(cadastroFileURI);
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean loginFileRequest = httpRequest.getRequestURI().equals(loginFileURI);
        
        if (loggedIn && ((indexRequest) || (indexFileRequest) || (cadastroRequest || cadastroFileRequest) || (loginRequest || loginFileRequest))) {
        	httpRequest.getRequestDispatcher("/listaCompras").forward(request, response);
        } else if (!loggedIn && isLoginRequired()) {
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/loginUsuario");
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
	}
	
	private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();
 
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }
 
        return false;
    }
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
