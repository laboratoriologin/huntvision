package login.com.huntvision.models;

/**
 * Created by Ricardo on 19/01/2015.
 */

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")

public class Vistoria extends Base {

    @DatabaseField(generatedId = true)
    private Long id;

    private String serviceName;

    @DatabaseField
    private String clienteId;

    @DatabaseField
    private String data;

    @DatabaseField
    private String usuarioId;

    @DatabaseField
    private String itemId;

    private Cliente cliente;

    @DatabaseField
    private Integer flagSincronizado;

    private Usuario usuario;

    private List<VistoriaResposta> respostas;

    public String getId() {
        return id.toString();
    }

    @Override
    public void setId(String id) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {return "vistorias";}

    @Override
    public void setServiceName(String serviceName) {

    }

    ;

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getFlagSincronizado() {
        return flagSincronizado;
    }

    public void setFlagSincronizado(Integer flagSincronizado) {
        this.flagSincronizado = flagSincronizado;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<VistoriaResposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<VistoriaResposta> respostas) {
        this.respostas = respostas;
    }
}
