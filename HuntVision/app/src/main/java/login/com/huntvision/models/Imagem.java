package login.com.huntvision.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class Imagem extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String caminho;

    @DatabaseField
    private String legenda;

    private String serviceName;

    public Imagem() {

        setServiceName("imagens");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
