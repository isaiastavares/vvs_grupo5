package recursosHumanos.negocio;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import recursosHumanos.entidade.Candidato;
import recursosHumanos.enumeradores.EnumSexo;
import recursosHumanos.enumeradores.EnumTipoDocumentoPessoal;
import recursosHumanos.enumeradores.EnumTipoNacionalidade;
import recursosHumanos.excecoes.DataInvalidaException;
import recursosHumanos.excecoes.DocumentoReservistaInvalidoException;
import recursosHumanos.excecoes.IdadeInvalidadeException;
import recursosHumanos.excecoes.NacionalidadeInvalidaException;
import recursosHumanos.excecoes.NomeInvalidoException;
import recursosHumanos.excecoes.SexoInvalidoException;
import recursosHumanos.util.DataUtils;

public class CandidatoNegTest {

    EnumTipoNacionalidade tipoNacionalidade = null;
    EnumTipoDocumentoPessoal tipoDocumentoPessoal = null;
    String numeroCPF = null;
    String numeroPassaporte = null;
	EnumSexo sexo = null;
	String numeroDocumentoReservista = null;
	Candidato candidato = new Candidato();
	CandidatoNeg candidatoNeg = new CandidatoNeg();

	public CandidatoNegTest(){
		/*try {
			dataNascimento = DataUtils.formataData("29/02/2016");
		}catch (Exception e){
			e.printStackTrace();
		}*/
	}




	//CT1 - CE I(1) = nome nulo
	@Test
	public void testValidaNomeNulo() {
		String nome = null;
        candidato.setNome(nome);
		try {
			candidatoNeg.validaNome(candidato);
		}catch(Exception e){
			Assert.assertTrue(e instanceof NomeInvalidoException);
		}

	}

	//CT2 - CE I(2) = nome não informado
	@Test
	public void testValidaNomeNaoInformado() {
        candidato.setNome(" ");
		try {
			candidatoNeg.validaNome(candidato);
		}catch(Exception e){
			Assert.assertTrue(e instanceof NomeInvalidoException);
		}

	}

	//CT3 - CE I(3) = nome com caractere especial
	@Test
	public void testaNomeComCaractereEspecial(){
		candidato.setNome("João S@antana da Sou&a");
		try {
			candidatoNeg.validaNome(candidato);
		} catch(Exception e){
			Assert.assertTrue(e instanceof NomeInvalidoException);
		}
	}

	//CT4 - CE I(4) - Nome com menos de cinco posições
	@Test
	public void testaNomeComMenosDeCincoPosicoes(){
		candidato.setNome("John");
		try{
			candidatoNeg.validaNome(candidato);
		}catch (Exception e){
			Assert.assertTrue(e instanceof NomeInvalidoException);
		}
	}

	//CT5 - CE i(5) nome com mais de cem posições
	public void testaNomeComMaisDeCemPosicoes(){
		candidato.setNome("aaaaaaaaaaaaaaaaaaaaaBBBBBBBBBBBBBBBBBBBBBBBbbbbbbwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwiiiiiiiiiiiiiiiiiiiiiiiiiiiipppppppppppppppppppppppooooooooooooooo");
		try {
			candidatoNeg.validaNome(candidato);
		} catch(Exception e){
			Assert.assertTrue(e instanceof NomeInvalidoException);
		}
	}

	//CT6 - CE V(6) = nome válido
	@Test
	public void testValidaNomeValido() {
		candidato.setNome("Antonio Carlos de Sousa");
		try {
			Assert.assertTrue(candidatoNeg.validaNome(candidato));
		}catch(Exception e){
			Assert.assertTrue(e == null);
		}

	}

	//CT7 - CE I(7) = Data de Nascimento nula
	@Test
	public void testValidaDataNascimentoNula() {
		Date dataNasc = null;
        candidato.setDataNascimento(dataNasc);
		try {
			candidatoNeg.validaDataNascimento(candidato);
		} catch(Exception e){
			//Assert.assertTrue(e instanceof DataNaoInformadaException);

		}
	}

	//CT8 - CE I(8) = Data de Nascimento inválida
	@Test
	public void testValidaDataNascimentoInvalida() {
		try {
            candidato.setDataNascimento(DataUtils.formataData("30/02/2016"));
			candidatoNeg.validaDataNascimento(candidato);
		}catch(Exception e){
			Assert.assertTrue(e instanceof DataInvalidaException);
		}
	}

