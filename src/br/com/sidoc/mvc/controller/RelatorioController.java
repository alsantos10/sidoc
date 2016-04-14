package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.CargoDAO;
import br.com.sidoc.model.Cargo;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;

public class RelatorioController implements Logica {
	Message mensagem = new Message();
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		mensagem = null;
		
		if(PainelController.sessao!=null && PainelController.isLogado() == true){
			String acao = req.getParameter("acao");
			String dtInicio = req.getParameter("dt_inicio");
			String dtFinal  = req.getParameter("dt_final");
			req.setAttribute("link_acao", "sistema?c=Relatorio&acao=gerar");
			
			
			// Fazer validação dos dados de entrada
	
			
			if(acao!= null){
				if(acao.equals("gerar")){
					//dao.salva(ctg);
					mensagem.setMessage("Relatorio gerado com sucesso.");
                                        req.setAttribute("mensagem", mensagem.getMessage());
				}
			}			    
			
			RequestDispatcher rd = req.getRequestDispatcher("/views/relatorio/index.jsp");
			rd.forward(req, res);
		}else{
                    req.getSession().setAttribute("msg_erro", "Acesso restrito");
                    res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
		}
	}
}
