/* EnumUtilities.java
 * 
 * Criado pela equipe de desenvolvimento do projeto RHNet.
 * Integrante do sistema posse-net.
 * 
 * Segplan 2013 Secretaria de Gest�o e Planejamento do estado de Goi�s.
 * 
 * Todos os direitos Copyright est�o reservados.
 *
 */
package recursosHumanos.enumeradores;

import java.lang.reflect.Field;
/**
 * Enum Utilities
 * 
 * @author Gilmar Arantes.
 *
 */
public class EnumUtilities {
	/***/
	public static void setEnumName(Enum<?> enumerador, String val) {
		try {
			Field campoName = Enum.class.getDeclaredField("name");
			campoName.setAccessible(true);
			campoName.set(enumerador, val);
			campoName.setAccessible(false);
		} catch (Exception e) {
			throw new RuntimeException("Houve algum erro durante a criação do enum " + enumerador + " com valor = " + val);
		}
	}
}
