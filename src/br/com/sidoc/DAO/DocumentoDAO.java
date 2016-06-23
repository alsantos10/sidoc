package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Arquivo;
import br.com.sidoc.model.Categoria;
import br.com.sidoc.model.Departamento;
import br.com.sidoc.model.Documento;
import br.com.sidoc.model.Usuario;


public class DocumentoDAO {
	private String table;
	private Connection conn;
	
	public DocumentoDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
			this.table = "documentos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public long salva(Documento doc) throws SQLException{
		Long id 	 = null; 
		Long idUsu	 = null;
		Long idCateg = null;
		Long idDp	 = null;
		String sql   = "";		
		
		id = doc!=null ? doc.getId() : null;
		
		idUsu 	= doc.getUsuario()!=null ? doc.getUsuario().getId() : null ;
		idCateg	= doc.getCategoria() !=null ? doc.getCategoria().getId() : null ;
		idDp	= doc.getDepartamento().getId()!=null ? doc.getDepartamento().getId() : null ;
		
			
		if(id != null && id>0){
			sql = "UPDATE "+ table +" SET " +
					"titulo=?, ref_doc_fisico=?, descricao=?, ativo=?, "
					+ "dt_validade=?, id_usuario=?, id_categoria=?, id_departamento=? "
					+ "WHERE id=?";
		}else{
			sql = "INSERT INTO "+ table +
					"(titulo, ref_doc_fisico, descricao, ativo, "
					+ "dt_validade, id_usuario, id_categoria, id_departamento) "
					+ "VALUES(?,?,?,?,?,?,?,?)";
		}	
		System.out.println("Query utilizada: " + sql);
			
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			
			// Preparação para doc
			long key = -1L;
			//conn.setAutoCommit(false);
		stmt.setString(1, doc.getTitulo());
			stmt.setString(2, doc.getRefDocFisico());
			stmt.setString(3, doc.getDescricao());
			stmt.setString(4, doc.getAtivo());
			
			System.out.println("Chegou data: "+doc.getDtValidade().getTimeInMillis());
			Calendar cal = doc.getDtValidade();
			Date d = new Date(doc.getDtValidade().getTimeInMillis());
			System.out.println("Chegou data convertida: "+d);
			
			stmt.setDate(5, d);
			stmt.setLong(6, doc.getUsuario().getId());
			stmt.setLong(7, doc.getCategoria().getId());
			stmt.setLong(8, doc.getDepartamento().getId());
			if(id != null && id>0){
				stmt.setLong(9, id);
				stmt.executeUpdate();
			}else{
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs != null && rs.next()) {
				    key = rs.getLong(1);
				    rs.close();
				}
				id = key;
			}
			
			stmt.close();
			return id;
			
		}
//		catch (NullPointerException eNull){
//			System.out.println("Existem valores nulos: "+ eNull);
//		}
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
				System.out.println("Documento n�o foi encontrado.");
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
		return id;
	}
	
	public List<Documento> getLista(){
		try {
			String sql = "SELECT * FROM " + table + " ORDER BY titulo";
			List<Documento> docs = new ArrayList<Documento>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Documento doc = new Documento();
				doc.setId(rs.getLong("id"));
				doc.setTitulo(rs.getString("titulo"));
				doc.setRefDocFisico(rs.getString("ref_doc_fisico"));
				doc.setDescricao(rs.getString("descricao"));
				doc.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				doc.setDtCadastro(dt);
				
				Calendar dt2 = Calendar.getInstance();
				dt2.setTime(rs.getDate("dt_validade"));
				doc.setDtValidade(dt2);
				
				Categoria categ 	= this.retCategoria(rs.getLong("id_categoria"));
				Departamento depto 	= this.retDepartamento(rs.getLong("id_departamento"));
				Usuario usuario 	= this.retUsuario(rs.getLong("id_usuario"));
				
				doc.setCategoria(categ);
				doc.setDepartamento(depto);
				doc.setUsuario(usuario);
				
				// adicionando o objeto a lista
				docs.add(doc);
			}
			rs.close();
			stmt.close();
			return docs;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Documento retornaDocumento(Long id){
		String sql = "SELECT * FROM "+ table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Documento doc = new Documento();
			if(rs.next()){
				doc.setId(rs.getLong("id"));
				doc.setTitulo(rs.getString("titulo"));
				doc.setRefDocFisico(rs.getString("ref_doc_fisico"));
				doc.setDescricao(rs.getString("descricao"));
				doc.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				doc.setDtCadastro(dt);
				
				Calendar dt2 = Calendar.getInstance();
				dt2.setTime(rs.getDate("dt_validade"));
				doc.setDtValidade(dt2);
				
				Categoria categ 	= this.retCategoria(rs.getLong("id_categoria"));
				Departamento depto 	= this.retDepartamento(rs.getLong("id_departamento"));
				Usuario usuario 	= this.retUsuario(rs.getLong("id_usuario"));
				
				doc.setCategoria(categ);
				doc.setDepartamento(depto);
				doc.setUsuario(usuario);
				rs.close();
				stmt.close();
			}
		//	System.out.println("Documento :"+doc.getTitulo());
			return doc; 
		}
		catch (NullPointerException eNull){
			System.out.println("Erro: " + eNull.getMessage());
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;	
	}
	
	public void remove(Documento doc){
		// String para excluir registro de doc
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, doc.getId());
			stmt.execute();
			stmt.close();
			
		}
		catch (NullPointerException e2)  
		{
			System.out.println("Documento n�o foi encontrado.");
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
        
        
        public List<Documento> getRelatorio(String dtIni, String dtFim){
		try {
			String sql = 
                        "SELECT * FROM " + table  + " " +
                        "WHERE " + table + ".dt_cadastro BETWEEN ('" + dtIni + "') AND ('" + dtFim + "') " +
                        "ORDER BY " + table + ".dt_cadastro DESC";
			List<Documento> docs = new ArrayList<Documento>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Documento doc = new Documento();
				doc.setId(rs.getLong("id"));
				doc.setTitulo(rs.getString("titulo"));
				doc.setRefDocFisico(rs.getString("ref_doc_fisico"));
				doc.setDescricao(rs.getString("descricao"));
				doc.setAtivo(rs.getString("ativo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				doc.setDtCadastro(dt);
				
				Calendar dt2 = Calendar.getInstance();
				dt2.setTime(rs.getDate("dt_validade"));
				doc.setDtValidade(dt2);
				
				Categoria categ 	= this.retCategoria(rs.getLong("id_categoria"));
				Departamento depto 	= this.retDepartamento(rs.getLong("id_departamento"));
				Usuario usuario 	= this.retUsuario(rs.getLong("id_usuario"));
                                
				doc.setCategoria(categ);
				doc.setDepartamento(depto);
				doc.setUsuario(usuario);
				
				// adicionando o objeto a lista
				docs.add(doc);
			}
			rs.close();
			stmt.close();
			return docs;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
        
	
	/**
	 * @param long
	 * @return Categoria
	 */
	private Categoria retCategoria(long id){
		CategoriaDAO dao = new CategoriaDAO();
		Categoria categ = dao.retornaCategoria(id);
		return categ;
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
	private Usuario retUsuario(long id) throws NullPointerException{
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.retornaUsuario(id);
		return user;
	}
	

	
	public List<Usuario> getListaUsuario(){
		try {
			String sql = "SELECT * FROM usuarios WHERE usuario_tipo = 'gerente' "
					+ "OR usuario_tipo = 'funcionario' OR usuario_tipo = 'administrador' ORDER BY nome";
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
}
