package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sidoc.utils.Utils;


public class HomeController implements Logica {
	String mensagem;
	String usuario;
	String senha;
	static HttpSession sessao;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		mensagem = null;
	
		String acao = req.getParameter("acao");
		if(acao!= null){
			if(acao.equals("login")){
				if(sessao !=null && PainelController.isLogado()){
					res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
				}else{
					req.setAttribute("link_acao", "sistema?c=Painel&acao=login");
					req.setAttribute("mensagem", mensagem);
					RequestDispatcher rd = req.getRequestDispatcher("/views/home/login.jsp");
					rd.forward(req, res);
				}
			}
			
			else if(acao.equals("contato")){
				req.getSession().setAttribute("mensagem",null);
				req.setAttribute("link_acao", "sistema?c=Home&acao=enviar");
				req.setAttribute("mensagem", mensagem);
				RequestDispatcher rd = req.getRequestDispatcher("/views/home/contato.jsp");
				rd.forward(req, res);
			}
			
			else if(acao.equals("enviar")){
				// Enviar contato
				req.getSession().setAttribute("mensagem","Mensagem enviada com sucesso");
				res.sendRedirect(Utils.getBaseUrl(req));
			}
			else{
				mensagem = "Ação inválida.";
				req.getSession().setAttribute("mensagem",mensagem);
				res.sendRedirect(Utils.getBaseUrl(req));
			}
		}else{
			req.getSession().setAttribute("mensagem",null);
			res.sendRedirect(Utils.getBaseUrl(req));
		}
	}
}
