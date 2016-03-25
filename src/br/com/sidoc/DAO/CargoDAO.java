package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Cargo;


public class CargoDAO {
	private String table;
	private Connection conn;
	
	public CargoDAO(){
		try {
			this.conn = new ConnectionFactory().getConnection();
			this.table = "cargos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salva(Cargo cargo){
		Long id = cargo.getId();
		String sql;
		if(id != null){
			sql = "UPDATE "+ table +" SET " +
					"cargo=? WHERE id=?";
		}else{
			sql = "INSERT INTO "+ table +
					"(cargo) VALUES(?)";
		}	
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cargo.getCargo());
			if(id != null){
				stmt.setLong(2, id);
			}
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Cargo> getLista(){
		try {
			String sql = "SELECT * FROM "+ table + " ORDER BY cargo";
			List<Cargo> cargos = new ArrayList<Cargo>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Cargo cargo = new Cargo();
				cargo.setId(rs.getLong("id"));
				cargo.setCargo(rs.getString("cargo"));
				// adicionando o objeto a lista
				cargos.add(cargo);
			}
			rs.close();
			stmt.close();
			return cargos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Cargo retornaCargo(Long id){
		String sql = "SELECT * FROM "+ table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Cargo cargo = new Cargo();
			if(rs.next()){
				cargo.setId(rs.getLong("id"));
				cargo.setCargo(rs.getString("cargo"));
				rs.close();
				stmt.close();
			}
			return cargo;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void remove(Cargo cargo){
		String sql = "DELETE FROM "+ table +" WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, cargo.getId());
			stmt.execute();
			stmt.close();			
		} 
		catch (NullPointerException e)  
		{
			System.out.println("Cargo não foi encontrado.");
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
