package br.com.sidoc.utils;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

public class Utils {

	  public static String BASE_ABSOLUTE_PATH =
		   "C:/Users/Vilma/Documents/Projetos_git/sidoc/WebContent/";
	  

	public static String getRelativeUrl(
		    HttpServletRequest request ) {

		    String baseUrl = null;

		    if ( ( request.getServerPort() == 80 ) ||
		         ( request.getServerPort() == 443 ) )
		      baseUrl =
		        request.getScheme() + "://" +
		        request.getServerName() +
		        request.getContextPath();
		    else
		      baseUrl =
		        request.getScheme() + "://" +
		        request.getServerName() + ":" + request.getServerPort() +
		        request.getContextPath();

		    StringBuffer buf = request.getRequestURL();

		    if ( request.getQueryString() != null ) {
		      buf.append( "?" );
		      buf.append( request.getQueryString() );
		    }

		    return buf.substring( baseUrl.length() );
		  }

	public static String getBaseUrl(
			HttpServletRequest request ) {
	    if ( ( request.getServerPort() == 80 ) ||
	         ( request.getServerPort() == 443 ) )
	      return request.getScheme() + "://" +
	             request.getServerName() +
	             request.getContextPath();
	    else
	      return request.getScheme() + "://" +
	             request.getServerName() + ":" + request.getServerPort() +
	             request.getContextPath();
	  }

	  /**
	   * Returns the file specified by <tt>path</tt> as returned by
	   * <tt>ServletContext.getRealPath()</tt>.
	   */
	  public static File getRealFile(
	    HttpServletRequest request,
	    String path ) {

	    return
	      new File( request.getSession().getServletContext().getRealPath( path ) );
	  }
	  

	  public static String removerAcentos(String str) {
		  String strNova = "";
		  strNova = str.trim().toLowerCase();
		  strNova = strNova.replaceAll("\\s+", "_");
		  strNova = strNova.replaceAll("-+", "_");
		  strNova = strNova.replaceAll("[^0-9a-zA-Z_]+?", "");
		  strNova = strNova.replaceAll("_+", "_");
		  return strNova;
	  }
		
	  public static String md5(String senha) throws NoSuchAlgorithmException{
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(),0,senha.length());
			BigInteger i = new BigInteger(1, m.digest()); 
			//Formatando o resuldado em 32 caracteres, completando com 0 caso falte 
			String s = String.format("%1$032X", i); 
			return s;
		}
	 
}
