/*
 * Enumerador.java
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


/**
 * Interface que define as operações implementadas pelos enums definidos no sistema.
 * 
 * @author Gilmar Arantes.
 */ 
public interface Enumerador {
	/**	 
	 * Método que retorna o valor abreviado do atributo.
	 * @return 
	 * O valor abreviado do atributo
	 */
	String getAbreviado();
	/**	 
	 * Método que retorna a descrição do atributo.
	 * @return 
	 * A descrição do atributo
	 */
	String getDescricao();
}