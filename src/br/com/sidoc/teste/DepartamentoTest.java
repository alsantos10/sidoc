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
		  
//		  dp.setDepartamento("Serviços");
//		  dp.setSigla("SER");
//		  dp.setId((long)3);
//		  dao.altera(dp);
//		  
//		  dao.remove(dp);
		  
//		  dp.setDepartamento("Jurídico");
//		  dp.setSigla("JUR");
//		  dao.adiciona(dp);
//		  dp.setDepartamento("Almoxarifado");
//		  dp.setSigla("ALM");
//		  dao.adiciona(dp);
//		  dp.setDepartamento("Marketing");
//		  dp.setSigla("MKT");
//		  dao.adiciona(dp);
//		  dp.setDepartamento("Recursos Humanos");
//		  dp.setSigla("RH");
//		  dao.adiciona(dp);
//		  dp.setDepartamento("Tecnologia da Infomação");
//		  dp.setSigla("TI");
//		  dao.adiciona(dp);
//		  dp.setDepartamento("Financeiro");
//		  dp.setSigla("FIN");
//		  dao.adiciona(dp);
	  }


}
