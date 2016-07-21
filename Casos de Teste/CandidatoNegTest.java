package recursosHumanos.negocio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import recursosHumanos.entidade.Candidato;
import recursosHumanos.enumeradores.EnumSexo;
import recursosHumanos.enumeradores.EnumTipoDocumentoPessoal;
import recursosHumanos.enumeradores.EnumTipoNacionalidade;
import recursosHumanos.excecoes.CPFInvalidoException;
import recursosHumanos.excecoes.DataInvalidaException;
import recursosHumanos.excecoes.DataNaoInformadaException;
import recursosHumanos.excecoes.DocumentoPessoalInvalidoException;
import recursosHumanos.excecoes.IdadeInvalidadeException;
import recursosHumanos.excecoes.NacionalidadeInvalidaException;
import recursosHumanos.excecoes.NomeInvalidoException;
import recursosHumanos.excecoes.PassaporteInvalidoException;
import recursosHumanos.excecoes.SexoInvalidoException;

public class CandidatoNegTest {
	
	private CandidatoNeg candidatoNeg = new CandidatoNeg();
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testNomeNull() throws NomeInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNome(null);
		thrown.expect(NomeInvalidoException.class);
		boolean nomeValido = candidatoNeg.validaNome(candidato);
		assertFalse(nomeValido);
	}
	
	/**
	 * Antonio Umberto
	 */
	@Test
	public void testNomeVazio() throws NomeInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNome("");
		thrown.expect(NomeInvalidoException.class);
		boolean nomeValido = candidatoNeg.validaNome(candidato);
		assertFalse(nomeValido);
	}
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testNomeMenorQue5Caracteres() throws NomeInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNome("abcd");
		thrown.expect(NomeInvalidoException.class);
		boolean nomeValido = candidatoNeg.validaNome(candidato);
		assertFalse(nomeValido);
	}
	
	/**
	 * Herbert Nunes
	 * @throws SexoInvalidoException
	 */
	@Test
	public void testNomeMaiorQue100Caracteres() throws NomeInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNome("jfsdljfldsjflsdjflsdjfljsdfljsdlkfjsdlfjsdljfsdlkjf"
				+ "fjdlskjflsdjfljsdfjsdlfjsdlfjsdlkjfklsdjflsdjflsjdfdsfjsdlfj"
				+ "dfjsdljflkdsjflksdjfldsjfljsdfljsdflsdjflksdjflkjsdlfjsjfdk"
				+ "kldfjdslkjflsdjflsdjflksdjfklsdjflksdjflksdjfklsdjfklsdjf"
				+ "lsdflksdjflksjdflkjsdflkjsdlkfjsdlkfjsdlkfjsdlkfjsdlkjfsld");
		thrown.expect(NomeInvalidoException.class);
		boolean nomeValido = candidatoNeg.validaNome(candidato);
		assertFalse(nomeValido);
	}
	
	/**
	 * Alexandre de Matos
	 */
	@Test
	public void testNomeComNumero() throws NomeInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNome("132456");
		thrown.expect(NomeInvalidoException.class);
		boolean nomeValido = candidatoNeg.validaNome(candidato);
		assertFalse(nomeValido);
	}
	
	/**
	 * Antonio Umberto
	 */
	@Test
	public void testNomeValido() throws NomeInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNome("abcdef");
		boolean nomeValido = candidatoNeg.validaNome(candidato);
		assertTrue(nomeValido);
	}
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testDataNascimentoNull() throws DataNaoInformadaException, DataInvalidaException, ParseException {
		Candidato candidato = new Candidato();
		candidato.setDataNascimento(null);
		thrown.expect(DataNaoInformadaException.class);
		boolean dataValida = candidatoNeg.validaDataNascimento(candidato);
		assertFalse(dataValida);
	}
	
	/**
	 * Herbert Nunes
	 * @throws SexoInvalidoException
	 */
	@Test
	public void testDataNascimentoValida() throws DataNaoInformadaException, DataInvalidaException, ParseException {
		Candidato candidato = new Candidato();
		candidato.setDataNascimento(new Date());
		boolean dataValida = candidatoNeg.validaDataNascimento(candidato);
		assertTrue(dataValida);
	}
	
	/**
	 * Alexandre de Matos
	 */
	@Test
	public void testNacionalidadeNull() throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, CPFInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNacionalidade(null);
		thrown.expect(NacionalidadeInvalidaException.class);
		boolean nacionalidadeValida = candidatoNeg.validarCPF(candidato);
		assertFalse(nacionalidadeValida);
	}
	
	/**
	 * Antonio Umberto
	 */
	@Test
	public void testNacionalidadeBrasileiraSemCpf() throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, CPFInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNacionalidade(EnumTipoNacionalidade.BRASILEIRA);
		candidato.setTipoDocumentoPessoal(EnumTipoDocumentoPessoal.PASSAPORTE);
		thrown.expect(DocumentoPessoalInvalidoException.class);
		boolean valido = candidatoNeg.validarCPF(candidato);
		assertFalse(valido);
	}
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testNacionalidadeEstrangeiraSemPassaporte() throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, CPFInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNacionalidade(EnumTipoNacionalidade.ESTRANGEIRA);
		candidato.setTipoDocumentoPessoal(EnumTipoDocumentoPessoal.CPF);
		thrown.expect(DocumentoPessoalInvalidoException.class);
		boolean valido = candidatoNeg.validarCPF(candidato);
		assertFalse(valido);
	}
	
	/**
	 * Herbert Nunes
	 * @throws SexoInvalidoException
	 */
	@Test
	@Ignore
	public void testCpfInvalido() throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, CPFInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNacionalidade(EnumTipoNacionalidade.BRASILEIRA);
		candidato.setTipoDocumentoPessoal(EnumTipoDocumentoPessoal.CPF);
		candidato.setCPF("0439620813");
		thrown.expect(CPFInvalidoException.class);
		boolean valido = candidatoNeg.validarCPF(candidato);
		assertFalse(valido);
	}
	
	/**
	 * Alexandre de Matos
	 */
	@Test
	public void testIdadeMenorQue18() throws IdadeInvalidadeException, ParseException {
		Candidato candidato = new Candidato();
		candidato.setDataNascimento(new Date());
		thrown.expect(IdadeInvalidadeException.class);
		boolean valido = candidatoNeg.validaIdade(candidato);
		assertFalse(valido);
	}
	
	/**
	 * Antonio Umberto
	 */
	@Test
	public void testIdadeMaiorQue75() throws IdadeInvalidadeException, ParseException {
		Candidato candidato = new Candidato();
		Calendar calendar = new GregorianCalendar();
		calendar.set(1924, 3, 3);
		candidato.setDataNascimento(calendar.getTime());
		thrown.expect(IdadeInvalidadeException.class);
		boolean valido = candidatoNeg.validaIdade(candidato);
		assertFalse(valido);
	}
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testIdadeValida() throws IdadeInvalidadeException, ParseException {
		Candidato candidato = new Candidato();
		Calendar calendar = new GregorianCalendar();
		calendar.set(1994, 3, 3);
		candidato.setDataNascimento(calendar.getTime());
		boolean valido = candidatoNeg.validaIdade(candidato);
		assertTrue(valido);
	}
	
	/**
	 * Herbert Nunes
	 * @throws SexoInvalidoException
	 */
	@Test
	@Ignore
	public void testPassaporteNull() throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, PassaporteInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNacionalidade(EnumTipoNacionalidade.ESTRANGEIRA);
		candidato.setTipoDocumentoPessoal(EnumTipoDocumentoPessoal.PASSAPORTE);
		candidato.setPassaporte(null);
		thrown.expect(PassaporteInvalidoException.class);
		boolean valido = candidatoNeg.validaPassaporte(candidato);
		assertFalse(valido);
	}
	
	/**
	 * Alexandre de Matos
	 */
	@Test
	@Ignore
	public void testPassaporteVazio() throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, PassaporteInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setNacionalidade(EnumTipoNacionalidade.ESTRANGEIRA);
		candidato.setTipoDocumentoPessoal(EnumTipoDocumentoPessoal.PASSAPORTE);
		candidato.setPassaporte(" ");
		thrown.expect(PassaporteInvalidoException.class);
		boolean valido = candidatoNeg.validaPassaporte(candidato);
		assertFalse(valido);
	}
	
	/**
	 * Antonio Umberto
	 */
	@Test
	public void testSexoNull() throws SexoInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setSexo(null);
		thrown.expect(SexoInvalidoException.class);
		boolean valido = candidatoNeg.validaSexo(candidato);
		assertFalse(valido);
	}
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testSexoMasculino() throws SexoInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setSexo(EnumSexo.M);
		boolean valido = candidatoNeg.validaSexo(candidato);
		assertTrue(valido);
	}
	
	/**
	 * Herbert Nunes
	 * @throws SexoInvalidoException
	 */
	@Test
	public void testSexoFeminino() throws SexoInvalidoException {
		Candidato candidato = new Candidato();
		candidato.setSexo(EnumSexo.F);
		boolean valido = candidatoNeg.validaSexo(candidato);
		assertTrue(valido);
	}
	
	/**
	 * Alexandre de Matos
	 */
	@Test
	public void testNecessitaReservistaParaMulher() {
		Candidato candidato = new Candidato();
		candidato.setSexo(EnumSexo.F);
		boolean valido = candidatoNeg.validaNecessidadeReservista(candidato);
		assertFalse(valido);
	}
	
	/**
	 * Antonio Umberto
	 */
	@Test
	public void testNecessitaReservistaParaHomemMaiorQue45() {
		Candidato candidato = new Candidato();
		candidato.setSexo(EnumSexo.M);
		Calendar calendar = new GregorianCalendar();
		calendar.set(1924, 3, 3);
		candidato.setDataNascimento(calendar.getTime());
		boolean valido = candidatoNeg.validaNecessidadeReservista(candidato);
		assertFalse(valido);
	}
	
	/**
	 * @author Isaias Tavares
	 */
	@Test
	public void testNecessitaReservistaParaHomemMenorQue45() {
		Candidato candidato = new Candidato();
		candidato.setSexo(EnumSexo.M);
		Calendar calendar = new GregorianCalendar();
		calendar.set(1994, 3, 3);
		candidato.setDataNascimento(calendar.getTime());
		boolean valido = candidatoNeg.validaNecessidadeReservista(candidato);
		assertTrue(valido);
	}
	
}
