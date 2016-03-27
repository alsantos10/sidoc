package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;


public class ContatoController implements Logica {
	Message mensagem = new Message();
	static HttpSession sessao;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
	
		String acao = req.getParameter("acao");
		if(acao!= null){
			if(acao.equals("enviar")){
				// Enviar contato
				mensagem.setMessage("Mensagem enviada com sucesso");
				req.setAttribute("mensagem", mensagem.getMessage());
				res.sendRedirect(Utils.getBaseUrl(req));
			}
			else{
				mensagem.setMessage("Acao invalida.");
				req.setAttribute("mensagem", mensagem.getMessage());
				//res.sendRedirect(Utils.getBaseUrl(req));
			}
		}
		System.out.println("Veio aqui");
		req.setAttribute("link_acao", "sistema?c=Contato&acao=enviar");
		RequestDispatcher rd = req.getRequestDispatcher("/views/home/contato.jsp");
		rd.forward(req, res);
		
	}
}
