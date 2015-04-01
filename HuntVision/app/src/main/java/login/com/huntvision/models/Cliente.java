package login.com.huntvision.models;

/**
 * Created by Ricardo on 19/01/2015.
 */

import com.j256.ormlite.field.DatabaseField;

@SuppressWarnings("serial")
public class Cliente extends Base {

    private String serviceName;

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String email;

    @DatabaseField
    private String celular;

    @DatabaseField
    private String cNPJ;

    @DatabaseField
    private String endereco;

    @DatabaseField
    private String cidade;

    @DatabaseField
    private String bairro;

    @DatabaseField
    private String estado;

    @DatabaseField
    private String cep;

    @DatabaseField
    private String pais;


    public Cliente() {
        setServiceName("clientes");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCNPJ() {
        return getcNPJ();
    }

    public void setCNPJ(String cNPJ) {
        this.setcNPJ(cNPJ);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getcNPJ() {
        return cNPJ;
    }

    public void setcNPJ(String cNPJ) {
        this.cNPJ = cNPJ;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
