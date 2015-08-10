package login.com.huntvision;

import android.view.View;
import android.widget.TabHost;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.io.File;

import login.com.huntvision.managers.sqlite.DatabaseHelper;
import login.com.huntvision.models.Usuario;

/**
 * Created by Ricardo on 16/01/2015.
 */
public class DefaultActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    public void backPressed(View view) {
        super.onBackPressed();
    }

    public TabHost getTabHost() {
        return ((HuntVisionApp) getApplication()).getTabHost();
    }

    public void setTabHost(TabHost mTabHost) {
        ((HuntVisionApp) getApplication()).setTabHost(mTabHost);
    }

    public String getUrlWS() {
        return ((HuntVisionApp) getApplication()).getUrlWS();
    }

    public void setUrlWS(String urlWS) {
        ((HuntVisionApp) getApplication()).setUrlWS(urlWS);
    }

    public String getKeyHuntVision() {
        return ((HuntVisionApp) getApplication()).getKeyHuntVision();
    }

    public void setKeyHuntVision(String keyHuntVision) {
        ((HuntVisionApp) getApplication()).setKeyHuntVision(keyHuntVision);
    }

    public Usuario getUsuario() {
        Usuario usuario = ((HuntVisionApp) getApplication()).getUsuario();
        return getHelper().getUsuarioRuntimeDAO().queryForId(usuario.getId());
    }

    public void setUsuario(Usuario usuario) {
        ((HuntVisionApp) getApplication()).setUsuario(usuario);
    }

    public File getDataFolder() {
        return ((HuntVisionApp) getApplication()).getDataFolder();
    }

    public File getTmpDataFolder() {
        return ((HuntVisionApp) getApplication()).getTmpDataFolder();
    }


    public void onBackPressed(View view) {

        super.onBackPressed();

    }
}