	//CT9 - CE I(9) = Data de Nascimento válida com idade < 18 anos
	@Test
	public void testValidaDataNascimentoValidaComIdadeInferiorDezoitoAnos() {
		try {
            candidato.setDataNascimento(DataUtils.formataData("20/10/2000"));
			candidatoNeg.validaIdade(candidato);
		} catch(Exception e ){
			Assert.assertTrue(e instanceof IdadeInvalidadeException);
		}

	}

	//CT10 - CE I(10) - Data de Nascimento válida com idade maior que 75 anos
	@Test
	public void testaValidaDataNascimentoValidadeComIdadeSuperiorSetentaCincoAnos(){
		try {
            candidato.setDataNascimento(DataUtils.formataData("15/03/1940"));
			candidatoNeg.validaIdade(candidato);
		} catch (Exception e){
			Assert.assertTrue(e instanceof IdadeInvalidadeException);
		}
	}

	//CT11 - CE V(11) - Data de Nascimento Válida com Idade Válida
	@Test
	public void testaDataNascimentoValidaIdadeValida(){
		try {
            candidato.setDataNascimento(DataUtils.formataData("25/07/1965"));
			Assert.assertTrue(candidatoNeg.validaIdade(candidato));
		} catch (Exception e){
			Assert.assertTrue(e == null);
		}
	}

	@Test
	public void validaSexoNaoInformado(){
		candidato.setSexo(sexo);
		try {
			candidatoNeg.validaSexo(candidato);
		}catch (Exception e){
			Assert.assertTrue(e instanceof SexoInvalidoException);
		}
	}

    @Test
    public void validaSexoFeminino(){
        sexo = EnumSexo.F;
        candidato.setSexo(sexo);
        try {
            candidatoNeg.validaSexo(candidato);
        }catch (Exception e){
            Assert.assertTrue(e == null);
            //System.out.println(e.getClass());
        }
    }

    @Test
    public void validaSexoMasculino(){
        sexo = EnumSexo.M;
        candidato.setSexo(sexo);
        try {
            candidatoNeg.validaSexo(candidato);
        }catch (Exception e){
            Assert.assertTrue(e == null);
            //System.out.println(e.getClass());
        }
    }

    /*
    validar documento de reservista no cenário em que este documento não é obrigatório, pois o candidato é do sexo feminino
     */
    @Test
    public void validaDocumentoReservista(){
        candidato.setSexo(EnumSexo.F);
        try {
            candidatoNeg.validaDocumentoReservista(candidato);
        } catch (Exception e){
            Assert.assertTrue(e == null);
        }
    }

    /*
    validar documento de reservista no cenário em que este documento não é obrigatório, pois o candidato é do sexo masculino e possui idade superior a 45 anos.
     */
    @Test
    public void validaDocumentoReservista1(){
        try {
            candidato.setSexo(EnumSexo.M);
            candidato.setDataNascimento(DataUtils.formataData("27/06/1967"));
            candidatoNeg.validaDocumentoReservista(candidato);
        } catch (Exception e){
            Assert.assertTrue(e == null);
        }
    }

    /*
    validar documento de reservista no cenário em que este documento é obrigatório e não foi informado
     */
    @Test
    public void validaDocumentoReservista2(){
        try {
            candidato.setSexo(EnumSexo.M);
            candidato.setDataNascimento(DataUtils.formataData("27/06/1972"));
            candidatoNeg.validaDocumentoReservista(candidato);
        } catch (Exception e){
            Assert.assertTrue(e instanceof DocumentoReservistaInvalidoException);
        }

    }

    /*
    validar documento de reservista no cenário em que este documento é obrigatório e foi informado
     */
    @Test
    public void validaDocumentoReservista3(){
        try {
            numeroDocumentoReservista = "123456";
            candidato.setSexo(EnumSexo.M);
            candidato.setDataNascimento(DataUtils.formataData("27/06/1972"));
            candidato.setDocumentoReservista(numeroDocumentoReservista);
            candidatoNeg.validaDocumentoReservista(candidato);
        } catch (Exception e){
            Assert.assertTrue(e == null);
        }

    }


	/*CT12 - CE I(16)
	- Tipo de Nacionalidade null
	- Tipo de Documento = NULL
	- Número do CPF = NULL;
	 */
	@Test
	public void testaValidarCPF1(){
        candidato.setNacionalidade(tipoNacionalidade);
        candidato.setTipoDocumentoPessoal(tipoDocumentoPessoal);
        candidato.setCPF(numeroCPF);
		try {
			candidatoNeg.validarCPF(candidato);
		}catch (Exception e){
			Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
		}
	}

