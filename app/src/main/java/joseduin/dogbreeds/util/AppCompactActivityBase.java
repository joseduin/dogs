package joseduin.dogbreeds.util;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Activity padre que sirve para metodos comunes de las vistas
 */
public class AppCompactActivityBase extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
