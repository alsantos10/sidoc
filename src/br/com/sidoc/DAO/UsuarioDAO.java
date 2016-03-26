package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Cargo;
import br.com.sidoc.model.Contato;
import br.com.sidoc.model.Departamento;
import br.com.sidoc.model.Endereco;
import br.com.sidoc.model.Usuario;


public class UsuarioDAO {
	private String table;
	private Connection conn;
	
	public UsuarioDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
//			if(conn.getAutoCommit())
//			     conn.setAutoCommit(false);
			this.table = "usuarios";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void salva(Usuario usuario){
		Long id 	= null; 
		Long idEnd 	= null; 
		Long idCont	= null;
		Long idCg	= null;
		Long idDp	= null;
		String sql1 = "";		
		
		id 		= usuario!=null ? usuario.getId() : null ;
		idEnd 	= usuario.getEndereco()!=null ? usuario.getEndereco().getId() : null ;
		idCont 	= usuario.getContato() !=null ? usuario.getContato().getId() : null ;
		idDp	= usuario.getDepartamento().getId()!=null ? usuario.getDepartamento().getId() : null ;
		idCg	= usuario.getCargo().getId()!=null ? usuario.getCargo().getId() : null ;

		// Atualizar os outros objetos
		EnderecoDAO daoE = new EnderecoDAO();
		Endereco end = new Endereco();
		end.setBairro(usuario.getEndereco().getBairro());
		end.setCep(usuario.getEndereco().getCep());
		end.setCidade(usuario.getEndereco().getCidade());
		end.setEstado(usuario.getEndereco().getEstado());
		end.setId(usuario.getEndereco().getId());
		end.setLogradouro(usuario.getEndereco().getLogradouro());
		end.setNumero(usuario.getEndereco().getNumero());
		end.setPais(usuario.getEndereco().getPais());
		idEnd = daoE.salva(end);
		
		ContatoDAO daoC = new ContatoDAO();
		Contato contato = new Contato();
		contato.setCelular(usuario.getContato().getCelular());
		contato.setEmail(usuario.getContato().getEmail());
		contato.setId(usuario.getContato().getId());
		contato.setTelefoneCom(usuario.getContato().getTelefoneCom());
		contato.setTelefoneRes(usuario.getContato().getTelefoneRes());
		idCont = daoC.salva(contato);
		
		// Setar os ids
		if(idCont>0){
			Contato contatoNovo = daoC.retornaContato(idCont);
			usuario.setContato(contatoNovo);
		}
		if(idEnd>0){
			Endereco endNovo = daoE.retornaEndereco(idEnd);
			usuario.setEndereco(endNovo);
		}
			
		if(id != null && id>0){
			sql1 = "UPDATE "+ table +" SET " +
					"login=?, nome=?, sobrenome=?, cpf=?, rg=?, usuario_tipo=?,"
					+ "id_cargo=?, id_departamento=?, id_endereco=?, id_contato=?, "
					+ "sexo=?, ativo=?, id_gerente=? WHERE id=?";
		}else{
			sql1 = "INSERT INTO "+ table +
					"(login, nome, sobrenome, cpf, rg, usuario_tipo,"
					+ "id_cargo, id_departamento, id_endereco, id_contato, "
					+ "sexo, ativo,id_gerente,  senha) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}	
		try {
			// Preparação para usuario
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSobrenome());
			stmt.setString(4, usuario.getCpf());
			stmt.setString(5, usuario.getRg());
			stmt.setString(6, usuario.getUsuarioTipo());
			stmt.setLong(7, usuario.getCargo().getId());
			stmt.setLong(8, usuario.getDepartamento().getId());
			stmt.setLong(9, usuario.getEndereco().getId());
			stmt.setLong(10, usuario.getContato().getId());
			stmt.setString(11, usuario.getSexo());
			stmt.setString(12, usuario.getAtivo());
			stmt.setLong(13, usuario.getGerente().getId());
			if(id != null && id>0){
				stmt.setLong(14, id);
			}else{
				stmt.setString(14, usuario.getSenha());				
			}
			stmt.execute();
			stmt.close();
			
		}
		catch (NullPointerException eNull){
			System.out.println("Existem valores nulos: "+ eNull.getMessage());
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
			     System.out.println("Transaction failed.");
			     conn.rollback();
			}
			catch (SQLException se) {
				throw new RuntimeException(se);
			}
			catch (NullPointerException e2)  
			{
				System.out.println("Usuario não foi encontrado.");
			}
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	            	 System.err.print("Ocorreu um erro. " +excep.getMessage());
	            }
	        }
		}
	}

	public void alterarSenha(Usuario usuario){
		Long id 	= null; 
		String sql1 = "";		
		id = usuario!=null ? usuario.getId() : null ;
		if(id != null && id>0){
			sql1 = "UPDATE "+ table +" SET senha=? WHERE id=?";
		}	
		try {
			// Preparação para usuario
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, usuario.getSenha());
			stmt.setLong(2, id);
			stmt.execute();
			stmt.close();
			
		}
		catch (NullPointerException eNull){
			System.out.println("Existem valores nulos: "+ eNull.getMessage());
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
			     System.out.println("Transaction failed.");
			     conn.rollback();
			}
			catch (SQLException se) {
				throw new RuntimeException(se);
			}
			catch (NullPointerException e2)  
			{
				System.out.println("Usuario não foi encontrado.");
			}
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	            	 System.err.print("Ocorreu um erro. " +excep.getMessage());
	            }
	        }
		}
	}
	
	public List<Usuario> getLista(){
		try {
			String sql = "SELECT a.*, c.id AS id_contato1, email, tel_com, tel_res, celular, "
					+ "e.id AS id_endereco1, logradouro, numero, bairro, cep, cidade, estado, pais "
					+ "FROM "+ table +" AS a "
					+ "LEFT JOIN contatos AS c ON a.id_contato = c.id "
					+ "LEFT JOIN enderecos AS e ON a.id_endereco = e.id "
					+ "ORDER BY nome, sobrenome";
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setRg(rs.getString("rg"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				usuario.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				usuario.setDtCadastro(dt);
				
				Cargo cargo = this.retCargo(rs.getLong("id_cargo"));
				Endereco endereco = this.retEndereco(rs.getLong("id_endereco1"));
				Departamento depto = this.retDepartamento(rs.getLong("id_departamento"));
				Contato contato = this.retContato(rs.getLong("id_contato1"));
				Usuario gerente = this.retGerente(rs.getLong("id_gerente"));
				
				usuario.setCargo(cargo);
				usuario.setDepartamento(depto);
				usuario.setEndereco(endereco);
				usuario.setContato(contato);
				usuario.setGerente(gerente);
				
				// adicionando o objeto a lista
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario retornaUsuario(Long id){
		String sql = "SELECT a.*, c.id AS id_contato1, email, tel_com, tel_res, celular, "
				+ "e.id AS id_endereco1, logradouro, numero, bairro, cep, cidade, estado, pais "
				+ "FROM "+ table +" AS a "
				+ "LEFT JOIN contatos AS c ON a.id_contato = c.id "
				+ "LEFT JOIN enderecos AS e ON a.id_endereco = e.id "
				+ "WHERE a.id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			if(rs.next()){
				usuario.setId(id);
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setRg(rs.getString("rg"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				usuario.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				usuario.setDtCadastro(dt);				

				Cargo cargo = this.retCargo(rs.getLong("id_cargo"));
				Endereco endereco = this.retEndereco(rs.getLong("id_endereco1"));
				Departamento depto = this.retDepartamento(rs.getLong("id_departamento"));
				Contato contato = this.retContato(rs.getLong("id_contato1"));
				Usuario gerente = this.retGerente(rs.getLong("id_gerente"));	
				
				usuario.setCargo(cargo!=null?cargo:null);
				usuario.setDepartamento(depto!=null?depto:null);
				usuario.setEndereco(endereco!=null?endereco:null);
				usuario.setContato(contato!=null?contato:null);
				usuario.setGerente(gerente!=null?gerente:null);			
				
				rs.close();
				stmt.close();
			}
			return usuario; 
		}
		catch (NullPointerException eNull){
			System.out.println("Erro: " + eNull);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;	
	}
	
	public Usuario retornaGerenteDoFuncionario(Long id_gerente){
		String sql = "SELECT * FROM "+ table +
				" WHERE id = ? AND (usuario_tipo = 'gerente' OR usuario_tipo = 'administrador')";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id_gerente);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			if(rs.next()){
				usuario.setId(id_gerente);
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				usuario.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				usuario.setDtCadastro(dt);				
				rs.close();
				stmt.close();
			}
			return usuario; 
		}
		catch (NullPointerException eNull){
			System.out.println("Erro: " + eNull);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;	
	}
	
	public Usuario retornaUsuarioByLogin(String login) throws NullPointerException{
		String sql = "SELECT a.*, c.id AS id_contato1, email, tel_com, tel_res, celular, "
				+ "e.id AS id_endereco1, logradouro, numero, bairro, cep, cidade, estado, pais "
				+ "FROM "+ table +" AS a "
				+ "LEFT JOIN contatos AS c ON a.id_contato = c.id "
				+ "LEFT JOIN enderecos AS e ON a.id_endereco = e.id "
				+ "WHERE a.login = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			if(rs.next()){
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(login);
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setRg(rs.getString("rg"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				usuario.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				usuario.setDtCadastro(dt);				
				
				Cargo cargo = this.retCargo(rs.getLong("id_cargo"));
				Endereco endereco = this.retEndereco(rs.getLong("id_endereco1"));
				Departamento depto = this.retDepartamento(rs.getLong("id_departamento"));
				Contato contato = this.retContato(rs.getLong("id_contato1"));
				Usuario gerente = this.retGerente(rs.getLong("id_gerente"));
				
				usuario.setCargo(cargo);
				usuario.setDepartamento(depto);
				usuario.setEndereco(endereco);
				usuario.setContato(contato);
				usuario.setGerente(gerente);
				rs.close();
				stmt.close();
			}
			return usuario;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public Usuario autenticaUsuarioByLogin(String login, String senha) throws NullPointerException{
		if(login == null || login == "" || senha == null || senha == ""){
			return null;
		}
		String sql = "SELECT a.id, a.login, a.senha, a.nome, a.sobrenome,usuario_tipo, a.id_departamento, a.id_gerente,"
				+ "a.ativo, c.id AS idC, c.email FROM "+ table +" AS a "
				+ "LEFT JOIN contatos AS c ON a.id_contato = c.id "
				+ "WHERE a.login = ? AND a.senha = ? AND ativo = 's'";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			if(rs.next()){
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(login);
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				usuario.setAtivo(rs.getString("ativo"));
				usuario.setEmail(rs.getString("email"));
				DepartamentoDAO daoD = new DepartamentoDAO();
				usuario.setDepartamento(daoD.retornaDepartamento(rs.getLong("id_departamento")));
				usuario.setGerente(this.retGerente(rs.getLong("id_gerente")));
				rs.close();
				stmt.close();
			}
			return usuario;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public Usuario autenticaUsuarioByEmail(String email, String senha ){
		if(email == null || email == "" || senha == null || senha == ""){
			return null;
		}
		String sql = "SELECT a.id, a.login, a.senha, a.nome, a.sobrenome,usuario_tipo, "
				+ "a.ativo, c.id AS idC, c.email FROM "+ table +" AS a "
				+ "LEFT JOIN contatos AS c ON a.id_contato = c.id "
				+ "WHERE c.email = ? AND a.senha = ? AND a.ativo = 's'";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			if(rs.next()){
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				usuario.setAtivo(rs.getString("ativo"));
				usuario.setEmail(email);
				rs.close();
				stmt.close();
			}
			return usuario;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void remove(Usuario usuario){
		// String para excluir registro de usuario
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		Long idEnd  = null;
		Long idCont = null;
		try {
			idCont = usuario.getContato().getId();
			idEnd  = usuario.getEndereco().getId();
			if(idEnd!=null && idEnd>0){
				Endereco endereco = new Endereco();
				endereco.setId(idEnd);
				EnderecoDAO daoE = new EnderecoDAO();
				daoE.remove(endereco);
			}
			if(idCont!=null && idCont>0){
				Contato contato  = new Contato();
				contato.setId(idCont);
				ContatoDAO daoC = new ContatoDAO();
				daoC.remove(contato);
			}			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			stmt.execute();
			stmt.close();
			
		}
		catch (NullPointerException e2)  
		{
			System.out.println("Usuario nao foi encontrado.");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			try {
			     System.out.println("Transaction failed.");
			     conn.rollback();
			}
			catch (SQLException se) {
				throw new RuntimeException(se);
			}
			
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	            	 System.err.print("Ocorreu um erro. " +excep.getMessage());
	            }
	        }
		}
	}
	
	public int lastId(){
		String sql = "SELECT * FROM "+ this.table;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.last();
			int idLast;
			idLast = rs.getInt("id");
			rs.close();
			return idLast;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param long
	 * @return Endereco
	 */
	private Endereco retEndereco(long idEndereco){
		EnderecoDAO endDAO = new EnderecoDAO();
		Endereco endereco = endDAO.retornaEndereco(idEndereco);
		return endereco;
	}
	
	/**
	 * @param long
	 * @return Cargo
	 */
	private Cargo retCargo(long id){
		CargoDAO dao = new CargoDAO();
		Cargo cargo = dao.retornaCargo(id);
		return cargo;
	}
	
	/**
	 * @param long
	 * @return Contato
	 */
	private Contato retContato(long id){
		ContatoDAO dao = new ContatoDAO();
		Contato contato = dao.retornaContato(id);
		return contato;
	}
	
	/**
	 * @param long
	 * @return Departamento
	 */
	private Departamento retDepartamento(long id){
		DepartamentoDAO dao = new DepartamentoDAO();
		Departamento depto = dao.retornaDepartamento(id);
		return depto;
	}
	
	/**
	 * @param long
	 * @return Usuario
	 */
	private Usuario retGerente(long id){
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.retornaGerenteDoFuncionario(id);
		return user;
	}
	
	public List<Usuario> getListaGerente(){
		try {
			String sql = "SELECT * FROM "+ table + " WHERE usuario_tipo = 'gerente' OR usuario_tipo = 'administrador' ORDER BY nome,sobrenome";
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setRg(rs.getString("rg"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setAtivo(rs.getString("ativo"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				
				Cargo cargo = this.retCargo(rs.getLong("id_cargo"));
				Endereco endereco = this.retEndereco(rs.getLong("id_endereco"));
				Departamento depto = this.retDepartamento(rs.getLong("id_departamento"));
				Contato contato = this.retContato(rs.getLong("id_contato"));
				Usuario gerente = this.retGerente(rs.getLong("id_gerente"));
				
				usuario.setCargo(cargo);
				usuario.setDepartamento(depto);
				usuario.setEndereco(endereco);
				usuario.setContato(contato);
				usuario.setGerente(gerente);
				
				// adicionando o objeto a lista
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> getListaUsuario(){
		try {
			String sql = "SELECT * FROM usuarios WHERE usuario_tipo = 'gerente' "
					+ "OR usuario_tipo = 'funcionario' OR usuario_tipo = 'administrador' ORDER BY nome, sobrenome";
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUsuarioTipo(rs.getString("usuario_tipo"));
				// adicionando o objeto a lista
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean retornaDepartamento(long idFun, long idGerente){
		if(idFun>0 && idGerente>0){
			UsuarioDAO dao = new UsuarioDAO();
			Usuario user = dao.retornaUsuario(idFun);
			return user.getGerente().getId() == idGerente;
		}
		return false;
	}

}
