package recursosHumanos.negocio;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import recursosHumanos.entidade.Candidato;
import recursosHumanos.entidade.Cargo;
import recursosHumanos.enumeradores.EnumTipoDocumentoHabilitacao;
import recursosHumanos.excecoes.CargoInvalidoException;
import recursosHumanos.excecoes.DocumentoHabilitacaoException;
import recursosHumanos.excecoes.SalarioInvalidoException;

public class CargoNegTest {

	private Cargo cargo = new Cargo();
	String motorista = "Motorista";
	String medico = "Médico";
	String engenheiro = "Engenheiro";
	String tecnicoInformatica = "Técnico em Informática";
	private CargoNeg cargoNeg = new CargoNeg();
	List<String> listaCargos;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//CT 32 - CE I(36) - validar descrição de cargo = null
	@Test
	public void validaDescricaoNula() {
		String descricaoCargo = null;
		cargo.setDescricao(descricaoCargo);
		try {
			cargoNeg.validaDescricao(cargo);
		} catch (Exception e){
			Assert.assertTrue(e instanceof CargoInvalidoException);
		}

	}

	//CT 33 - CE I(36) - validar descrição de cargo = " ";
	@Test
	public void validaDescricaoEmBranco() {
		cargo.setDescricao(" ");
		try {
			cargoNeg.validaDescricao(cargo);
		} catch (Exception e){
			Assert.assertTrue(e instanceof CargoInvalidoException);
		}

	}

	//CT 34 - CE I(36) - validar descrição de cargo com valor fora do conjunto dos valores válidos.
	@Test
	public void validaDescricaoValorForaConjunto() {
		cargo.setDescricao("Jardineiro");
		try {
			cargoNeg.validaDescricao(cargo);
		} catch (Exception e){
			Assert.assertTrue(e instanceof CargoInvalidoException);
		}

	}


	// CT 35 - CE V (33) = qualquer valor pertencente ao conjunto dos valores válidos
	@Test
	public void testValidaDescricaoValida() {
		cargo.setDescricao(motorista);
		try {
			Assert.assertTrue(cargoNeg.validaDescricao(cargo));
		} catch(Exception e){
			Assert.assertTrue(e == null);
		}
	}

	//CT 36
	@Test
	public void testValidaSalarioMotoristaMenor1000() {
		cargo.setDescricao(motorista);
		cargo.setSalario(999.99d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}

	}

    //CT 37
	@Test
	public void testValidaSalarioMotoristaMaior2000() {
		cargo.setDescricao(motorista);
		cargo.setSalario(2000.01d);
		try {
            cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	//CT 38
	@Test
	public void testValidaSalarioValidoMotorista(){
		cargo.setDescricao(motorista);
		cargo.setSalario(1500.00d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e == null);
		}
	}

	//CT39
	@Test
	public void testSalarioTecnicoInformaticaMenor2000(){
		cargo.setDescricao(tecnicoInformatica);
		cargo.setSalario(1999.99d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	//CT40
	@Test
	public void testSalarioTecnicoInformaticaMaior3000(){
		cargo.setDescricao(tecnicoInformatica);
		cargo.setSalario(3000.01d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	@Test
	public void testSalarioValidoTecnicoInformatica(){
		cargo.setDescricao(tecnicoInformatica);
		cargo.setSalario(2500.00d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e == null);
		}
	}

	@Test
	public void testSalarioMedicoMenor10000(){
		cargo.setDescricao(medico);
		cargo.setSalario(9999.99d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	@Test
	public void testSalarioMedicoMaior15000(){
		cargo.setDescricao(medico);
		cargo.setSalario(15000.01d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	@Test
	public void testSalarioValidoMedico(){
		cargo.setDescricao(medico);
		cargo.setSalario(12000.00d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			assertTrue(e == null);
		}
	}

	@Test
	public void testSalarioEngenheiroMenor5000(){
		cargo.setDescricao(engenheiro);
		cargo.setSalario(4999.99d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	@Test
	public void testSalarioEngenheiroMaior10000(){
		cargo.setDescricao(engenheiro);
		cargo.setSalario(10000.01d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SalarioInvalidoException);
		}
	}

	@Test
	public void testSalarioValidoEngenheiro(){
		cargo.setDescricao(engenheiro);
		cargo.setSalario(7500.00d);
		try {
			cargoNeg.validaSalario(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e == null);
		}
	}


	// motorista x cnh
	@Test
	public void testValidaDocumentoHabilitacaoMotoristaCNH() {
		try {
			cargo.setDescricao(motorista);
			cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CNH);
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			assertTrue(e == null);
		}

	}

	// motorista x crm
	@Test
	public void testValidaDocumentoHabilitacaoMotoristaCRM() {
		cargo.setDescricao(motorista);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CRM);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}

	}

	// motorista x crea
	@Test
	public void testValidaDocumentoHabilitacaoMotoristaCREA() {
		cargo.setDescricao(motorista);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CREA);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}

	}

	// Tecnico em Informatica x cnh
	@Test
	public void testValidaDocumentoHabilitacaoTecnicoInformaticaCNH() {
		cargo.setDescricao(tecnicoInformatica);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CNH);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}

	}

	// Tecnico em Informatica x crm
	@Test
	public void testValidaDocumentoHabilitacaoTecnicoInformaticaCRM() {
		cargo.setDescricao(tecnicoInformatica);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CRM);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}

	}

