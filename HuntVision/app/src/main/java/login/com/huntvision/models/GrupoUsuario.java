package login.com.huntvision.models;

import com.j256.ormlite.field.DatabaseField;



/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class GrupoUsuario extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String descricao;


    private String serviceName;

    public GrupoUsuario() {

        setServiceName("grupousuarios");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
