package login.com.huntvision.models;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;

import org.apache.james.mime4j.field.datetime.DateTime;


import java.io.InputStream;
import java.util.List;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class Questionario extends Base {

    public Questionario() {
        setServiceName("questionarios");
    }

    @DatabaseField(id = true)
    private String id;

    private String serviceName;

    @DatabaseField
    private Long tipoQuestionarioId;

    @DatabaseField
    private Long itemId;

    @DatabaseField
    private String data;

    @DatabaseField
    private String status;

    @DatabaseField
    private String pergunta;

    @DatabaseField
    private Long usuarioId;

    @DatabaseField
    private String observacao;

    private List<Imagem> imagems;

    private List<Resposta> respostas;



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

    public Long getTipoQuestionarioId() {
        return tipoQuestionarioId;
    }

    public void setTipoQuestionarioId(Long tipoQuestionarioId) {
        this.tipoQuestionarioId = tipoQuestionarioId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Imagem> getImagems() {
        return imagems;
    }

    public void setImagems(List<Imagem> imagems) {
        this.imagems = imagems;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Questionario that = (Questionario) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
