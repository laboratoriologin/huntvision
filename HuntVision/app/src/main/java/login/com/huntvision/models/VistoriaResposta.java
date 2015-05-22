package login.com.huntvision.models;

/**
 * Created by Ricardo on 19/01/2015.
 */

import com.j256.ormlite.field.DatabaseField;

import java.util.List;


@SuppressWarnings("serial")

public class VistoriaResposta extends Base {

    @DatabaseField(id = true)
    private String id;

    private String serviceName;

    @DatabaseField
    private String vistoriaId;

    @DatabaseField
    private String respostaId;

    @DatabaseField
    private String observacao;

    private List<Imagem> imagens;

    private Resposta resposta;

    private Vistoria vistoria;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVistoriaId() {
        return vistoriaId;
    }

    public void setVistoriaId(String vistoriaId) {
        this.vistoriaId = vistoriaId;
    }

    public String getRespostaId() {
        return respostaId;
    }

    public void setRespostaId(String respostaId) {
        this.respostaId = respostaId;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
