package joseduin.dogbreeds.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public class Message {

    /**
     * Mensaje impreso en pantalla de corta duracion
     * @param context
     * @param message: mensaje a imprimir
     */
    public static void messageShort(Context context, String message) {
        toast(context, message, Snackbar.LENGTH_SHORT);
    }

    /**
     * Mensaje impreso en pantalla de larga duracion
     * @param context
     * @param message: mensaje a imprimir
     */
    public static void messageLong(Context context, String message) {
        toast(context, message, Snackbar.LENGTH_LONG);
    }

    /**
     * Mensaje impreso en pantalla
     * @param context
     * @param message: mensaje a imprimir
     * @param duration: duracion del mensaje
     */
    public static void toast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

}
