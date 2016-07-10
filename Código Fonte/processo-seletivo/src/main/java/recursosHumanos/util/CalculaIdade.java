package recursosHumanos.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by gilmario on 10/05/16.
 */
public class CalculaIdade {
    //Calcula a Idade baseado em String. Exemplo: calculaIdade("20/08/1977","dd/MM/yyyy");

    public static int calcularIdade(String dataNasc) throws ParseException{

        String pattern = "dd/MM/yyyy";

        DateFormat sdf = new SimpleDateFormat(pattern);

        Date dataNascInput = null;

        dataNascInput  = sdf.parse(dataNasc);

        Calendar dateOfBirth = new GregorianCalendar();

        dateOfBirth.setTime(dataNascInput);

        //cria um objeto calendar com a data atual

        Calendar today = Calendar.getInstance();

        //obtém a idade baseada no ano;

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);

        if(today.before(dateOfBirth)){
            age--;
        }

        return age;

    }

    //Calcula a Idade baseado em java.util.Date

    public static int calcularIdade(Date dataNasc){

        Calendar dateOfBirth = new GregorianCalendar();

        dateOfBirth.setTime(dataNasc);

        //Cria um objeto calendar com a data atual

        Calendar today = Calendar.getInstance();

        //Obtém a idade baseada no ano

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);

        if(today.before(dateOfBirth)){
            age--;
        }

        return age;

    }

}
