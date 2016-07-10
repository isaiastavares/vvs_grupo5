package recursosHumanos.util;

/**
 * Created by gilmario on 10/05/16.
 */
public class ValidaString {
    public static boolean validar(String s){
        return s.matches("[ a-zà-úA-ZÀ-Ú ]+");
    }
}
