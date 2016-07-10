package recursosHumanos.enumeradores;
/**
 * Enum definido para manter os tipos de documentos de habilitação.
 * 
 * @author Gilmar Arantes
 *
 */
public enum EnumTipoDocumentoHabilitacao implements Enumerador{
	/** * */
	CNH("CNH","Carteira Nacional de Habilitacao"),
	/** * */
	CRM("CRM","Conselho Regional de Medicina"),
	/** * */
	CREA("CREA","Conselho Regional de Engenharia e Arquitetura");
	
	/**
	 * Valor abreviado.
	 */
	private final String abreviado;
	/**
	 * Descrição do valor.
	 */
	private final String descricao;
	
	/**
	 * Construtor parametrizado com o valor abreviado e a descrição.
	 * @param abreviado
	 * @param descricao
	 */
	private EnumTipoDocumentoHabilitacao(String abreviado, String descricao) {
		this.abreviado = abreviado;
		this.descricao = descricao;
		EnumUtilities.setEnumName(this, this.abreviado);
	}
	
	/**	 
	 * Método que retorna o valor abreviado do atributo.
	 * @return 
	 * O valor abreviado do atributo
	 */
	public String getAbreviado() {
		return abreviado;
	}
	/**	 
	 * Método que retorna a descrição do atributo.
	 * @return 
	 * A descrição do atributo
	 */
	
	public String getDescricao() {
		return descricao;
	}
	
}
