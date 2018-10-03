package br.com.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	
	/**
	 * Encripta valores com hash md5
	 * 
	 * @param String valor
	 * 
	 * @return String md5
	 */
	public static String toMd5(String valor) {
		String md5 = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");			
			BigInteger hashForm = new BigInteger(1, md.digest(valor.getBytes()));
			md5 = hashForm.toString(16);			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}
