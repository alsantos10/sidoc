package br.com.sidoc.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Arquivo;
import br.com.sidoc.model.Documento;
import br.com.sidoc.model.Usuario;


public class ArquivoDAO {
	private String table;
	private Connection conn;
	
	public ArquivoDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
			this.table = "arquivos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public long salva(Arquivo file){
		Long id 	 = null; 
		Long idUsu	 = null;
		Long idDoc = null;
		String sql   = "";		
		
		
		id = file!=null ? file.getId() : null;
		idUsu 	= file.getUsuario()!=null ? file.getUsuario().getId() : 0 ;
		idDoc	= file.getDocumento()!=null? file.getDocumento().getId() : 0 ;
			
		if(id != null && id>0){
			sql = "UPDATE "+ table +" SET " +
				"titulo=?, arquivo=?, id_usuario=?, id_documento=? "+
				"WHERE id=?";
		}else{
			sql = "INSERT INTO "+ table +
					"(titulo, arquivo, id_usuario, id_documento) "
					+ "VALUES(?,?,?,?)";
		}	
		System.out.println("Query utilizada: " + sql);
			
		try {
		
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// Preparação para file
			long key = -1L;
			stmt.setString(1, file.getTitulo());
			stmt.setString(2, file.getArquivo());
			stmt.setLong(3, idUsu);
			stmt.setLong(4, idDoc);
			if(id != null && id>0){
				stmt.setLong(5, id);
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
//			System.out.println("Existem valores nulos: "+ eNull.getMessage());
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
				System.out.println("Arquivo n�o foi encontrado.");
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
	
	public List<Arquivo> getLista(){
		try {
			String sql = "SELECT * FROM " + table + " ORDER BY titulo";
			List<Arquivo> files = new ArrayList<Arquivo>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Arquivo file = new Arquivo();
				file.setId(rs.getLong("id"));
				file.setTitulo(rs.getString("titulo"));
				file.setArquivo(rs.getString("arquivo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				file.setDtCadastro(dt);
				
				Documento doc 	= this.retDocumento(rs.getLong("id_documento"));
				Usuario usuario 	= this.retUsuario(rs.getLong("id_usuario"));
				
				file.setDocumento(doc);
				file.setUsuario(usuario);
				
				// adicionando o objeto a lista
				files.add(file);
			}
			rs.close();
			stmt.close();
			return files;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Arquivo retornaArquivo(Long id){
		String sql = "SELECT * FROM "+ table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Arquivo file = new Arquivo();
			if(rs.next()){
				file.setId(rs.getLong("id"));
				file.setTitulo(rs.getString("titulo"));
				file.setArquivo(rs.getString("arquivo"));
				
				// montando a data atraves do Calendar
				Calendar dt = Calendar.getInstance();
				dt.setTime(rs.getDate("dt_cadastro"));
				file.setDtCadastro(dt);
				
				Documento doc 	= this.retDocumento(rs.getLong("id_documento"));
				Usuario usuario 	= this.retUsuario(rs.getLong("id_usuario"));
				
				file.setDocumento(doc);
				file.setUsuario(usuario);
				rs.close();
				stmt.close();
			}
			System.out.println("Arquivo :"+file.getTitulo());
			return file; 
		}
		catch (NullPointerException eNull){
			System.out.println("Erro: " + eNull.getMessage());
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;	
	}
	
	public void remove(Arquivo file){
		// String para excluir registro de file
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, file.getId());
			stmt.execute();
			stmt.close();
			
		}
		catch (NullPointerException e2)  
		{
			System.out.println("Arquivo n�o foi encontrado.");
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
	 * @return Documento
	 */
	private Documento retDocumento(long id){
		DocumentoDAO dao = new DocumentoDAO();
		Documento doc = dao.retornaDocumento(id);
		return doc;
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

	   
    /**
     * Copia arquivos de um local para o outro 
     * @param origem - Arquivo de origem 
     * @param destino - Arquivo de destino 
     * @param overwrite - Confirmação para sobrescrever os arquivos 
     * @throws IOException 
     */
    public static void copy(File origem, File destino) throws IOException {
        Date date = new Date();
        
        System.out.println("Origem " + origem.getAbsolutePath());
        
        InputStream in = new FileInputStream(origem);
        
        System.out.println("Destino " + destino.getAbsolutePath());
        OutputStream out = new FileOutputStream(destino);           
        // Transferindo bytes de entrada para saída
        
        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght= in.read(buffer)) > 0) {
            out.write(buffer, 0, lenght);
        }
        in.close();
        out.close();
        Long time = new Date().getTime() - date.getTime();
        System.out.println("Saiu copy "+time);
    }
    
    
    public String retornaUltimoArquivo(){
    	String sql = "SELECT SUBSTRING(arquivo, 1 , 10) as valor "
		+ "FROM "+ this.table + " ORDER BY arquivo DESC LIMIT 1";
    	try {
    		PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String res = null;
			if(rs.next()){
				res = rs.getString("valor");
			}
			rs.close();
			stmt.close();
			System.out.println("arquivo :"+res);
			
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
        
    
    public Boolean verificaArquivoDuplicado(String nomeArquivo){
    	if(nomeArquivo!=null){
	    	String sql = "SELECT count(arquivo) as valor FROM "+ this.table + 
	    			" WHERE arquivo = " + nomeArquivo;
	    	try {
	    		PreparedStatement stmt = this.conn. prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				Boolean res = false;
				if(rs.next()){
					res = rs.getBoolean("valor");
				}
				rs.close();
				stmt.close();
				System.out.println("arquivo :"+res);
				return res;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
    	}
    	return false;
    }
}
