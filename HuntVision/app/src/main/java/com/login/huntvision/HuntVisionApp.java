package com.login.huntvision;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TabHost;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import com.login.huntvision.models.Usuario;
import com.login.huntvision.utils.Constantes;

public class HuntVisionApp extends Application implements LocationListener {

    private TabHost mTabHost;
    private String keyHuntVision;
    private LocationManager locationManager;
    private Double latitude = -12.875836;
    private Double longitude = -38.308309;

    private Boolean isChanged = false;

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


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

            if (locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                latitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
                longitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
                isChanged = true;
            }

        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

            if (locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                latitude = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
                longitude = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();
                isChanged = true;
            }

        }

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
        editor.putString(Constantes.KEY_HUNTVISION, this.keyHuntVision).commit();
    }

    public String getUrlWS() {
        return getSharedPreferences(Constantes.SHARED_PREFS, 0).getString(Constantes.KEY_WS, null);

    }

    public void setUrlWS(String urlWS) {
        SharedPreferences.Editor editor = getSharedPreferences(Constantes.SHARED_PREFS, 0).edit();
        editor.putString(Constantes.KEY_WS, urlWS).commit();
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        isChanged = true;
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public Boolean getChanged() {
        return isChanged;
    }
}
