package recursosHumanos.util;

import recursosHumanos.excecoes.CPFInvalidoException;

public class ValidaCPF {
	
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
	private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }
	
	public static boolean isValidCPF(String cpf) throws CPFInvalidoException{
		boolean result = false;
		if ((cpf==null) || (cpf.length()!=11)) {
			 throw new CPFInvalidoException(Mensagem.erro + Mensagem.cpfInvalido);
		};

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	    Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	    result = cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
		if(!result){
			throw new CPFInvalidoException(Mensagem.erro + Mensagem.cpfInvalido);
		}

		return result;
	}
	
}