	/*CT13 - CE I(17)
	- Tipo de Nacionalidade null
	- Tipo de Documento = null
	- Número do CPF = em branco;
	 */
	@Test
	public void testaValidarCPF2(){
		numeroCPF = " ";
        candidato.setNacionalidade(tipoNacionalidade);
        candidato.setTipoDocumentoPessoal(tipoDocumentoPessoal);
        candidato.setCPF(numeroCPF);
		try {
			candidatoNeg.validarCPF(candidato);
		}catch (Exception e){
			Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
		}
	}

    /*CT14 - CE I(18)
	- Tipo de Nacionalidade null
	- Tipo de Documento = null
	- Número do CPF = inválido;
	 */
    @Test
    public void testaValidarCPF3(){
        numeroCPF = "33462461169";
        candidato.setNacionalidade(tipoNacionalidade);
        candidato.setTipoDocumentoPessoal(tipoDocumentoPessoal);
        candidato.setCPF(numeroCPF);
        try {
            candidatoNeg.validarCPF(candidato);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

    /*CT15 - CE I(19)
	- Tipo de Nacionalidade null
	- Tipo de Documento = null
	- Número do cpf = válido;*/

    @Test
    public void testaValidarCPF4(){
        numeroCPF = "33462461168";
        candidato.setNacionalidade(tipoNacionalidade);
        candidato.setTipoDocumentoPessoal(tipoDocumentoPessoal);
        candidato.setCPF(numeroCPF);
        try {
            candidatoNeg.validaPassaporte(candidato);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

    /*CT16 - CE I(20)
	- Tipo de Nacionalidade = null
	- Tipo de Documento = null
	- Número do PASSAPORTE = null;*/


    @Test
    public void testaValidarPassaporte(){
        candidato.setNacionalidade(tipoNacionalidade);
        candidato.setTipoDocumentoPessoal(tipoDocumentoPessoal);
        candidato.setPassaporte(numeroPassaporte);
        try {
            candidatoNeg.validaPassaporte(candidato);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }


    /*CT17 - CE I(21)
	- Tipo de Nacionalidade null
	- Tipo de Documento = null
	- Número do PASSAPORTE = em branco;*/

    @Test
    public void testaValidarPassaporte2(){
        numeroPassaporte = " ";
        candidato.setNacionalidade(tipoNacionalidade);
        candidato.setTipoDocumentoPessoal(tipoDocumentoPessoal);
        candidato.setPassaporte(numeroPassaporte);
        try {
            candidatoNeg.validarCPF(candidato);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

    /*CT18 - CE I(22)
	- Tipo de Nacionalidade null
	- Tipo de Documento = null
	- Número do CPF = inválido;
    @Test
    public void testaValidarCPF5(){
        tipoNacionalidade = "Z";
        tipoDocumentoPessoal = "C";
        numeroCPF = "33462461169";
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

CT19 - CE I(23)
	- Tipo de Nacionalidade inválida
	- Tipo de Documento = CPF
	- Número do CPF = válido;


    @Test
    public void testaValidarCPF6(){
        tipoNacionalidade = "Z";
        tipoDocumentoPessoal = "C";
        numeroCPF = "33462461168";
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

CT20 - CE I(24)
	- Tipo de Nacionalidade inválida
	- Tipo de Documento = PASSAPORTE
	- Número do PASSAPORTE = null;


    @Test
    public void testaValidarPassaporte3(){
        tipoNacionalidade = "Z";
        tipoDocumentoPessoal = "P";
        numeroPassaporte = null;
        try {
            candidatoNeg.validaPassaporte(tipoNacionalidade, tipoDocumentoPessoal, numeroPassaporte);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

CT21 - CE I(25)
	- Tipo de Nacionalidade inválida
	- Tipo de Documento = PASSAPORTE
	- Número do PASSAPORTE = válido;


    @Test
    public void testaValidarPassaporte4(){
        tipoNacionalidade = "Z";
        tipoDocumentoPessoal = "P";
        numeroPassaporte = "123456";
        try {
            candidatoNeg.validaPassaporte(tipoNacionalidade, tipoDocumentoPessoal, numeroPassaporte);
        }catch (Exception e){
            Assert.assertTrue(e instanceof NacionalidadeInvalidaException);
        }
    }

CT22 - CE I(26)
	- Tipo de Nacionalidade = BRASILEIRA
	- Tipo de Documento = CPF
	- Número do CPF = null;


    @Test
    public void testaValidarCPF7(){
        tipoNacionalidade = "B";
        tipoDocumentoPessoal = "C";
        numeroCPF = "123456";
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof CPFInvalidoException);
        }
    }

CT23 - CE I(27)
	- Tipo de Nacionalidade = BRASILEIRA
	- Tipo de Documento = CPF
	- Número do CPF = inválido;


    @Test
    public void testaValidarCPF8(){
        tipoNacionalidade = "B";
        tipoDocumentoPessoal = "C";
        numeroCPF = "33462461169";
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof CPFInvalidoException);
        }
    }

CT24 - CE V(28)
	- Tipo de Nacionalidade = BRASILEIRA
	- Tipo de Documento = CPF
	- Número do CPF = válido;


    @Test
    public void testaValidarCPF9(){
        tipoNacionalidade = "B";
        tipoDocumentoPessoal = "C";
        numeroCPF = "33462461168";
        try {
            Assert.assertTrue(candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

CT25 - CE I(29)
	- Tipo de Nacionalidade = BRASILEIRA
	- Tipo de Documento = PASSAPORTE
	- Número do PASSAPORTE = null;


    @Test
    public void testaValidarPassaporte5(){
        tipoNacionalidade = "B";
        tipoDocumentoPessoal = "P";
        numeroPassaporte = null;
        try {
            candidatoNeg.validaPassaporte(tipoNacionalidade, tipoDocumentoPessoal, numeroPassaporte);
        }catch (Exception e){
            Assert.assertTrue(e instanceof DocumentoPessoalInvalidoException);
        }
    }

CT26 - CE I(30)
	- Tipo de Nacionalidade = BRASILEIRA
	- Tipo de Documento = PASSAPORTE
	- Número do PASSAPORTE = válido;


    @Test
    public void testaValidarPassaporte6(){
        tipoNacionalidade = "B";
        tipoDocumentoPessoal = "P";
        numeroPassaporte = "123456";
        try {
            candidatoNeg.validaPassaporte(tipoNacionalidade, tipoDocumentoPessoal, numeroPassaporte);
        }catch (Exception e){
            Assert.assertTrue(e instanceof DocumentoPessoalInvalidoException);
        }
    }

CT27 - CE I(31)
	- Tipo de Nacionalidade = ESTRANGEIRA
	- Tipo de Documento = CPF
	- Número do CPF = null;


    @Test
    public void testaValidarCPF10(){
        tipoNacionalidade = "E";
        tipoDocumentoPessoal = "C";
        numeroCPF = null;
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof DocumentoPessoalInvalidoException);
        }
    }

CT28 - CE I(32)
	- Tipo de Nacionalidade = ESTRANGEIRA
	- Tipo de Documento = CPF
	- Número do CPF = inválido;


    @Test
    public void testaValidarCPF11(){
        tipoNacionalidade = "E";
        tipoDocumentoPessoal = "C";
        numeroCPF = "33462461169";
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof DocumentoPessoalInvalidoException);
        }
    }

CT29 - CE I(33)
	- Tipo de Nacionalidade = ESTRANGEIRA
	- Tipo de Documento = CPF
	- Número do CPF = válido;


    @Test
    public void testaValidarCPF12(){
        tipoNacionalidade = "E";
        tipoDocumentoPessoal = "C";
        numeroCPF = "33462461168";
        try {
            candidatoNeg.validarCPF(tipoNacionalidade, tipoDocumentoPessoal, numeroCPF);
        }catch (Exception e){
            Assert.assertTrue(e instanceof DocumentoPessoalInvalidoException);
        }
    }

CT30 - CE I(34)
	- Tipo de Nacionalidade = ESTRANGEIRA
	- Tipo de Documento = PASSAPORTE
	- Número do PASSAPORTE = null;


    @Test
    public void testaValidarPassaporte7(){
        tipoNacionalidade = "E";
        tipoDocumentoPessoal = "P";
        numeroPassaporte = null;
        try {
            candidatoNeg.validaPassaporte(tipoNacionalidade, tipoDocumentoPessoal, numeroPassaporte);
        }catch (Exception e){
            Assert.assertTrue(e instanceof PassaporteInvalidoException);
        }
    }

CT31 - CE V(35)
	- Tipo de Nacionalidade = ESTRANGEIRA
	- Tipo de Documento = PASSAPORTE
	- Número do PASSAPORTE = válido;


    @Test
    public void testaValidarPassaporte8(){
        tipoNacionalidade = "E";
        tipoDocumentoPessoal = "P";
        numeroPassaporte = "123456";
        try {
            Assert.assertTrue(candidatoNeg.validaPassaporte(tipoNacionalidade, tipoDocumentoPessoal, numeroPassaporte));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

*/
}

