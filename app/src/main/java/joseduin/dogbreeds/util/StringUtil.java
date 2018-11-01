package joseduin.dogbreeds.util;

import java.util.Locale;

public class StringUtil {

    /**
     * Convertimos una cadena en minusculas
     * @param s: String a convertir
     * @return cadena en minuscula
     */
    public static String toLowerCase(String s) {
        return s.toLowerCase(Locale.getDefault());
    }

    /**
     * Comparamos si una cadema contiene a la otra en si
     * @param s1: Cadema a buscar
     * @param s2: Subcadena buscada
     * @return Existencia de dicha coincidencia
     */
    public static boolean contains(String s1, String s2) {
        s1 = toLowerCase(nullTranform(s1).trim());
        s2 = toLowerCase(nullTranform(s2).trim());
        return s1.contains(s2);
    }

    /**
     * Validamos que el String no sea null,
     * evitando posibles errores de java.lang.NullPointerException
     * @param s: Cadena a validar
     * @return Cadena validada
     */
    public static String nullTranform(String s) {
        return s == null ? "" : s;
    }

}
