package br.com.sidoc.mvc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.com.sidoc.DAO.ArquivoDAO;
import br.com.sidoc.DAO.DocumentoDAO;
import br.com.sidoc.DAO.UsuarioDAO;
import br.com.sidoc.model.Arquivo;
import br.com.sidoc.model.Documento;
import br.com.sidoc.model.Usuario;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;

@MultipartConfig
public class ArquivoController implements Logica {
	private static final String PATH_DIR = "C:/Users/Andre/Documents/Projetos_git/sidoc/WebContent/";
	private static final String UPLOAD_DIR = PATH_DIR + "assets/uploads/";

	Message mensagem = new Message();
	public String id = null;
	public String titulo = null;
	public String dtCadastro = null;
	public String idDocumento = null;
	public String idUsuario = null;
	public String nomeArquivo = null;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		
		if(PainelController.sessao!=null && PainelController.isLogado() == false){
			mensagem.setMessage("Acesso restrito.");
			mensagem.setStyle("danger");
			
			res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
		}else{
			String acao = req.getParameter("acao");
			req.setAttribute("link_acao", "sistema?c=Arquivo&acao=inserir");
			
			// se acao null, carrega a lista
			if(acao != null)
			{
				ArquivoDAO dao = new ArquivoDAO();
				
				// Popular variaveis locais com dados recebidos via post
				if(req.getParameter("id")!=null ) id = req.getParameter("id");
				if(req.getParameter("dt_cadastro")!=null) dtCadastro = req.getParameter("dt_cadastro");
				
				if(acao.equals("inserir"))
				{
					req.getSession().setAttribute("mensagem",null);
					req.setAttribute("link_acao", "sistema?c=Arquivo&acao=upload");
					RequestDispatcher rd = req.getRequestDispatcher("/views/arquivo/upload.jsp");
					rd.forward(req, res);
				}
				else if(acao.equals("exibir"))
				{
					req.getSession().setAttribute("mensagem",null);
					if(id==null){
						mensagem.setMessage("Nenhum arquivo selecionado");
						mensagem.setStyle("danger");
						req.getSession().setAttribute("mensagem",mensagem.getMessage());
						res.sendRedirect("sistema?c=Arquivo");
					}else if(dao.retornaArquivo( Long.parseLong(id)) == null){
						mensagem.setMessage("Arquivo nao foi encontrado");
						mensagem.setStyle("danger");
						req.getSession().setAttribute("mensagem",mensagem.getMessage());
						res.sendRedirect("sistema?c=Arquivo");
					}else{
						Arquivo fileNew = dao.retornaArquivo( Long.parseLong(id));
						req.setAttribute("file", fileNew);
						req.setAttribute("link_acao", "sistema?c=Arquivo&acao=editar&id="+id);
						RequestDispatcher rd = req.getRequestDispatcher("/views/arquivo/upload.jsp");
						rd.forward(req, res);
					}
				}
				else if(acao.equals("upload"))
				{
					req.getSession().setAttribute("mensagem",null);
					if(req.getParameter("titulo")!=null) titulo =  req.getParameter("titulo");
						
					// Create path components to save the file
				    final Part filePart   = req.getPart("file");
				    final String fileName = getFileName(filePart);
	
				    String fileNameNew = getAlteraNome(fileName);
				    OutputStream out = null;
				    InputStream filecontent = null;
				    
				    try {
				    	File novoArquivo = new File(UPLOAD_DIR + fileNameNew);
				    	if(novoArquivo.isFile()){
				    		mensagem.setMessage("Upload cancelado. Arquivo existente: "+ fileNameNew);
							mensagem.setStyle("info");
				    	}
				    	else
				    	{
					    	out = new FileOutputStream(novoArquivo);
					        filecontent = filePart.getInputStream();
					        
					        int read = 0;
					        final byte[] bytes = new byte[1024];
		
					        while ((read = filecontent.read(bytes)) != -1) {
					            out.write(bytes, 0, read);
					        }
					        Arquivo arquivo = new Arquivo();
					        arquivo.setArquivo(fileNameNew);
					        arquivo.setTitulo(titulo);
					        
					        if(req.getParameter("id_documento")!=null){
					        	idDocumento  = req.getParameter("id_documento");
					        	if(idDocumento.length()>0){
					        		DocumentoDAO docDAO = new DocumentoDAO();
					            	Documento doc = docDAO.retornaDocumento(Long.parseLong(idDocumento));
							        if(doc != null){arquivo.setDocumento(doc); }
								}
					        }
				        
							if(req.getParameter("id_usuario")!=null){
								idUsuario  = req.getParameter("id_usuario");
								if(idUsuario.length()>0){
									UsuarioDAO uDAO = new UsuarioDAO();
									Usuario usuario = uDAO.retornaUsuario(Long.parseLong(idUsuario));
									arquivo.setUsuario(usuario);
								}
							}
							Long idNovo = gravarArquivo(arquivo);
							if(idNovo > 0){
								Arquivo arquivoNew = dao.retornaArquivo(idNovo);								
								mensagem.setMessage("Upload realizado com sucesso. Arquivo: "+ arquivoNew.getTitulo());
								mensagem.setStyle("success");
							}
				    	}
						req.setAttribute("link_acao", "/sistema?c=Arquivo&acao=inserir");					    
						req.getSession().setAttribute("mensagem",mensagem.getMessage());
						res.sendRedirect("sistema?c=Arquivo");
				       
			    	} 
				    catch (FileNotFoundException fne) {
				    	System.out.println("You either did not specify a file to upload or are "
				                + "trying to upload a file to a protected or nonexistent "
				                + "location.");
				        System.out.println("<br/> ERROR: " + fne.getMessage());
	
					} finally {
				        if (out != null) {
				            out.close();
				        }
				        if (filecontent != null) {
				            filecontent.close();
				        }		    										
					}
				    
				}
				else if(acao.equals("excluir"))
				{
					req.getSession().setAttribute("mensagem",null);
					if(id!=null){
						Arquivo arquivo = dao.retornaArquivo(Long.parseLong(id));
						String fileNameNew = arquivo.getArquivo();
						File nomeArquivo = new File(UPLOAD_DIR + fileNameNew);
						try {
							this.removerArquivo(nomeArquivo);
							dao.remove(arquivo);
							mensagem.setMessage("Arquivo "+arquivo.getTitulo()+" excluido com sucesso.");
							mensagem.setStyle("success");
						} catch (Exception e) {
							System.out.println("Erro ao excluir arquivo " + e.getMessage());
						}
					}
					mensagem.setMessage("Nenhum arquivo selecionado");
					mensagem.setStyle("danger");
					req.getSession().setAttribute("mensagem",mensagem.getMessage());
					res.sendRedirect("sistema?c=Arquivo");
					
				}
				else if(acao.equals("editar"))
				{
					req.getSession().setAttribute("mensagem",null);
					if(id!=null){
						if(req.getParameter("titulo")!=null) titulo =  req.getParameter("titulo");
						Arquivo arquivo = dao.retornaArquivo(Long.parseLong(id));
						arquivo.setTitulo(titulo);
					    try {
			    		    if(req.getParameter("id_documento")!=null){
					        	idDocumento  = req.getParameter("id_documento");
					        	if(idDocumento.length()>0){
						        	DocumentoDAO docDAO = new DocumentoDAO();
					            	Documento doc = docDAO.retornaDocumento(Long.parseLong(idDocumento));
							        if(doc != null){arquivo.setDocumento(doc); }
								}
					        }
				        
							if(req.getParameter("id_usuario")!=null){
								idUsuario  = req.getParameter("id_usuario");
								if(idUsuario.length()>0){
									UsuarioDAO uDAO = new UsuarioDAO();
									Usuario usuario = uDAO.retornaUsuario(Long.parseLong(idUsuario));
									arquivo.setUsuario(usuario);
								}
							}
							Long idNovo = gravarArquivo(arquivo);
							if(idNovo > 0){
								Arquivo arquivoNew = dao.retornaArquivo(idNovo);
								mensagem.setMessage("Alteração no arquivo "+ arquivoNew.getTitulo()+" realizada com sucesso");
								mensagem.setStyle("success");
							}
					       
				    	}catch (Exception e) {
							System.out.println("Error: " +e.getMessage());
						}
					}else{
						mensagem.setMessage("Nenhum arquivo selecionado");
						mensagem.setStyle("info");
					}
	
					req.setAttribute("link_acao", "/sistema?c=Arquivo&acao=inserir");
				    req.getSession().setAttribute("mensagem",mensagem.getMessage());
					res.sendRedirect("sistema?c=Arquivo");
				    
				}else{
					req.setAttribute("link_acao", "/sistema?c=Arquivo&acao=inserir");
				    req.setAttribute("mensagem", mensagem.getMessage());
					RequestDispatcher rd = req.getRequestDispatcher("/views/arquivo/index.jsp");
					rd.forward(req, res);
				}
			}
			else
			{
				//Caso acao is null				
				req.setAttribute("link_acao", "/sistema?c=Arquivo&acao=inserir");
			    req.setAttribute("mensagem", null);
				RequestDispatcher rd = req.getRequestDispatcher("/views/arquivo/index.jsp");
				rd.forward(req, res);
			}
		}
	}


	private String getFileName(final Part part) {
		//final String partHeader = part.getHeader("content-disposition");
	   // System.out.println("Part Header "+ partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	private String getAlteraNome(String fileName){
		String ext = "";
		if(fileName != null){
			int pos = fileName.lastIndexOf(".");
			if(pos>0){
				ext = fileName.substring(pos+1, fileName.length());
				fileName = fileName.substring(0, pos);
				String fileNameNovo = this.renomearArquivo(fileName);
				return fileNameNovo + '.' + ext;
			}			
		}					
		return null;
	}
	
	private Long gravarArquivo(Arquivo arquivo){
		ArquivoDAO dao = new ArquivoDAO();
		long idNovo = 0;
		try {
			idNovo = dao.salva(arquivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idNovo;
	}
	

	   
    private String renomearArquivo(String str) {
    	final int MAX_VALOR = 50;  // AtÃ© 50 caracteres
    	ArquivoDAO dao = new ArquivoDAO();
    	// Fazer uma busca na lista de arquivos e 
    	//verificar qual o ultimo nome do arquivo para concatenar o prÃ³ximo numero
		String prevFile = dao.retornaUltimoArquivo();
		int num = 1;
		if(prevFile!=null){
			num = Integer.parseInt(prevFile) +1;
		}
		prevFile = String.format("%010d", num);
		String strNova = prevFile.concat("_"+Utils.removerAcentos(str)).substring(0, (str.length()+11)>MAX_VALOR?MAX_VALOR:str.length()+11);
		return strNova;
	  }
	
    private Boolean removerArquivo(File fileName){
    	if(fileName.exists()){
    		System.out.println("Arquivo existe");
    		fileName.delete();
    		return true;
    	}
    	return false;
    }
    
}
