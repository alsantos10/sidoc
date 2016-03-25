package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.CategoriaDAO;
import br.com.sidoc.model.Categoria;
import br.com.sidoc.utils.Utils;

public class CategoriaController implements Logica {
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
			String categ = req.getParameter("categoria");
			String id = req.getParameter("id");
			req.setAttribute("link_acao", "/sistema?c=Categoria&acao=inserir");
			
			CategoriaDAO dao = new CategoriaDAO();
			
			Categoria ctg = new Categoria();
			if(id != null) ctg.setId(Long.parseLong(req.getParameter("id")));
			if(categ != null) ctg.setCategoria(categ);
			
			// Fazer validação dos dados de entrada
	
			
			if(acao!= null){
				if(acao.equals("inserir")){
					dao.salva(ctg);
					mensagem = "Categoria inserida com sucesso.";
				}
				else if(acao.equals("editar")){
					dao.salva(ctg);
					mensagem = "Categoria alterada com sucesso.";
					Categoria categoria = dao.retornaCategoria(ctg.getId());
					if(categoria != null){
						req.setAttribute("categ", categoria);
						req.setAttribute("link_acao", "/sistema?c=Categoria&acao=editar&id="+ctg.getId());
					}
				}
				else if(acao.equals("excluir")){
					dao.remove(ctg);
					mensagem = "Categoria excluída com sucesso.";
				}
				else if(acao.equals("exibir")){
					Categoria categoria = dao.retornaCategoria(ctg.getId());
					if(categoria != null){
						req.setAttribute("categ", categoria);
						req.setAttribute("link_acao", "/sistema?c=Categoria&acao=editar&id="+ctg.getId());
					}
				}
				else{
					mensagem = "Ação inválida.";
				}
			}
			    
			req.setAttribute("mensagem", mensagem);
			RequestDispatcher rd = req.getRequestDispatcher("/views/categoria/index.jsp");
			rd.forward(req, res);
		}
	}
}
