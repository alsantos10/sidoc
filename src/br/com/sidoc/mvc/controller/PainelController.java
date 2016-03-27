package br.com.sidoc.mvc.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sidoc.DAO.UsuarioDAO;
import br.com.sidoc.Rules.Validation;
import br.com.sidoc.model.Usuario;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;


public class PainelController implements Logica {
	Message mensagem = new Message();
	String usuario;
	String senha;
	static HttpSession sessao = null;
	int[] erros = new int[10];

	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		String acao = req.getParameter("acao");
		// Fazer validação dos dados de entrada
		if(acao!= null){
			if(acao.equals("login")){
				usuario = req.getParameter("usuario")!=null?req.getParameter("usuario"):null;
				senha = req.getParameter("senha")!=null?req.getParameter("senha"):null;
			
				Usuario userValido = null;
				if(usuario!=null && senha!=null && Validation.minCaracters(usuario,4) &&
						Validation.minMaxCaracters(senha,5,12)){
					userValido =  this.autenticarUsuario(usuario, senha);
				}
				
				if(userValido!=null){
					sessao = req.getSession();
					sessao.setAttribute("usuario_id", userValido.getId());
					sessao.setAttribute("usuario_logged", userValido.getLogin());
					sessao.setAttribute("usuario_usuariotipo", userValido.getUsuarioTipo());
					sessao.setAttribute("usuario_departamento", userValido.getDepartamento());
					sessao.setAttribute("usuario_gerente", userValido.getGerente());
					
					mensagem.setMessage("Usu�rio autenticado com sucesso");
					req.setAttribute("mensagem", mensagem.getMessage());
					
					req.getSession().setAttribute("usuarioLogado",userValido);
					res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
				}else{
					req.getSession().setAttribute("usuario",usuario);
					req.getSession().setAttribute("senha", senha);

					mensagem.setMessage("Usuario inexistente ou inativo. Contate o administrador");
					req.setAttribute("mensagem", mensagem.getMessage());
					
					res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
				}
			}
			
			else if(acao.equals("contato")){
				if(sessao==null){
					res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=contato");
				}else{
					req.setAttribute("link_acao", "sistema?c=Home&acao=enviar");
					mensagem.setMessage("Contato inserido com sucesso");
					req.setAttribute("mensagem", mensagem.getMessage());
					RequestDispatcher rd = req.getRequestDispatcher("/views/home/contato.jsp");
					rd.forward(req, res);
				}
			}
			
			else if(acao.equals("logout")){
				if(sessao!=null){
					sessao.removeAttribute("usuario_id");  
					sessao.removeAttribute("usuario_logged");  
					sessao.removeAttribute("usuario_nome");  
					sessao.removeAttribute("usuario_usuariotipo");  
					sessao.removeAttribute("usuario_departamento_id");  
					req.getSession().setAttribute("usuarioLogado","");
					req.getSession().setAttribute("usuario", "");
					req.getSession().setAttribute("senha", "");
					sessao.invalidate();  
					sessao =null;
				}
				mensagem.setMessage( "Logout realizado com sucesso.");
				req.setAttribute("mensagem", mensagem.getMessage());				
				res.sendRedirect(Utils.getBaseUrl(req));				
			}
			else{
				mensagem.setMessage("Ação inválida.");
				req.setAttribute("mensagem", mensagem.getMessage());
			}
		}else{
			if(sessao != null && PainelController.isLogado()){
				req.setAttribute("mensagem", null);
				RequestDispatcher rd = req.getRequestDispatcher("/views/painel/index.jsp");
				rd.forward(req, res);
			}else{
				mensagem.setMessage("Acesso restrito");
				req.setAttribute("mensagem", mensagem.getMessage());
				res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
			}
		}
	}
	
	
	public static boolean isLogado(){
		if(sessao != null && sessao.getAttribute("usuario_logged") != null){
			return true;
		}
		return false;
	}

	public static boolean isLogadoAdmin(){
		String tipo = String.valueOf(sessao.getAttribute("usuario_usuariotipo"));
		return sessao != null && tipo.equals("administrador");
	}

	public static boolean isLogadoGerente(){
		String tipo = String.valueOf(sessao.getAttribute("usuario_usuariotipo"));
		return sessao != null && tipo.equals("gerente");
	}

	public static boolean isLogadoAlterar(String id){
		if(id!=null){
			String idSessao = String.valueOf(sessao.getAttribute("usuario_id"));
			return sessao != null && idSessao.equals(id);
		}
		return false;
	}

	public static boolean isLogadoMesmoDepartamento(String id){
		if(sessao != null &&id!=null){
			UsuarioDAO dao = new UsuarioDAO();
			long idFun = Long.parseLong(id);
			long idGerente = (long) sessao.getAttribute("usuario_id");
			return dao.retornaDepartamento(idFun, idGerente);
		}
		return false;
	}
	
	private Usuario autenticarUsuario(String usuario, String senha) throws NoSuchAlgorithmException{
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = new Usuario();
		user = dao.autenticaUsuarioByEmail(usuario, Utils.md5(senha));
		if(user != null && user.getId()>0){
			System.out.println("Usuario credenciado pelo email");
			return user;
		}
		else {
			user = dao.autenticaUsuarioByLogin(usuario, Utils.md5(senha));
			if(user != null && user.getId()>0){
				System.out.println("Usuario credenciado pelo login");
				return user;
			}
			else{
				System.out.println("Usuario inexistente ou inativo. Contate o administrador");
			}
			return null;
		}
	}
	
}
