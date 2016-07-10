package recursosHumanos.entidade;

import java.util.Date;

import recursosHumanos.enumeradores.EnumSexo;
import recursosHumanos.enumeradores.EnumTipoDocumentoPessoal;
import recursosHumanos.enumeradores.EnumTipoNacionalidade;

public class Candidato {
	private String nome;
	private Date dataNascimento;
	private EnumSexo sexo;
	private EnumTipoNacionalidade nacionalidade;
	private EnumTipoDocumentoPessoal tipoDocumentoPessoal;
	private String CPF;
	private String passaporte;
	private String documentoReservista;
	private Cargo cargo;


	public EnumTipoDocumentoPessoal getTipoDocumentoPessoal() {

		return tipoDocumentoPessoal;
	}

	public void setTipoDocumentoPessoal(EnumTipoDocumentoPessoal tipoDocumentoPessoal) {
		this.tipoDocumentoPessoal = tipoDocumentoPessoal;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public Date getDataNascimento() {

		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	public EnumTipoNacionalidade getNacionalidade() {

		return nacionalidade;
	}

	public void setNacionalidade(EnumTipoNacionalidade nacionalidade) {

		this.nacionalidade = nacionalidade;
	}

	public String getCPF() {

		return CPF;
	}

	public void setCPF(String cPF) {

		CPF = cPF;
	}

	public String getPassaporte() {

		return passaporte;
	}

	public void setPassaporte(String passaporte) {

		this.passaporte = passaporte;
	}

	public Cargo getCargo() {

		return cargo;
	}

	public void setCargo(Cargo cargo) {

		this.cargo = cargo;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public String getDocumentoReservista() {
		return documentoReservista;
	}

	public void setDocumentoReservista(String documentoReservista) {
		this.documentoReservista = documentoReservista;
	}
}
