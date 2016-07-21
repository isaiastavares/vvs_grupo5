package cargo.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import recursosHumanos.entidade.Candidato;
import recursosHumanos.entidade.Cargo;
import recursosHumanos.enumeradores.EnumTipoDocumentoHabilitacao;
import recursosHumanos.excecoes.CargoInvalidoException;
import recursosHumanos.excecoes.DocumentoHabilitacaoException;
import recursosHumanos.excecoes.SalarioInvalidoException;
import recursosHumanos.excecoes.VagaIndisponivelException;
import recursosHumanos.negocio.CargoNeg;
import recursosHumanos.util.Mensagem;

public class CargoNegTest {
	
	
  @Test
  public void testaValidaDescricao() throws CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Engenheiro");
	  
	  Assert.assertTrue(cargoNeg.validaDescricao(cargo));
  }
  
  @Test
  public void testaValidaDescricaoInvalida() {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Carpinteiro");
	  
	  try {
		  cargoNeg.validaDescricao(cargo);
	  }catch (CargoInvalidoException e) {
		  Assert.assertEquals("Cargo inválido!", Mensagem.cargoInvalido);
	  }
  }
  
  @Test
  public void testaQuantidadeVagasInicialEngenheiro() throws CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Engenheiro");
	  
	  int quantidadeVagas = cargoNeg.obtemQuantidadeVagasIncialCargo(cargo);
	  
	  Assert.assertEquals(quantidadeVagas, 50);
  }
  
  @Test
  public void testaQuantidadeVagasInicialMedico() throws CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Médico");
	  
	  int quantidadeVagas = cargoNeg.obtemQuantidadeVagasIncialCargo(cargo);
	  
	  Assert.assertEquals(quantidadeVagas, 10);
  }
  
  
  @Test
  public void testaQuantidadeVagasInicialMotorista() throws CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Motorista");
	  
	  int quantidadeVagas = cargoNeg.obtemQuantidadeVagasIncialCargo(cargo);
	  
	  Assert.assertEquals(quantidadeVagas, 15);
  }
  
  @Test
  public void testaQuantidadeVagasInicialTecnicoTI() throws CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Técnico em Informática");
	  
	  int quantidadeVagas = cargoNeg.obtemQuantidadeVagasIncialCargo(cargo);
	  
	  Assert.assertEquals(quantidadeVagas, 20);
  }
  
  @Test
  public void testaQuantidadeVagasInvalida() {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Carpinteiro");
	  
	  try {
		  cargoNeg.validaDescricao(cargo);
	  }catch (CargoInvalidoException e) {
		  Assert.assertEquals("ERRO! Cargo inválido!", Mensagem.erro + Mensagem.cargoInvalido);
	  }
  }
  
  @Test
  public void testaValidaDocumentoHabilitacaoMedico() throws CargoInvalidoException, DocumentoHabilitacaoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Médico");
	  cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CRM);
	  
	  Assert.assertTrue(cargoNeg.validaDocumentoHabilitacao(cargo));
  }
  
  @Test
  public void testaValidaDocumentoHabilitacaoEgenheiro() throws CargoInvalidoException, DocumentoHabilitacaoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Engenheiro");
	  cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CREA);
	  
	  Assert.assertTrue(cargoNeg.validaDocumentoHabilitacao(cargo));
  }
  
  @Test
  public void testaValidaDocumentoHabilitacaoMotorista() throws CargoInvalidoException, DocumentoHabilitacaoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Motorista");
	  cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CNH);
	  
	  Assert.assertTrue(cargoNeg.validaDocumentoHabilitacao(cargo));
  }
  
  @Test
  public void testaValidaDocumentoHabilitacaoTecnicoInformatica() throws CargoInvalidoException, DocumentoHabilitacaoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Técnico em Informática");
	  
	  Assert.assertTrue(cargoNeg.validaDocumentoHabilitacao(cargo));
  }
  
  
  @Test
  public void testaValidaSalarioMedico() throws SalarioInvalidoException, CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Médico");
	  cargo.setSalario(9000);
	  
	  Assert.assertTrue(cargoNeg.validaSalario(cargo));
  }
  
  @Test
  public void testaValidaSalarioEngenheiro() throws SalarioInvalidoException, CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Engenheiro");
	  cargo.setSalario(4000);
	  
	  Assert.assertTrue(cargoNeg.validaSalario(cargo));
  }
  

  @Test
  public void testaValidaSalarioTecnicaInformatica() throws SalarioInvalidoException, CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Técnico em Informática");
	  cargo.setSalario(2000);
	  
	  Assert.assertTrue(cargoNeg.validaSalario(cargo));
  }
  
  @Test
  public void testaValidaSalarioMotorista() throws SalarioInvalidoException, CargoInvalidoException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Motorista");
	  cargo.setSalario(1500);
	  
	  Assert.assertTrue(cargoNeg.validaSalario(cargo));
  }
  
  @Test
  public void testaOcupaVagaMedico() throws SalarioInvalidoException, CargoInvalidoException, VagaIndisponivelException {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Médico");
	  
	  Candidato candidato  = new Candidato();
	  candidato.setNome("Lucas");
	  
	  cargoNeg.configuraQuantidadeVagas(cargo);
	  
	  Assert.assertTrue(cargoNeg.ocupaVaga(cargo, candidato));
  }
  
  @Test
  public void testaValidaCargo() throws Exception {
	  CargoNeg cargoNeg = new CargoNeg();
	  Cargo cargo = new Cargo();
	  cargo.setDescricao("Médico");
	  cargo.setSalario(9000);
	  cargo.setTipoDocumentoHabilitacao(EnumTipoDocumentoHabilitacao.CRM);
	  
	  cargoNeg.configuraQuantidadeVagas(cargo);
	  
	  
	  Assert.assertTrue(cargoNeg.validaCargo(cargo));
  }

}
