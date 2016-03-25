package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.CargoDAO;
import br.com.sidoc.model.Cargo;
import br.com.sidoc.utils.Utils;

public class CargoController implements Logica {
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
			String cargo1 = req.getParameter("cargo");
			String id = req.getParameter("id");
			req.setAttribute("link_acao", "sistema?c=Cargo&acao=inserir");
			
			CargoDAO dao = new CargoDAO();
			
			Cargo ctg = new Cargo();
			if(id != null) ctg.setId(Long.parseLong(req.getParameter("id")));
			if(cargo1 != null) ctg.setCargo(cargo1);
			
			// Fazer validação dos dados de entrada
	
			
			if(acao!= null){
				if(acao.equals("inserir")){
					dao.salva(ctg);
					mensagem = "Cargo inserido com sucesso.";
				}
				else if(acao.equals("editar")){
					dao.salva(ctg);
					mensagem = "Cargo alterado com sucesso.";
					Cargo cargo = dao.retornaCargo(ctg.getId());
					if(cargo != null){
						req.setAttribute("cargo", cargo);
						req.setAttribute("link_acao", "sistema?c=Cargo&acao=editar&id="+ctg.getId());
					}
				}
				else if(acao.equals("excluir")){
					dao.remove(ctg);
					mensagem = "Cargo excluída com sucesso.";
				}
				else if(acao.equals("exibir")){
					Cargo cargo = dao.retornaCargo(ctg.getId());
					if(cargo != null){
						req.setAttribute("cargo", cargo);
						req.setAttribute("link_acao", "sistema?c=Cargo&acao=editar&id="+ctg.getId());
					}
				}
				else{
					mensagem = "Ação inválida.";
				}
			}
			    
			req.setAttribute("mensagem", mensagem);
			RequestDispatcher rd = req.getRequestDispatcher("/views/cargo/index.jsp");
			rd.forward(req, res);
		}
	}
}
