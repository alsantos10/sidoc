package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Contato;


public class ContatoDAO {
	private String table;
	private Connection conn;
	
	public ContatoDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
			this.table = "contatos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Long salva(Contato contato){
		Long id = contato.getId();
		String sql;
		if(id != null && id>0){
			sql = "UPDATE "+ table +" SET " +
					"email=?,tel_com=?,tel_res=?,celular=? WHERE id=?";
		}else{
			sql = "INSERT INTO "+ table +
					"(email, tel_com, tel_res, celular) VALUES(?,?,?,?)";
		}	
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, contato.getEmail());
			stmt.setString(2, contato.getTelefoneCom());
			stmt.setString(3, contato.getTelefoneRes());
			stmt.setString(4, contato.getCelular());
			if(id != null && id>0){
				stmt.setLong(5, id);
			}
			stmt.executeUpdate();	
			if(id == null || id<=0){
				ResultSet rs1 = stmt.getGeneratedKeys();
				id = rs1.next()?rs1.getLong(1):0; 
				rs1.close();
			}
			stmt.close();
			return id;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getLista(){
		try {
			String sql = "SELECT * FROM "+ table + " ORDER BY contato";
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setEmail(rs.getString("email"));
				contato.setTelefoneCom(rs.getString("tel_com"));
				contato.setTelefoneRes(rs.getString("tel_res"));
				contato.setCelular(rs.getString("celular"));
				// adicionando o objeto a lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Contato retornaContato(Long id){
		String sql = "SELECT * FROM "+ table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Contato contato = new Contato();
			if(rs.next()){
				contato.setId(rs.getLong("id"));
				contato.setEmail(rs.getString("email"));
				contato.setTelefoneCom(rs.getString("tel_com"));
				contato.setTelefoneRes(rs.getString("tel_res"));
				contato.setCelular(rs.getString("celular"));
				rs.close();
				stmt.close();
			}
			return contato;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void remove(Contato contato){
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();			
		} 
		catch (NullPointerException e)  
		{
			System.out.println("Contato nao foi encontrado.");
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
