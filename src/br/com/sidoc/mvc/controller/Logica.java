package br.com.sidoc.mvc.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public interface Logica {
	void executa(HttpServletRequest req,
			HttpServletResponse res) throws Exception;
}
