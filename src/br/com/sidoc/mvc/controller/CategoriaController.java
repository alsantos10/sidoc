package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.CategoriaDAO;
import br.com.sidoc.model.Categoria;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;

public class CategoriaController implements Logica {
	Message mensagem = new Message();
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		if(PainelController.sessao!=null && PainelController.isLogado() == true){
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
					mensagem.setMessage("Categoria inserida com sucesso.");
				}
				else if(acao.equals("editar")){
					dao.salva(ctg);
					mensagem.setMessage("Categoria alterada com sucesso.");
					Categoria categoria = dao.retornaCategoria(ctg.getId());
					if(categoria != null){
						req.setAttribute("categ", categoria);
						req.setAttribute("link_acao", "/sistema?c=Categoria&acao=editar&id="+ctg.getId());
					}
				}
				else if(acao.equals("excluir")){
					dao.remove(ctg);
					mensagem.setMessage("Categoria excuida com sucesso.");
				}
				else if(acao.equals("exibir")){
					Categoria categoria = dao.retornaCategoria(ctg.getId());
					if(categoria != null){
						req.setAttribute("categ", categoria);
						req.setAttribute("link_acao", "/sistema?c=Categoria&acao=editar&id="+ctg.getId());
					}
				}
				else{
					mensagem.setMessage("Ação invalida.");
					mensagem.setStyle("danger");
				}
                                req.setAttribute("mensagem", mensagem.getMessage());
			}
			
			
			RequestDispatcher rd = req.getRequestDispatcher("/views/categoria/index.jsp");
			rd.forward(req, res);
		}else{
			req.getSession().setAttribute("msg_erro", "Acesso restrito");
			res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
		}
	}
}
