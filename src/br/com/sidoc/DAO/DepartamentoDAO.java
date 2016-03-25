package br.com.sidoc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sidoc.conexao.ConnectionFactory;
import br.com.sidoc.model.Departamento;


public class DepartamentoDAO {
	private String table;
	private Connection conn;
	
	public DepartamentoDAO(){
		try {
			this.conn =  new ConnectionFactory().getConnection();
			this.table = "departamentos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salva(Departamento dp){
		String sql;
		Long id = dp.getId();
		if(id != null){
			sql = "UPDATE "+ this.table +" SET " +
					"departamento=?, sigla=? WHERE id=?";
		}else{
			sql = "INSERT INTO "+ this.table +
				"(departamento, sigla) VALUES(?,?)";
		}
		try {
			// prepared statement para inser��o
			PreparedStatement stmt = conn.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, dp.getDepartamento());
			stmt.setString(2, dp.getSigla());
			if(id != null){
				stmt.setLong(3, dp.getId());
			}
			//executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Departamento> getLista(){
		try {
			String sql = "SELECT * FROM "+ this.table + " ORDER BY departamento";
			List<Departamento> departamentos = new ArrayList<Departamento>();
			PreparedStatement stmt = this.conn. prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Departamento dp = new Departamento();
				dp.setId(rs.getLong("id"));
				dp.setDepartamento(rs.getString("departamento"));
				dp.setSigla(rs.getString("sigla"));
				// adicionando o objeto a lista
				departamentos.add(dp);
			}
			rs.close();
			stmt.close();
			return departamentos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Departamento retornaDepartamento(Long id){
		String sql = "SELECT * FROM "+ this.table +" WHERE id = ?";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Departamento dp = new Departamento();
			if(rs.next()){
				dp.setId(rs.getLong("id"));
				dp.setDepartamento(rs.getString("departamento"));
				dp.setSigla(rs.getString("sigla"));
				rs.close();
				stmt.close();
			}
			return dp;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void remove(Departamento dp){
		String sql = "DELETE FROM "+ this.table +" WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, dp.getId());
			stmt.execute();
			stmt.close();			
		} 
		catch (NullPointerException e)  
		{
			System.out.println("Departamento n�o foi encontrado.");
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
