package br.com.sidoc.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -6545095931822597053L;

	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		String parametro  = request.getParameter("c");
		String nomeDaClasse = "br.com.sidoc.mvc.controller." + parametro + "Controller";
		try {
			@SuppressWarnings("rawtypes")
			Class classe = Class.forName(nomeDaClasse);
			Logica logica = (Logica) classe.newInstance();
			// Recebe o String após a execução da lógica
			logica.executa(request, response);
		} catch (Exception e) {
			throw new ServletException("A lógica de negócios causou uma excessão ", e);
		}
	}
}
