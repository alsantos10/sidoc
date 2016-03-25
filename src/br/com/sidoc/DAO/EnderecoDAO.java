package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Endereco;

public class EnderecoDAO {
	private String table;
	private Connection conn;
	
	public EnderecoDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
			this.table = "enderecos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Long salva(Endereco endereco){
		Long id = null;
		id = endereco.getId();
		String sql;
		if(id != null && id>0){
			sql = "UPDATE "+ table +" SET " +
					"logradouro=?, numero=?, bairro=?, cidade=?, estado=?, cep=?,pais=?"
					+ " WHERE id=?";
		}else{
			sql = "INSERT INTO "+ table +
				  " (logradouro, numero, bairro, cidade, estado, cep, pais)"
					+ " VALUES (?,?,?,?,?,?,?)";
		}	
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, endereco.getLogradouro());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCidade());
			stmt.setString(5, endereco.getEstado());
			stmt.setString(6, endereco.getCep());
			stmt.setString(7, endereco.getPais());
			if(id != null && id>0){
				stmt.setLong(8, id);
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
	
	public List<Endereco> getLista(){
		try {
			String sql = "SELECT * FROM "+ table;
			List<Endereco> enderecos = new ArrayList<Endereco>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Endereco endereco = new Endereco();
				endereco.setId(rs.getLong("id"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setPais(rs.getString("pais"));
				// adicionando o objeto a lista
				enderecos.add(endereco);
			}
			rs.close();
			stmt.close();
			return enderecos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Endereco retornaEndereco(Long id){
		String sql = "SELECT * FROM "+ table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Endereco endereco = new Endereco();
			if(rs.next()){
				endereco.setId(rs.getLong("id"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setPais(rs.getString("pais"));
				rs.close();
				stmt.close();
			}
			return endereco;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void remove(Endereco endereco){
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, endereco.getId());
			stmt.execute();
			stmt.close();			
		} 
		catch (NullPointerException e)  
		{
			System.out.println("Endereço inexistente.");
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
