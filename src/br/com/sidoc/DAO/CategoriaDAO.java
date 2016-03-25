package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Categoria;


public class CategoriaDAO {
	private String table;
	private Connection conn;
	
	public CategoriaDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
			this.table = "categorias";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Long salva(Categoria categoria){
		Long id = categoria.getId();
		String sql;
		Long idInsenrido = null;
		if(id != null){
			sql = "UPDATE "+ table +" SET " +
					"categoria=? WHERE id=?";
		}else{
			sql = "INSERT INTO "+ table +
					"(categoria) VALUES(?)";
		}	
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoria.getCategoria());
			if(id != null){
				stmt.setLong(2, id);
				stmt.execute();
				return id; 
			}
			stmt.execute();
			String sqlId = "SELECT MAX(id) as idNew FROM " + table;
			ResultSet rs = stmt.executeQuery(sqlId);
			while(rs.next()){
				idInsenrido=Long.parseLong(rs.getString("idNew"));
			}
			stmt.close();
			return idInsenrido;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Categoria> getLista(){
		try {
			String sql = "SELECT * FROM "+ table + " ORDER BY categoria";
			List<Categoria> categorias = new ArrayList<Categoria>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id"));
				categoria.setCategoria(rs.getString("categoria"));
				// adicionando o objeto a lista
				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			return categorias;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Categoria retornaCategoria(Long id){
		String sql = "SELECT * FROM "+ table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Categoria categoria = new Categoria();
			if(rs.next()){
				categoria.setId(rs.getLong("id"));
				categoria.setCategoria(rs.getString("categoria"));
				rs.close();
				stmt.close();
			}
			return categoria;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void remove(Categoria categoria){
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, categoria.getId());
			stmt.execute();
			stmt.close();			
		} 
		catch (NullPointerException e)  
		{
			System.out.println("Categoria n�o foi encontrada.");
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
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
}
