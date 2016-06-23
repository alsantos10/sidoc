package br.com.sidoc.teste;

import br.com.sidoc.DAO.DepartamentoDAO;
import br.com.sidoc.model.Departamento;

public class DepartamentoTest {

	  public static void main(String[] args) {
		  // Criar departamentos
		  Departamento dp = new Departamento();
		  DepartamentoDAO dao = new DepartamentoDAO();

		  dp.setId((long)6);
		  dao.retornaDepartamento(dp.getId());
	  }


}
