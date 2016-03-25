package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.CargoDAO;
import br.com.sidoc.DAO.DepartamentoDAO;
import br.com.sidoc.DAO.UsuarioDAO;
import br.com.sidoc.model.Contato;
import br.com.sidoc.model.Endereco;
import br.com.sidoc.model.Usuario;
import br.com.sidoc.utils.Utils;

public class FuncionarioController implements Logica {
	String mensagem;
	public String id = null;
	public String nome = null;
	public String sobrenome = null;
	public String login = null;
	public String senha = null;
	public String cpf = null;
	public String rg = null;
	public String usuarioTipo = null;
	public String sexo = null;
	public String idUsuarioTipo = null;
	public String idCargo = null;
	public String idDepartamento = null;
	public String idEndereco = null;
	public String idContato = null;
	public String idGerente = null;
	public String ativo = null;
	public String email = null;
	public String telCom = null;
	public String telRes = null;
	public String celular = null;
	public String logradouro = null;
	public String numero = null;
	public String bairro = null;
	public String cep = null;
	public String cidade = null;
	public String estado = null;
	public String pais = null;
	
	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		mensagem = null;
		String acao = req.getParameter("acao");
		
		if(PainelController.sessao!=null && PainelController.isLogado()){
			// Está logado
			if(!PainelController.isLogadoAdmin() && !PainelController.isLogadoGerente()){
				mensagem = "Acesso restrito.";
				req.getSession().setAttribute("mensagem", mensagem);
				res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
			}
			else
			{
				// É administrador ou Gerente

				req.setAttribute("link_acao", "sistema?c=Funcionario&acao=inserir");
				
				// se acao null, carrega a lista
				if(acao != null)
				{
					// Popular variaveis locais com dados recebidos via post
					if(req.getParameter("id")!=null ) id = req.getParameter("id");
					if(req.getParameter("nome")!=null)nome =  req.getParameter("nome");
					if(req.getParameter("sobrenome")!=null)sobrenome =  req.getParameter("sobrenome");
					if(req.getParameter("login")!=null)login = req.getParameter("login");
					if(req.getParameter("senha")!=null)senha =  Utils.md5(req.getParameter("senha"));
					if(req.getParameter("ativo")!=null)ativo =  req.getParameter("ativo");
					if(req.getParameter("cpf")!=null)cpf = req.getParameter("cpf");
					if(req.getParameter("rg")!=null)rg =  req.getParameter("rg");
					if(req.getParameter("sexo")!=null)sexo =  req.getParameter("sexo");
					if(req.getParameter("id_cargo")!=null) idCargo  = req.getParameter("id_cargo");
					if(req.getParameter("id_departamento")!=null) idDepartamento  =  req.getParameter("id_departamento");
					if(req.getParameter("id_gerente")!=null) idGerente  = req.getParameter("id_gerente");
					if(req.getParameter("usuario_tipo")!=null) usuarioTipo  = req.getParameter("usuario_tipo");
					
					// Campos de contato
					if(req.getParameter("id_contato")!=null) idContato  = req.getParameter("id_contato");
					if(req.getParameter("email")!=null) email  = req.getParameter("email");
					if(req.getParameter("tel_com")!=null) telCom  = req.getParameter("tel_com");
					if(req.getParameter("tel_res")!=null) telRes  = req.getParameter("tel_res");
					if(req.getParameter("celular")!=null) celular  = req.getParameter("celular");
					
					// Campos de Endereco
					if(req.getParameter("id_endereco")!=null) idEndereco  = req.getParameter("id_endereco");
					if(req.getParameter("logradouro")!=null) logradouro  = req.getParameter("logradouro");
					if(req.getParameter("numero")!=null) numero  = req.getParameter("numero");
					if(req.getParameter("bairro")!=null) bairro  = req.getParameter("bairro");
					if(req.getParameter("cep")!=null) cep  = req.getParameter("cep");
					if(req.getParameter("cidade")!=null) cidade  = req.getParameter("cidade");
					if(req.getParameter("estado")!=null) estado  = req.getParameter("estado");
					if(req.getParameter("pais")!=null)pais =  req.getParameter("pais");
					
					UsuarioDAO dao = new UsuarioDAO();
					DepartamentoDAO deptoDao = new DepartamentoDAO();
					CargoDAO cargoDao = new CargoDAO();
					
					// Popular o objeto Usuario
					Usuario func = new Usuario();
					
					func.setAtivo(ativo);
					func.setCpf(cpf);
					if(idCargo!=null && idCargo!="")
						func.setCargo(cargoDao.retornaCargo(Long.parseLong(idCargo)));
					if(idDepartamento!=null && idDepartamento!="")
						func.setDepartamento(deptoDao.retornaDepartamento(Long.parseLong(idDepartamento)));
					if(idGerente!=null && idGerente!="")
						func.setGerente(dao.retornaUsuario(Long.parseLong(idGerente)));
					
					func.setLogin(login);
					func.setNome(nome);
					func.setRg(rg);
					func.setSenha(senha);
					func.setSexo(sexo);
					func.setSobrenome(sobrenome);
					func.setUsuarioTipo(usuarioTipo); // usuario_tipo fixo
					
					// Popular o objeto Usuario
					Contato contato = new Contato();
					if(idContato!=null && idContato!="")contato.setId(Long.parseLong(idContato));
					contato.setCelular(celular);
					contato.setEmail(email);
					contato.setTelefoneCom(telCom);
					contato.setTelefoneRes(telRes);
					
					// Popular o objeto Endereco
					Endereco end = new Endereco();
					if(idEndereco!=null && idEndereco!="")end.setId(Long.parseLong(idEndereco));
					end.setBairro(bairro);
					end.setCep(cep);
					end.setCidade(cidade);
					end.setEstado(estado);
					end.setLogradouro(logradouro);
					end.setNumero(numero);
					end.setPais(pais);
					
					func.setContato(contato);
					func.setEndereco(end);
				
				// Fazer validação dos dados de entrada
					
					if(acao.equals("inserir"))
					{
						req.setAttribute("link_acao", "sistema?c=Funcionario&acao=gravar");
						RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/create.jsp");
						rd.forward(req, res);
					}
			
					
					else if(acao.equals("senha"))
					{
						if(PainelController.isLogadoAlterar(id) || PainelController.isLogadoAdmin() ||
								(PainelController.isLogadoGerente() && PainelController.isLogadoMesmoDepartamento(id) )){
							System.out.println("Id Logado True " +  id);
							req.setAttribute("link_acao", "sistema?c=Funcionario&acao=gravarSenha&id="+id);
							RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/alterar-senha.jsp");
							rd.forward(req, res);
						}else{
							System.out.println("Id Logado False " +  id);
							mensagem = "Ação inválida.";
							req.getSession().setAttribute("mensagem",mensagem);
							res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
						}
					} 
					
					else if(acao.equals("gravar"))
					{		
						if(PainelController.isLogadoAlterar(id) || PainelController.isLogadoAdmin() || 
								PainelController.isLogadoGerente()){
							
							if(id!=null && id!=""){
								func.setId(Long.parseLong(id));
								if((PainelController.isLogadoGerente() && !PainelController.isLogadoMesmoDepartamento(id))){
									mensagem = "Ação inválida.";
									req.getSession().setAttribute("mensagem",mensagem);
									res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
								}
							}			
							dao.salva(func);
							Usuario funcNovo = dao.retornaUsuarioByLogin(func.getLogin());
							
							if(id==null || id=="") id = String.valueOf(funcNovo.getId()); 
							req.setAttribute("func", funcNovo);
							mensagem = "Funcionario salvo com sucesso.";
						    req.setAttribute("mensagem", mensagem);
						    req.setAttribute("link_acao", "sistema?c=Funcionario&acao=gravar&id="+id);
							RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/create.jsp");
							rd.forward(req, res);
						}else{
							mensagem = "Ação inválida.";
							req.getSession().setAttribute("mensagem",mensagem);
							res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
						}
					}
					
					else if(acao.equals("gravarSenha"))
					{
						if(PainelController.isLogadoAlterar(id) || PainelController.isLogadoAdmin() ||
								(PainelController.isLogadoGerente() && PainelController.isLogadoMesmoDepartamento(id))){
							if(id!=null && id!="")func.setId(Long.parseLong(id));
							String senhaAtual = req.getParameter("senhaAtual");
							String senhaNova = req.getParameter("senhaNova");
							String senhaConf = req.getParameter("senhaConf");
							Usuario funcBD = dao.retornaUsuario(func.getId());
												
							if(senhaNova.equals(senhaConf)){
								if(funcBD.getSenha().equals(Utils.md5(senhaAtual))){
									func.setSenha(Utils.md5(senhaNova));
									dao.alterarSenha(func);
									mensagem = "Senha alterada com sucesso.";
								}else{
									mensagem = "Erro ao alterar a senha.";
								}
							}else{
								mensagem = "Nova senha incorreta.";
							}
						    req.setAttribute("mensagem", mensagem);
						    req.setAttribute("link_acao", "sistema?c=Funcionario&acao=gravarSenha&id="+id);
							RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/alterar-senha.jsp");
							rd.forward(req, res);
						}else{
							mensagem = "Ação inválida.";
							req.getSession().setAttribute("mensagem",mensagem);
							res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
						}
					}
					
					
					else if(acao.equals("excluir"))
					{
						if(!PainelController.isLogadoAlterar(id) || (PainelController.isLogadoGerente()
								&& PainelController.isLogadoMesmoDepartamento(id))){
							mensagem = "Funcionario não foi encontrado.";
							func.setId(Long.parseLong(id));
							Usuario funcNovo = dao.retornaUsuario(func.getId());
							System.out.println("ID: " + funcNovo.getId());
							if(funcNovo.getId()>0 ){
								dao.remove(funcNovo);
								mensagem = "Funcionario excluído com sucesso.";
							}
						}else{
							mensagem = "Ação inválida.";
							req.getSession().setAttribute("mensagem",mensagem);
							res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
						}
						req.setAttribute("mensagem", mensagem);
						RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/index.jsp");
						rd.forward(req, res);
							
					}
					
					
					else if(acao.equals("editar"))
					{
						if(PainelController.isLogadoAlterar(id) || PainelController.isLogadoAdmin() ||
								(PainelController.isLogadoGerente() && PainelController.isLogadoMesmoDepartamento(id))){
							func.setId(Long.parseLong(id));
							Usuario user = dao.retornaUsuario(Long.parseLong(id));
							if(user != null){
								System.out.println("ID Usuario: " + id);
								req.setAttribute("func", user);
								req.setAttribute("link_acao", "sistema?c=Funcionario&acao=gravar&id="+user.getId());	    
								req.setAttribute("mensagem", mensagem);
								RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/create.jsp");
								rd.forward(req, res);
							}
						}else{
							mensagem = "Ação inválida.";
							req.getSession().setAttribute("mensagem",mensagem);
							res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Painel");
						}
					}
				}
				else
				{
					//Acao is null
					req.setAttribute("mensagem", mensagem);
					RequestDispatcher rd = req.getRequestDispatcher("/views/funcionario/index.jsp");
					rd.forward(req, res);
				}
			}
		}
		// Restricoes
		else
		{
			System.out.println("Aqui6");
			mensagem = "Acesso restrito.";
			res.sendRedirect(Utils.getBaseUrl(req) + "/sistema?c=Home&acao=login");
			System.out.println("Aqui7");
		}
	}
}
