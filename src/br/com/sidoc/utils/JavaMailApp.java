/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sidoc.utils;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Andre
 */
public class JavaMailApp {

    private final String email;
    private final String name;
    private final String[] dadosMsg;
    
    public JavaMailApp(String email, String name, String[] msg){
        this.email = email;
        this.name  = name;
        this.dadosMsg = msg;
    }

    public void sendMail() throws RuntimeException{
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email@gmail.com", "password");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.email)); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(this.email+","+this.email);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Contato Sidoc");//Assunto
//            message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
            // Send the actual HTML message, as big as you like
	   message.setContent(
              "<h1>Obrigado por entrar em contato</h1>"+
              "<p>Estamos analisando sua mensagem e em breve retornaremos.</p><hr />"+
              "<p>Nome: "+this.dadosMsg[0]+"</p>"+
              "<p>Email: "+this.dadosMsg[1]+"</p>"+
              "<p>Departamento: "+this.dadosMsg[2]+"</p>"+
              "<p>Comentário: "+this.dadosMsg[3]+"</p>",
             "text/html");
           MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
           
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
