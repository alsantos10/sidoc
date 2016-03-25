package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.DepartamentoDAO;
import br.com.sidoc.model.Departamento;
import br.com.sidoc.utils.Utils;

public class DepartamentoController implements Logica {
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
			String dep = req.getParameter("departamento");
			String sigla = req.getParameter("sigla");
			String id = req.getParameter("id");
			req.setAttribute("link_acao", "/sistema?c=Departamento&acao=inserir");
			
			DepartamentoDAO dao = new DepartamentoDAO();
			
			Departamento dp = new Departamento();
			if(id != null) dp.setId(Long.parseLong(req.getParameter("id")));
			if(dep != null) dp.setDepartamento(dep);
			if(sigla != null) dp.setSigla(sigla);
			System.out.println("Ação: " + acao);
			// Fazer validação dos dados de entrada
	
			
			if(acao!= null){
				if(acao.equals("inserir")){
					dao.salva(dp);
					mensagem = "Departamento inserido com sucesso.";
				}
				else if(acao.equals("editar")){
					dao.salva(dp);
					mensagem = "Departamento alterado com sucesso.";
					Departamento departamento = dao.retornaDepartamento(dp.getId());
					if(departamento != null){
						req.setAttribute("depto", departamento);
						req.setAttribute("link_acao", "/sistema?c=Departamento&acao=editar&id="+dp.getId());
					}
				}
				else if(acao.equals("excluir")){
					dao.remove(dp);
					mensagem = "Departamento excluído com sucesso.";
				}
				else if(acao.equals("exibir")){
					Departamento departamento = dao.retornaDepartamento(dp.getId());
					if(departamento != null){
						req.setAttribute("depto", departamento);
						req.setAttribute("link_acao", "/sistema?c=Departamento&acao=editar&id="+dp.getId());
					}
				}
				else{
					mensagem = "Ação inválida.";
				}
			}
			
			req.setAttribute("mensagem", mensagem);
			RequestDispatcher rd = req.getRequestDispatcher("/views/departamento/index.jsp");
			rd.forward(req, res);
		}
	}
}
