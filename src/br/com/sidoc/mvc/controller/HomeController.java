package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;


public class HomeController implements Logica {
	Message mensagem = new Message();
	String usuario;
	String senha;
	static HttpSession sessao;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
	
		String acao = req.getParameter("acao");
		if(acao!= null){
			if(acao.equals("login")){
				if(sessao !=null && PainelController.isLogado()){
					res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
				}else{
					req.setAttribute("link_acao", "sistema?c=Painel&acao=login");
					req.setAttribute("mensagem", null);
					req.setAttribute("mensagem", req.getSession().getAttribute("msg_erro"));
					req.getSession().setAttribute("msg_erro", "");
					RequestDispatcher rd = req.getRequestDispatcher("/views/home/login.jsp");
					rd.forward(req, res);
				}
			}else{
				mensagem.setMessage("Ação inválida.");
				req.setAttribute("mensagem", mensagem.getMessage());
				res.sendRedirect(Utils.getBaseUrl(req));
			}
		}else{
			req.getSession().setAttribute("mensagem",null);
			res.sendRedirect(Utils.getBaseUrl(req));
		}
	}
}
