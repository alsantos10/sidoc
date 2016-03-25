package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.EnderecoDAO;
import br.com.sidoc.model.Endereco;
import br.com.sidoc.utils.Utils;

public class EnderecoController implements Logica {
	String mensagem;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		mensagem = null;
		
		if(PainelController.sessao!=null && PainelController.isLogado() == false){
			mensagem = "Acesso restrito.";
			res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
		}else{
			String acao = req.getParameter("acao");
			String logr = req.getParameter("logradouro");
			String id = req.getParameter("id");
			req.setAttribute("link_acao", "/sistema?c=Endereco&acao=inserir");
			
			EnderecoDAO dao = new EnderecoDAO();
			
			Endereco endr = new Endereco();
			if(id != null) endr.setId(Long.parseLong(req.getParameter("id")));
			if(logr != null) endr.setLogradouro(logr);
			
			// Fazer validação dos dados de entrada
	
			
			if(acao!= null){
				if(acao.equals("inserir")){
					dao.salva(endr);
					mensagem = "Endereco inserido com sucesso.";
				}
				else if(acao.equals("editar")){
					dao.salva(endr);
					mensagem = "Endereco alterado com sucesso.";
					Endereco endereco = dao.retornaEndereco(endr.getId());
					if(endereco != null){
						req.setAttribute("address", endereco);
						req.setAttribute("link_acao", "/sistema?c=Endereco&acao=editar&id="+endr.getId());
					}
				}
				else if(acao.equals("excluir")){
					dao.remove(endr);
					mensagem = "Endereco excluído com sucesso.";
				}
				else if(acao.equals("exibir")){
					Endereco endereco = dao.retornaEndereco(endr.getId());
					if(endereco != null){
						req.setAttribute("address", endereco);
						req.setAttribute("link_acao", "/sistema?c=Endereco&acao=editar&id="+endr.getId());
					}
				}
				else{
					mensagem = "Ação inválida.";
				}
			}
			    
			req.setAttribute("mensagem", mensagem);
			RequestDispatcher rd = req.getRequestDispatcher("/views/endereco/index.jsp");
			rd.forward(req, res);
		}
	}
}
