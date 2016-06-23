package br.com.sidoc.mvc.controller;

import br.com.sidoc.DAO.DepartamentoDAO;
import br.com.sidoc.model.Departamento;
import br.com.sidoc.utils.JavaMailApp;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;

public class ContatoController implements Logica {

    Message mensagem = new Message();
    static HttpSession sessao;
    
    public String nome = null;
    public String email = null;
    public String comentario = null;
    public String departamento = null;

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String acao = req.getParameter("acao");
        if (acao != null) {
            if (acao.equals("enviar")) {
                if(req.getParameter("nome")!=null) nome  =  req.getParameter("nome");
                if(req.getParameter("email")!=null) email  =  req.getParameter("email");
                if(req.getParameter("comentario")!=null) comentario  =  req.getParameter("comentario");
                if(req.getParameter("id_departamento")!=null){
                    String idDepartamento  =  req.getParameter("id_departamento");
                    DepartamentoDAO deptoDao = new DepartamentoDAO();
                    Departamento depto = deptoDao.retornaDepartamento(Long.parseLong(idDepartamento));
                    departamento = depto.getDepartamento();
                }
                String[] sendMessage = {nome, email, departamento,comentario};
                
//                JavaMailApp mailApp = new JavaMailApp(email, nome, sendMessage);
//                mailApp.sendMail();
                
                // Enviar contato
                mensagem.setMessage("Mensagem enviada com sucesso");
                req.setAttribute("mensagem", mensagem.getMessage());
//                res.sendRedirect(Utils.getBaseUrl(req) + "sistema?c=Contato");
            } else {
                mensagem.setMessage("Acao invalida.");
                req.setAttribute("mensagem", mensagem.getMessage());
                //res.sendRedirect(Utils.getBaseUrl(req));
            }
        }
        System.out.println("Veio aqui");
        req.setAttribute("link_acao", "sistema?c=Contato&acao=enviar");
        RequestDispatcher rd = req.getRequestDispatcher("/views/home/contato.jsp");
        rd.forward(req, res);

    }
}
