package br.com.sidoc.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sidoc.DAO.DocumentoDAO;
import br.com.sidoc.model.Documento;
import br.com.sidoc.utils.Message;
import br.com.sidoc.utils.Utils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.faces.lifecycle.Phase;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
//import java.util.List;

public class RelatorioController implements Logica {

    Message mensagem = new Message();
    String dtInicio;
    String dtFinal;
    public static final String DEST = Utils.BASE_ABSOLUTE_PATH + "assets/pdfs/relatorio.pdf";

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String view;

        if (PainelController.sessao != null && PainelController.isLogado() == true) {
            String acao = req.getParameter("acao");
            String dtIniStr = "";
            String dtFimStr = "";

            req.setAttribute("link_acao", "sistema?c=Relatorio&acao=gerar");
            
            // Fazer validação dos dados de entrada
            if (acao != null) {
                if (acao.equals("gerar")) {
                    if (req.getParameter("dt_inicial") != null) {
                        dtInicio = req.getParameter("dt_inicial");
                    }
                    if (req.getParameter("dt_final") != null) {
                        dtFinal = req.getParameter("dt_final");
                    }
                    if (dtInicio != null) {
                        Date dtIni = new SimpleDateFormat("dd/MM/yyyy").parse(dtInicio);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        dtIniStr = sdf.format(dtIni);
                    }
                    if (dtFinal != null) {
                        Date dtFim = new SimpleDateFormat("dd/MM/yyyy").parse(dtFinal);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        dtFimStr = sdf2.format(dtFim);
                    }

                    DocumentoDAO dDao = new DocumentoDAO();
                    req.setAttribute("relatorio", dDao.getRelatorio(dtIniStr, dtFimStr));
                    req.setAttribute("dt_inicio", dtInicio);
                    req.setAttribute("dt_final", dtFinal);
                    view = "/views/relatorio/rel_ini_final.jsp";
                    RequestDispatcher rd = req.getRequestDispatcher(view);
                    rd.forward(req, res);
                    
                } else if (acao.equals("pdf")) {
                    if (req.getParameter("dt_inicial") != null) {
                        dtInicio = req.getParameter("dt_inicial");
                    }
                    if (req.getParameter("dt_final") != null) {
                        dtFinal = req.getParameter("dt_final");
                    }
                    if (dtInicio != null) {
                        Date dtIni = new SimpleDateFormat("dd/MM/yyyy").parse(dtInicio);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        dtIniStr = sdf.format(dtIni);
                    }
                    if (dtFinal != null) {
                        Date dtFim = new SimpleDateFormat("dd/MM/yyyy").parse(dtFinal);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        dtFimStr = sdf2.format(dtFim);
                    }
                    
                    String fileName = "rel-sidoc" + dtIniStr + "-" + dtFimStr + ".pdf" ;
                    res.setContentType("application/pdf");  
                    res.setHeader("Content-disposition",   "attachment; filename=" + fileName);

                    Document document = new Document(PageSize.A4, 5f, 5f, 5f, 5f);
                    PdfWriter.getInstance(document, new FileOutputStream(fileName));

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PdfWriter.getInstance(document, baos);

                    DocumentoDAO dDao = new DocumentoDAO();
                    List<Documento> docs = dDao.getRelatorio(dtIniStr, dtFimStr);
                    
                    document.open();

                    //Primeiro converte de String para Date
                    DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
                    Date dateInicial = formatUS.parse(dtIniStr);
                    //Depois formata data
                    DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
                    String dataInicialOut = formatBR.format(dateInicial);

                    //Primeiro converte de String para Date
                    DateFormat formatUS2 = new SimpleDateFormat("yyyy-mm-dd");
                    Date dateFinal = formatUS2.parse(dtFimStr);
                    //Depois formata data
                    DateFormat formatBR2 = new SimpleDateFormat("dd/mm/yyyy");
                    String dataFinalOut = formatBR2.format(dateFinal);

                    // Titulo e subtitulo da tabela        
                    Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
                    Paragraph p1 = new Paragraph("Relatório de documentos - Sistema de Documentaçao SIDOC", font1);
                    Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.GRAY);
                    Paragraph p2 = new Paragraph("Período de " +dataInicialOut+ " a " +dataFinalOut, font2);
                    document.add(p1);
                    document.add(p2);
                    document.add( new Paragraph(" "));

                    // Cria table
                    PdfPTable table = new PdfPTable(7);
                    table.setHeaderRows(1);
                    float[] widths = {20,10,10,10,14,19,7};
                    table.setWidths(widths);
                    table.setWidthPercentage(100);

                    // Cria celulas header
                    PdfPCell cell;
                    Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.DARK_GRAY);
                    cell = new PdfPCell(new Phrase("Documento", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Categoria", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Cadastro", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Validade", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Departamento", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Responsável", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Status", font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(6);
                    table.addCell(cell);

                    Font font3 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY);

                    for (Documento doc : docs) {
                        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
                        String dtCad = s.format(doc.getDtCadastro().getTime());
                        SimpleDateFormat s2 = new SimpleDateFormat("dd/MM/yyyy");
                        String dtVal = s2.format(doc.getDtValidade().getTime());

                        cell = new PdfPCell(new Phrase(doc.getTitulo(), font3));
                        cell.setPadding(2);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(doc.getCategoria().getCategoria(), font3));
                        cell.setPadding(2);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(dtCad, font3));
                        cell.setPadding(2);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(dtVal, font3));
                        cell.setPadding(2);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(doc.getDepartamento().getDepartamento(), font3));
                        cell.setPadding(2);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(doc.getUsuario().getNome() + " " +doc.getUsuario().getSobrenome(), font3));
                        cell.setPadding(2);
                        table.addCell(cell);

                        String status = (doc.getAtivo().equals("s"))?"Ativo":"Inativo";
                        cell = new PdfPCell(new Phrase(status, font3));
                        cell.setPadding(2);
                        table.addCell(cell);
                    }
                    document.add(table);
                    document.close();
                    
                    res.setContentLength(baos.size());
                    ServletOutputStream os = res.getOutputStream();
                    baos.writeTo(os);
                    os.flush();
                    os.close();                    
                }

            } else {
                view = "/views/relatorio/index.jsp";
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.forward(req, res);
            }

        } else {
            req.getSession().setAttribute("msg_erro", "Acesso restrito");
            view = "/sistema?c=Home&acao=login";
            res.sendRedirect(Utils.getBaseUrl(req) + view);
        }
    }
}
