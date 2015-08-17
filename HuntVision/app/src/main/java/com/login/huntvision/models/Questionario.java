package com.login.huntvision.models;

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
    private String procedimentos;

    @DatabaseField
    private String status;

    @DatabaseField
    private String pergunta;

    @DatabaseField
    private Long usuarioId;



    @DatabaseField
    private String observacao;

    @DatabaseField
    private Long protocoloId;

    @DatabaseField
    private String conformidade;

    private List<String> caminhosImagens;

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

    public List<String> getCaminhosImagens() {
        return caminhosImagens;
    }

    public void setCaminhosImagens(List<String> caminhosImagens) {
        this.caminhosImagens = caminhosImagens;
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

    public Long getProtocoloId() {
        return protocoloId;
    }

    public void setProtocoloId(Long protocoloId) {
        this.protocoloId = protocoloId;
    }


    public String getConformidade() {
        return conformidade;
    }

    public void setConformidade(String conformidade) {
        this.conformidade = conformidade;
    }

    public String getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(String procedimentos) {
        this.procedimentos = procedimentos;
    }
}
