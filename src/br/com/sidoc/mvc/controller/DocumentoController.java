package br.com.sidoc.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.CategoriaDAO;
import br.com.sidoc.DAO.DepartamentoDAO;
import br.com.sidoc.DAO.DocumentoDAO;
import br.com.sidoc.DAO.UsuarioDAO;
import br.com.sidoc.model.Documento;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;

public class DocumentoController implements Logica {
	Message mensagem = new Message();
	public String id = null;
	public String titulo = null;
	public String refDocFisico = null;
	public String descricao = null;
	public String ativo = null;
	public String dtCadastro = null;
	public String dtValidade = null;
	public String idCategoria = null;
	public String idDepartamento = null;
	public String idUsuario = null;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		if(PainelController.sessao!=null && PainelController.isLogado() == true){
			String acao = req.getParameter("acao");
			req.setAttribute("link_acao", "sistema?c=Documento&acao=inserir");
			
			// se acao null, carrega a lista
			if(acao != null)
			{
				// Popular variaveis locais com dados recebidos via post
				if(req.getParameter("id")!=null ) id = req.getParameter("id");
				if(req.getParameter("titulo")!=null) titulo =  req.getParameter("titulo");
				if(req.getParameter("ref_doc_fisico")!=null) refDocFisico =  req.getParameter("ref_doc_fisico");
				if(req.getParameter("descricao")!=null) descricao = req.getParameter("descricao");
				if(req.getParameter("ativo")!=null) ativo =  req.getParameter("ativo");
				if(req.getParameter("dt_cadastro")!=null) dtCadastro = req.getParameter("dt_cadastro");
				if(req.getParameter("dt_validade")!=null) dtValidade =  req.getParameter("dt_validade");
				if(req.getParameter("id_categoria")!=null) idCategoria  = req.getParameter("id_categoria");
				if(req.getParameter("id_departamento")!=null) idDepartamento  =  req.getParameter("id_departamento");
				if(req.getParameter("id_usuario")!=null) idUsuario  = req.getParameter("id_usuario");
							
				System.out.println("Data Val: "+dtValidade);
				
				Documento doc = new Documento();
				doc.setAtivo(ativo);
				doc.setDescricao(descricao);
				doc.setRefDocFisico(refDocFisico);
				doc.setTitulo(titulo);
				
				if(dtCadastro!=null) {
					// Converte Date em Calendar - Cadastro
					Calendar dtCa = Calendar.getInstance();
					dtCa.setTime(new SimpleDateFormat("dd/mm/yyyy").parse(dtCadastro));
					doc.setDtValidade(dtCa);		
				}
				
				if(dtValidade!=null){
					// Converte Date em Calendar - Validade
					Calendar calendar1 = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
					Date dt = sdf.parse(dtValidade);
					calendar1.setTime(dt);
					doc.setDtValidade(calendar1);
					
					System.out.println(""); 
					System.out.println("Dta: " + dtValidade); 
					System.out.println("Data formatada: "+sdf.parse(dtValidade)); 
					System.out.println("Data convertida: "+calendar1.getTime()); 
					System.out.println("Data convertida: "+doc.getDtValidade().toString()); 
				}	
				
				System.out.println("Data Val: "+doc.getDtValidade());
				
				CategoriaDAO catDao = new CategoriaDAO();
				DepartamentoDAO deptoDao = new DepartamentoDAO();
				UsuarioDAO daoU = new UsuarioDAO();
				
				if(idCategoria!=null && idCategoria!="")
					doc.setCategoria(catDao.retornaCategoria(Long.parseLong(idCategoria)));
				if(idDepartamento!=null && idDepartamento!="")
					doc.setDepartamento(deptoDao.retornaDepartamento(Long.parseLong(idDepartamento)));
				if(idUsuario!=null && idUsuario!="")
					doc.setUsuario(daoU.retornaUsuario(Long.parseLong(idUsuario)));
				
				System.out.println("ID Categ: " +idCategoria+ " ID Usu: "+idUsuario+" ID Depto: "+idDepartamento);
				
			// Fazer validação dos dados de entrada
				DocumentoDAO dao = new DocumentoDAO();
				if(acao.equals("inserir"))
				{
					req.setAttribute("link_acao", "sistema?c=Documento&acao=gravar");
					RequestDispatcher rd = req.getRequestDispatcher("/views/documento/create.jsp");
					rd.forward(req, res);
				}
				
				else if(acao.equals("gravar"))
				{
					if(id!=null && id!="")doc.setId(Long.parseLong(id));
					Long idLast = dao.salva(doc);
					Documento docNovo = dao.retornaDocumento(idLast);
					
					if(id==null || id=="") id = String.valueOf(docNovo.getId()); 
					req.setAttribute("doc", docNovo);
					
					mensagem.setMessage("Documento salvo com sucesso.");
					req.setAttribute("mensagem", mensagem.getMessage());
                                        req.setAttribute("link_acao", "sistema?c=Documento&acao=gravar&id="+id);
					RequestDispatcher rd = req.getRequestDispatcher("/views/documento/create.jsp");
					rd.forward(req, res);
				}
				
				
				else if(acao.equals("excluir"))
				{
					mensagem.setMessage("Documento nao foi encontrado.");
					mensagem.setStyle("danger");
					doc.setId(Long.parseLong(id));
					Documento docNovo = dao.retornaDocumento(doc.getId());
					System.out.println("ID: " + docNovo.getId());
					if(docNovo.getId()>0 ){
						dao.remove(docNovo);
						mensagem.setMessage("Documento excluido com sucesso.");
						mensagem.setStyle("success");
					}
					req.setAttribute("mensagem", mensagem.getMessage());
					RequestDispatcher rd = req.getRequestDispatcher("/views/documento/index.jsp");
					rd.forward(req, res);	
				}
				
				
				else if(acao.equals("editar"))
				{
					Documento docNovo = dao.retornaDocumento(Long.parseLong(id));
					if(docNovo != null){
						req.setAttribute("doc", docNovo);
						req.setAttribute("link_acao", "sistema?c=Documento&acao=gravar&id="+docNovo.getId());	    
						req.setAttribute("mensagem", null);
						RequestDispatcher rd = req.getRequestDispatcher("/views/documento/create.jsp");
						rd.forward(req, res);
					}
				}
				else
				{
					mensagem.setMessage("Ação invalida.");
					mensagem.setStyle("danger");
					req.setAttribute("mensagem", mensagem.getMessage());
					RequestDispatcher rd = req.getRequestDispatcher("/views/documento/index.jsp");
					rd.forward(req, res);
				}
				
				
			}
			else
			{
			    req.setAttribute("mensagem", null);
				RequestDispatcher rd = req.getRequestDispatcher("/views/documento/index.jsp");
				rd.forward(req, res);
			}
		}else{
			mensagem.setMessage("Acesso restrito.");
			mensagem.setStyle("danger");
			req.setAttribute("mensagem", mensagem.getMessage());
			res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
		}
		
	}
}