	// Tecnico em Informatica x crea
	@Test
	public void testValidaDocumentoHabilitacaoTecnicoInformaticaCREA() {
		cargo.setDescricao(tecnicoInformatica);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CREA);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}
	}

	// Engenheiro x cnh
	@Test
	public void testValidaDocumentoEngenheiroCNH() {
		cargo.setDescricao(engenheiro);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CNH);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}
	}

	// Engenheiro x crm
	@Test
	public void testValidaDocumentoHabilitacaoEngenheiroCRM() {
		cargo.setDescricao(engenheiro);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CRM);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}

	}

	// Engenheiro x crea
	@Test
	public void testValidaDocumentoHabilitacaoEngenheiroCREA() {
		cargo.setDescricao(engenheiro);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CREA);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e == null);
		}

	}

	// Medico x cnh
	@Test
	public void testValidaDocumentoMedicoCNH() {
		cargo.setDescricao(medico);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CNH);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}
	}

	// Medico x crm
	@Test
	public void testValidaDocumentoHabilitacaoMedicoCRM() {
		cargo.setDescricao(medico);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CRM);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e == null);
		}
	}

	// Medico x crea
	@Test
	public void testValidaDocumentoHabilitacaoMedicoCREA() {
		cargo.setDescricao(medico);
		cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CREA);
		try {
			cargoNeg.validaDocumentoHabilitacao(cargo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DocumentoHabilitacaoException);
		}
	}

	//testa ocupação de vaga, somente para validar a quantidade de vagas do cargo engenheiro
	@Test
	public void testaOcupacaoVagasCargoEngenheiro(){
		Candidato candidato = new Candidato();
		cargo.setDescricao(engenheiro);
		cargoNeg.configuraQuantidadeVagas(cargo);
        int quantidadeVagas = cargo.getQuantidadeVagas();
		for(int i = 0; i < quantidadeVagas; i++ ){
			try {
				cargoNeg.ocupaVaga(cargo, candidato);
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
		Assert.assertTrue(cargo.getQuantidadeVagas() == 0);
	}

    //testa ocupação de vaga, somente para validar a quantidade de vagas do cargo Técnico em Informática
    @Test
    public void testaOcupacaoVagasCargoTecnicoInformatica(){
        Candidato candidato = new Candidato();
        cargo.setDescricao(tecnicoInformatica);
        cargoNeg.configuraQuantidadeVagas(cargo);
        int quantidadeVagas = cargo.getQuantidadeVagas();
        for(int i = 0; i < quantidadeVagas; i++ ){
            try {
                cargoNeg.ocupaVaga(cargo, candidato);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        Assert.assertTrue(cargo.getQuantidadeVagas() == 0);
    }

    //testa ocupação de vaga, somente para validar a quantidade de vagas do cargo Motorista
    @Test
    public void testaOcupacaoVagasCargoMotorista(){
        Candidato candidato = new Candidato();
        cargo.setDescricao(motorista);
        cargoNeg.configuraQuantidadeVagas(cargo);
        int quantidadeVagas = cargo.getQuantidadeVagas();
        for(int i = 0; i < quantidadeVagas; i++ ){
            try {
                cargoNeg.ocupaVaga(cargo, candidato);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        Assert.assertTrue(cargo.getQuantidadeVagas() == 0);
    }

    //testa ocupação de vaga, somente para validar a quantidade de vagas do cargo Motorista
    @Test
    public void testaOcupacaoVagasCargoMedico(){
        Candidato candidato = new Candidato();
        cargo.setDescricao(medico);
        cargoNeg.configuraQuantidadeVagas(cargo);
        int quantidadeVagas = cargo.getQuantidadeVagas();
        for(int i = 0; i < quantidadeVagas; i++ ){
            try {
                cargoNeg.ocupaVaga(cargo, candidato);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        Assert.assertTrue(cargo.getQuantidadeVagas() == 0);
    }
}
