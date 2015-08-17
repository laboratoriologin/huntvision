package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;


import java.util.List;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")
public class Resposta extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String descricao;

    //Controle logico do app na tela de questionario, quando finaliza a vistoria somente a resposta certa e persistida como VistoriaResposta
    @DatabaseField
    private Boolean flagRespostaCerta;

    @DatabaseField
    private Boolean flagNaoConformidade;

    @DatabaseField
    private String observacao;

    @DatabaseField
    private Long questionarioId;

    private Questionario questionario;

    private String serviceName;

    private List<Questionario> questionarios;

    public Resposta() {
        this.serviceName = "respostas";
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

    public Boolean getFlagRespostaCerta() {
        return flagRespostaCerta;
    }

    public void setFlagRespostaCerta(Boolean flagRespostaCerta) {
        this.flagRespostaCerta = flagRespostaCerta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getQuestionarioId() {
        return questionarioId;
    }

    public void setQuestionarioId(Long questionarioId) {
        this.questionarioId = questionarioId;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<Questionario> getQuestionarios() {
        return questionarios;
    }

    public void setQuestionarios(List<Questionario> questionarios) {
        this.questionarios = questionarios;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Boolean getFlagNaoConformidade() {
        return flagNaoConformidade;
    }

    public void setFlagNaoConformidade(Boolean flagNaoConformidade) {
        this.flagNaoConformidade = flagNaoConformidade;
    }

}
