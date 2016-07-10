package recursosHumanos.util;

import recursosHumanos.excecoes.DataInvalidaException;
import recursosHumanos.excecoes.DataNaoInformadaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gilmario on 10/05/16.
 */
public class DataUtils {

    //retorna a data no formato "dd/MM/yyyy"
    public static Date formataData(String data) throws ParseException {
        Date dataFormatada = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dataFormatada = sdf.parse(data);
        return dataFormatada;
    }

    //retorna a data no formato "dd/MM/yyyy"
    public static String formataData(Date data) throws ParseException {
        String dataFormatada = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dataFormatada = sdf.format(data);
        return dataFormatada;
    }

    //valida uma data
    public static boolean isThisDateValid(String dateToValidate) throws DataNaoInformadaException, DataInvalidaException {
        String pattern = "dd/MM/yyyy";
        if(dateToValidate == null){
            throw new DataNaoInformadaException(Mensagem.dataNaoInformada);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
        }catch(ParseException pe){
            throw new DataInvalidaException(Mensagem.erro + " " + Mensagem.dataInvalida);
        }

        return true;
    }
}
