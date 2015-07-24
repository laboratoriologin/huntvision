package login.com.huntvision.models;

import com.j256.ormlite.field.DatabaseField;


/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class ProtocoloAcao extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private Integer protocolo_id;

    @DatabaseField
    private Integer acao_id;

    private String serviceName;

    public ProtocoloAcao() {

        setServiceName("protocolos_acoes");

    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getProtocolo_id() {
        return protocolo_id;
    }

    public void setProtocolo_id(Integer protocolo_id) {
        this.protocolo_id = protocolo_id;
    }

    public Integer getAcao_id() {
        return acao_id;
    }

    public void setAcao_id(Integer acao_id) {
        this.acao_id = acao_id;
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
