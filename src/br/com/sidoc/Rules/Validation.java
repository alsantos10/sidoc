package br.com.sidoc.Rules;

/**
 * Classe que vai servir de base para as validações
 * @author Andre
 *
 */
public class Validation {
	public static boolean minCaracters(String s, int min){
		if(min<=0) min = 3;
		if(s.length() >= min)
			return true;
		return false;
	}
	public static boolean maxCaracters(String s, int max){
		if(max<=0) max = 10;
		if(s.length() <= max)
			return true;
		return false;
	}
	public static boolean minMaxCaracters(String s, int min, int max){
		boolean val;
		val = Validation.maxCaracters(s, max);
		val = Validation.minCaracters(s, min);
		return val;
	}
	
}
