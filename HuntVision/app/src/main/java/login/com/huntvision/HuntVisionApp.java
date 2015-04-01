package login.com.huntvision;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.TabHost;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import login.com.huntvision.models.Usuario;
import login.com.huntvision.utils.Constantes;

/**
 * Created by Ricardo on 16/01/2015.
 */
public class HuntVisionApp extends Application {

    private TabHost mTabHost;
    private String keyHuntVision;

    @Override
    public void onCreate() {

        super.onCreate();

        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800)
                .diskCacheExtraOptions(480, 800, null)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(25)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public TabHost getTabHost() {
        return mTabHost;
    }

    public void setTabHost(TabHost mTabHost) {
        this.mTabHost = mTabHost;
    }


    public String getKeyHuntVision() {
        if (this.keyHuntVision == null) {
            this.keyHuntVision = getSharedPreferences(Constantes.SHARED_PREFS, 0).getString(Constantes.KEY_HUNTVISION, null);
        }
        return this.keyHuntVision;
    }

    public void setKeyHuntVision(String keyHuntVision) {
        this.keyHuntVision = keyHuntVision;
        SharedPreferences.Editor editor = getSharedPreferences(Constantes.SHARED_PREFS, 0).edit();
        editor.putString(Constantes.KEY_HUNTVISION, this.keyHuntVision);
    }

    public void setUsuario(Usuario usuario) {
        SharedPreferences.Editor editor = getSharedPreferences(Constantes.SHARED_PREFS, 0).edit();
        editor.putString(Constantes.KEY_USUARIO, usuario.getId()).commit();
    }

    public Usuario getUsuario() {

        String usuarioId = getSharedPreferences(Constantes.SHARED_PREFS, 0).getString(Constantes.KEY_USUARIO, "");

        Usuario usuario = new Usuario();

        usuario.setId(usuarioId);

        return usuario;

    }

    public File getDataFolder() {

        File dataDir = null;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            dataDir = new File(Environment.getExternalStorageDirectory(), "huntvision");

            if (!dataDir.isDirectory()) {

                dataDir.mkdirs();

            }

        }


        if (!dataDir.isDirectory()) {

            dataDir = this.getFilesDir();

        }


        return dataDir;

    }

    public File getTmpDataFolder() {

        File dataDir = null;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            dataDir = new File(Environment.getExternalStorageDirectory(), "huntvision_temp");

            if (!dataDir.isDirectory()) {

                dataDir.mkdirs();

            }

        }


        if (!dataDir.isDirectory()) {

            dataDir = this.getFilesDir();

        }


        return dataDir;

    }


}
