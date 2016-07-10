package recursosHumanos.negocio;

import java.text.ParseException;
import java.util.Date;

import recursosHumanos.entidade.Candidato;
import recursosHumanos.enumeradores.EnumSexo;
import recursosHumanos.excecoes.*;
import recursosHumanos.util.*;
import recursosHumanos.enumeradores.EnumTipoDocumentoPessoal;
import recursosHumanos.enumeradores.EnumTipoNacionalidade;

public class CandidatoNeg {
	
	boolean result = false;

	public boolean validaNome(Candidato candidato) throws NomeInvalidoException{
		
		if(candidato.getNome() == null || candidato.getNome().equals("") || candidato.getNome().length() < 5 || candidato.getNome().length() > 100){
			throw new NomeInvalidoException(Mensagem.erro + Mensagem.nomeInvalido);
		}else{
			result = ValidaString.validar(candidato.getNome());
            if(!result){
                throw new NomeInvalidoException(Mensagem.erro + Mensagem.nomeInvalido);
            }
		}
		return result;
	} 
	
	public boolean validaDataNascimento(Candidato candidato) throws DataNaoInformadaException, DataInvalidaException, ParseException{
        String dataNascimento;
        if(candidato.getDataNascimento() != null){
            try {
                dataNascimento = DataUtils.formataData(candidato.getDataNascimento());
            }catch (Exception e){
                throw new ParseException("Erro ao formatar a data", 0);
            }

            if(dataNascimento == null){
                throw new DataNaoInformadaException(Mensagem.erro + Mensagem.dataNascimentoInvalida);
            }else{
                result = DataUtils.isThisDateValid(dataNascimento);
                if(!result){
                    throw new DataInvalidaException(Mensagem.erro + Mensagem.dataInvalida);
                }
            }
        }else{
            throw new DataNaoInformadaException(Mensagem.erro + Mensagem.dataNaoInformada);
        }

		return result;
	}
	
	private boolean validaTipoNacionalidade(Candidato candidato) throws NacionalidadeInvalidaException{

		if(candidato.getNacionalidade() == null || !(candidato.getNacionalidade().equals(EnumTipoNacionalidade.BRASILEIRA) || candidato.getNacionalidade().equals(EnumTipoNacionalidade.ESTRANGEIRA))){
			throw new NacionalidadeInvalidaException(Mensagem.nacionalidadeInvalida + Mensagem.nacionalidadeValida);
		}else{
			result = true;
		}
		return result;
	}

    private boolean validaTipoDocumentoPessoal(Candidato candidato) throws DocumentoPessoalInvalidoException{
        if(candidato.getTipoDocumentoPessoal() == null || !(candidato.getTipoDocumentoPessoal().equals(EnumTipoDocumentoPessoal.CPF) || candidato.getTipoDocumentoPessoal().equals(EnumTipoDocumentoPessoal.PASSAPORTE))){
            throw new DocumentoPessoalInvalidoException(Mensagem.erro + Mensagem.documentoPessoalInvalido);
        }else{
            result = true;
        }
        return result;
    }
	
	private boolean validaCombinacaoTipoNacionalidadeTipoDocumento(Candidato candidato) throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException{

		if((validaTipoNacionalidade(candidato) && validaTipoDocumentoPessoal(candidato)) || (candidato.getNacionalidade().equals(EnumTipoNacionalidade.BRASILEIRA) && !candidato.getTipoDocumentoPessoal().equals(EnumTipoDocumentoPessoal.CPF))){
			throw new DocumentoPessoalInvalidoException(Mensagem.tipoDocumentoCPF);
		}else if(candidato.getNacionalidade().equals(EnumTipoNacionalidade.ESTRANGEIRA) && !candidato.getTipoDocumentoPessoal().equals(EnumTipoDocumentoPessoal.PASSAPORTE)){
			throw new DocumentoPessoalInvalidoException(Mensagem.tipoDocumentoPassaporte);
		}else{
			result = true;
		}
		
		return result;
	}
	
	public boolean validarCPF(Candidato candidato) throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, CPFInvalidoException{
        if(validaCombinacaoTipoNacionalidadeTipoDocumento(candidato)){
            if(ValidaCPF.isValidCPF(candidato.getCPF())){
                result = true;
            }else{
                throw new CPFInvalidoException(Mensagem.erro + Mensagem.cpfInvalido);
            }
        }
		return result;
	}

    public boolean validaIdade(Candidato candidato) throws IdadeInvalidadeException, ParseException{
        int idade = CalculaIdade.calcularIdade(candidato.getDataNascimento());
        if(idade < 18 || idade > 75){
            throw new IdadeInvalidadeException(Mensagem.erro + Mensagem.idadeInvalidade);
        }else{
            result = true;
        }
        return result;
    }

    public boolean validaPassaporte(Candidato candidato) throws NacionalidadeInvalidaException, DocumentoPessoalInvalidoException, PassaporteInvalidoException{
        if(validaCombinacaoTipoNacionalidadeTipoDocumento(candidato)){
            if(candidato.getPassaporte() != null || !(candidato.getPassaporte().equals(" "))){
                result = true;
            }else{
                throw new PassaporteInvalidoException(Mensagem.erro + Mensagem.passaporteInvalido);
            }
        }

        return result;
    }

    public boolean validaSexo(Candidato candidato) throws SexoInvalidoException{
        if(candidato.getSexo() == null || !(candidato.getSexo().equals(EnumSexo.F)) && !(candidato.getSexo().equals(EnumSexo.M))){
            throw new SexoInvalidoException(Mensagem.erro + Mensagem.sexoInvalido);
        }else{
            result = true;
        }
        return result;
    }

    public boolean validaNecessidadeReservista(Candidato candidato){
        boolean necessitaReservista = false;
        if(candidato.getSexo().equals(EnumSexo.M)){
            int idade = CalculaIdade.calcularIdade(candidato.getDataNascimento());
            if(idade <= 45){
                necessitaReservista = true;
            }
        }
        return necessitaReservista;
    }


    public boolean validaDocumentoReservista(Candidato candidato) throws DocumentoReservistaInvalidoException{
        if(validaNecessidadeReservista(candidato)){
            if(candidato.getDocumentoReservista() == null){
                throw new DocumentoReservistaInvalidoException(Mensagem.erro + Mensagem.documentoReservistaInvalido);
            }else{
                result = true;
            }
        }
        return result;
    }

    public boolean validaCandidato(Candidato candidato) throws Exception{
        if(validaNome(candidato) &&
                validaDataNascimento(candidato) &&
                validaIdade(candidato) &&
                validaSexo(candidato) &&
                validaDocumentoReservista(candidato) &&
                (validarCPF(candidato)) || validaPassaporte(candidato)){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
}
