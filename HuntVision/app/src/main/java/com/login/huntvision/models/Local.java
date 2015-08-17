package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;



import java.util.List;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class Local  extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private Long clienteId;

    @DatabaseField
    private Long grupoItemId;

    @DatabaseField
    private String nomeLocal;

    private String serviceName;


    private List<Cliente> clientes;


    private List<ItemLocal> itemLocals;


    public Local() {
        setServiceName("locais");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getGrupoItemId() {
        return grupoItemId;
    }

    public void setGrupoItemId(Long grupoItemId) {
        this.grupoItemId = grupoItemId;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<ItemLocal> getItemLocals() {
        return itemLocals;
    }

    public void setItemLocals(List<ItemLocal> itemLocals) {
        this.itemLocals = itemLocals;
    }
}
