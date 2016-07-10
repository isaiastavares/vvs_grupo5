package recursosHumanos.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recursosHumanos.entidade.Candidato;
import recursosHumanos.entidade.Cargo;
import recursosHumanos.excecoes.*;
import recursosHumanos.util.Mensagem;
import recursosHumanos.enumeradores.EnumTipoDocumentoHabilitacao;

public class CargoNeg {
	
	private boolean result = false;
	
	public List<String> obtemListaDescricoesargosValidas(){
		String cargo = "Engenheiro";
		String cargo1 = "Médico";
		String cargo2 = "Técnico em Informática";
		String cargo3 = "Motorista";
		List<String> cargos = new ArrayList<String>();
		cargos.add(cargo);
		cargos.add(cargo1);
		cargos.add(cargo2);
		cargos.add(cargo3);
		return cargos;
	}
	
	public boolean validaDescricao(Cargo cargo) throws CargoInvalidoException{
	
		List<String> cargosValidos = obtemListaDescricoesargosValidas();
		if(cargosValidos.contains(cargo.getDescricao())){
			result = true;
		}else{
			throw new CargoInvalidoException(Mensagem.cargoInvalido);
		}
		return result;
	}
	
	public boolean validaDocumentoHabilitacao(Cargo cargo) throws DocumentoHabilitacaoException, CargoInvalidoException{
		//valida medico
		if(validaDescricao(cargo)){
			if(cargo.getDescricao().equals("Médico") && cargo.getTipoDocumentoHabilitacao() != EnumTipoDocumentoHabilitacao.CRM){
				throw new DocumentoHabilitacaoException(Mensagem.tipoDocumentoCRM);
				//valida engenheiro
			}else if(cargo.getDescricao().equals("Engenheiro") && cargo.getTipoDocumentoHabilitacao() != EnumTipoDocumentoHabilitacao.CREA){
				throw new DocumentoHabilitacaoException(Mensagem.tipoDocumentoCREA);
				//valida motorista
			}else if(cargo.getDescricao().equals("Motorista") && cargo.getTipoDocumentoHabilitacao() != EnumTipoDocumentoHabilitacao.CNH){
				throw new DocumentoHabilitacaoException(Mensagem.tipoDocumentoCNH);
				//valida que Técnico em Informática não deve ter nenhum documento de habilitação.
			}else if (cargo.getDescricao().equals("Técnico em Informática") && (cargo.getTipoDocumentoHabilitacao() ==
					EnumTipoDocumentoHabilitacao.CNH || cargo.getTipoDocumentoHabilitacao() == EnumTipoDocumentoHabilitacao.CREA || cargo.getTipoDocumentoHabilitacao() == EnumTipoDocumentoHabilitacao.CRM)){
				throw new DocumentoHabilitacaoException(Mensagem.documentoTecnicoInformatica);
			}else{
				result = true;
			}
		}

		return result;
	}
	
	public boolean validaSalario(Cargo cargo) throws SalarioInvalidoException, CargoInvalidoException {
		if(validaDescricao(cargo)){
			if(cargo.getDescricao().equals("Médico") && (cargo.getSalario() < 8000.00d || cargo.getSalario() > 15000.00d)){
				throw new SalarioInvalidoException(Mensagem.salarioMedico);
			}else if(cargo.getDescricao().equals("Engenheiro") && (cargo.getSalario() < 3000.00d || cargo.getSalario() > 10000.00d)){
				throw new SalarioInvalidoException(Mensagem.salarioEngenheiro);
			}else if(cargo.getDescricao().equals("Tecnico em Informática") && (cargo.getSalario() < 1500.00d || cargo.getSalario() > 7000.00d)){
				throw new SalarioInvalidoException(Mensagem.salarioTecnicoInformatica);
			}else if(cargo.getDescricao().equals("Motorista") && (cargo.getSalario() < 1000.00d || cargo.getSalario() > 3000.00d)){
				throw new SalarioInvalidoException(Mensagem.salarioMotorista);
			}else{
				result = true;
			}
		}

		return result;
	}

	public boolean ocupaVaga(Cargo cargo, Candidato candidato) throws VagaIndisponivelException{
		if(cargo.getQuantidadeVagas() > 0){
			candidato.setCargo(cargo);
			cargo.setQuantidadeVagas(cargo.getQuantidadeVagas()-1);
			result = true;
		}else{
			throw new VagaIndisponivelException(Mensagem.erro + Mensagem.vagaIndisponivel);
		}
		return result;
	}

    public void configuraQuantidadeVagas(Cargo cargo){
        int quantidadeVagasCargoEngenheiro = 50;
        int quantidadeVagasCargoTecnicoInformatica = 20;
        int quantidadeVagasCargoMotorista = 15;
        int quantidadeVagasCargoMedico = 10;
        if(cargo.getDescricao().equals("Médico")){
            cargo.setQuantidadeVagas(quantidadeVagasCargoMedico);
        }else if(cargo.getDescricao().equals("Engenheiro")){
            cargo.setQuantidadeVagas(quantidadeVagasCargoEngenheiro);
        }else if(cargo.getDescricao().equals("Técnico em Informática")){
            cargo.setQuantidadeVagas(quantidadeVagasCargoTecnicoInformatica);
        }else if(cargo.getDescricao().equals("Motorista")){
            cargo.setQuantidadeVagas(quantidadeVagasCargoMotorista);
        }
    }

	public boolean validaQuantidadeVagas(Cargo cargo) throws QuantidadeVagasInvalidaException, CargoInvalidoException{
		if(validaDescricao(cargo)){
			if((cargo.getDescricao().equalsIgnoreCase("Médico") && (cargo.getQuantidadeVagas() != 10)) ||
					((cargo.getDescricao().equalsIgnoreCase("Engenheiro")) && (cargo.getQuantidadeVagas() != 50)) ||
					((cargo.getDescricao().equalsIgnoreCase("Técnico em Informática")) && (cargo.getQuantidadeVagas() != 20)) ||
					((cargo.getDescricao().equalsIgnoreCase("Motorista")) && (cargo.getQuantidadeVagas() != 15))){
				throw new QuantidadeVagasInvalidaException(Mensagem.erro + Mensagem.quantidadeVagasInvalida);
			}else{
				result = true;
			}
		}
		return result;
	}

}
