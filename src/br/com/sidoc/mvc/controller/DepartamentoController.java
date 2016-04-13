package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.DepartamentoDAO;
import br.com.sidoc.model.Departamento;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;

public class DepartamentoController implements Logica {
	Message mensagem = new Message();
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		if(PainelController.sessao!=null && PainelController.isLogado() == true){
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
					mensagem.setMessage("Departamento inserido com sucesso.");
                                        req.setAttribute("mensagem", mensagem.getMessage());
				}
				else if(acao.equals("editar")){
					dao.salva(dp);
					mensagem.setMessage("Departamento aLterado com sucesso.");
                                        req.setAttribute("mensagem", mensagem.getMessage());
                                        
					Departamento departamento = dao.retornaDepartamento(dp.getId());
					if(departamento != null){
						req.setAttribute("depto", departamento);
						req.setAttribute("link_acao", "/sistema?c=Departamento&acao=editar&id="+dp.getId());
					}
				}
				else if(acao.equals("excluir")){
					dao.remove(dp);
					mensagem.setMessage("Departamento excluido com sucesso.");
                                        req.setAttribute("mensagem", mensagem.getMessage());
				}
				else if(acao.equals("exibir")){
					Departamento departamento = dao.retornaDepartamento(dp.getId());
					if(departamento != null){
						req.setAttribute("depto", departamento);
						req.setAttribute("link_acao", "/sistema?c=Departamento&acao=editar&id="+dp.getId());
					}
				}
				else{
                                    mensagem.setMessage("Ação invalida.");
                                    mensagem.setStyle("danger");
                                    req.setAttribute("mensagem", mensagem.getMessage());
				}
                            RequestDispatcher rd = req.getRequestDispatcher("/views/departamento/index.jsp");
                            rd.forward(req, res);
			}
                            RequestDispatcher rd = req.getRequestDispatcher("/views/departamento/index.jsp");
                            rd.forward(req, res);			
		}else{
			req.getSession().setAttribute("msg_erro", "Acesso restrico");
			res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
		}
	}
}
