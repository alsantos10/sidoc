package br.com.sidoc.teste;

import br.com.sidoc.DAO.CategoriaDAO;
import br.com.sidoc.model.Categoria;

public class CategoriaTest {

	public static void main(String[] args) {
		Categoria c = new Categoria();
		CategoriaDAO dao = new CategoriaDAO();
		
//		c.setCategoria("Documentos pessoais");
//		dao.adiciona(c);
		c.setCategoria("Criminal2557756");
		c.setId((long)8);
		dao.salva(c);
//		c.setCategoria("Trabalhista");
//		dao.adiciona(c);
//		c.setCategoria("Pessoal2");
		dao.retornaCategoria(c.getId());
	}
}
