package br.com.util;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Component;

@Component
public class Util implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean validarCPF(String cpf) 
	{ 
		cpf = removeMask(cpf);
		
		int d1, d2, digito1, digito2, resto, digitoCPF;  
		String  nDigResult;  
		
		d1 = d2 = digito1 = digito2 = resto = 0;  
		
		for (int nCount = 1; nCount < cpf.length() -1; nCount++)  
		{  
			digitoCPF = Integer.valueOf (cpf.substring(nCount -1, nCount)).intValue();  
			//multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.  
			d1 = d1 + ( 11 - nCount ) * digitoCPF;  
			//para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.  
			d2 = d2 + ( 12 - nCount ) * digitoCPF;  
		};
		
		//Primeiro resto da divisão por 11.  
		resto = (d1 % 11);
		
		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
		if (resto < 2)  
			digito1 = 0;  
		else  
			digito1 = 11 - resto;  
		
		d2 += 2 * digito1;  
		
		//Segundo resto da divisão por 11.  
		resto = (d2 % 11);  
		
		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
		if (resto < 2)  
			digito2 = 0;  
		else  
			digito2 = 11 - resto;  
		
		//Digito verificador do CPF que está sendo validado.  
		String nDigVerific = cpf.substring (cpf.length()-2, cpf.length());  
		
		//Concatenando o primeiro resto com o segundo.  
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);  
		
		//comparar o digito verificador do cpf com o primeiro resto + o segundo resto.  
		return nDigVerific.equals(nDigResult);  
	}
	
	public static boolean validarCNPJ(String str_cnpj) {
		if (str_cnpj.length() != 14)
			return false;

		str_cnpj = str_cnpj.replace(".", "");
		str_cnpj = str_cnpj.replace("/", "");
		str_cnpj = str_cnpj.replace("-", "");

		if (str_cnpj.equals("00000000000000"))
			return false;
		if (str_cnpj.equals("11111111111111"))
			return false;
		if (str_cnpj.equals("22222222222222"))
			return false;
		if (str_cnpj.equals("33333333333333"))
			return false;
		if (str_cnpj.equals("44444444444444"))
			return false;
		if (str_cnpj.equals("55555555555555"))
			return false;
		if (str_cnpj.equals("66666666666666"))
			return false;
		if (str_cnpj.equals("77777777777777"))
			return false;
		if (str_cnpj.equals("88888888888888"))
			return false;
		if (str_cnpj.equals("99999999999999"))
			return false;

		int soma = 0, dig;
		String cnpj_calc = str_cnpj.substring(0, 12);
		char[] chr_cnpj = str_cnpj.toCharArray();
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		return str_cnpj.equals(cnpj_calc);
	}
	
	/**
	 * Retira as máscaras de CPF, telefone e CEP. <BR>
	 * CPF:<BR>Entrada -> 620.620.620-34 , Saída -> 62062062034<BR>
	 * Telefone:<BR>Entrada -> (85)3222-6577 , Saída -> 8532226577<BR>
	 * CEP:<BR>Entrada -> 60.115-190 , Saída -> 60115190<BR>
	 * @param str String que será retirada a máscara.
	 * @return Retorna a string sem a máscara de formatação.
	 */
	public static String removeMask(String str) { 
 		if (str == null)
			return "";

		return str.replaceAll("[-/().]", "");
	}
	
	public static String addMask(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static boolean validarEmail(String email) {  
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	public static String limparTexto(String texto) {
		String comAcento = "áàãâäéèêëíìîïóòõôöúùûüçÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙÛÜÇ.'´`\"";
		String semAcento = "aaaaaeeeeiiiiooooouuuucAAAAAEEEEIIIIOOOOOUUUUC ";

		for (int i = 0; i < comAcento.length(); i++)
			if (i >= semAcento.length())
				texto = texto.replaceAll(String.valueOf(comAcento.charAt(i)),
						"");
			else
				texto = texto.replace(comAcento.charAt(i), semAcento.charAt(i));

		texto = retiraDuploEspaco(texto);

		return texto.toLowerCase();
	}

	public static String retiraDuploEspaco(String texto) {
		while (texto.indexOf("  ") > -1)
			texto = texto.replaceAll("  ", " ");

		return texto.trim();
	}
	
	public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    
    public static <T> List<T> difference(List<T> list1, List<T> list2) {
    	List<T> list = new ArrayList<T>(list1.size());
    	
    	list.addAll(list1);
    	list.retainAll(list2);
    	
    	return list;
    }
}
