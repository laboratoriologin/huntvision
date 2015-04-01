package login.com.huntvision.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class Permissao extends Base {
    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private Boolean flag_alterar;

    @DatabaseField
    private Boolean flag_excluir;

    @DatabaseField
    private Boolean flag_inserir;

    @DatabaseField
    private Long grupoUsuarioID;

    @DatabaseField
    private Long menuId;

    private List<GrupoUsuario> grupoUsuarios;


    private List<Menu> menus;


    private String serviceName;

    public Permissao() {

        setServiceName("permissoes");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFlag_alterar() {
        return flag_alterar;
    }

    public void setFlag_alterar(Boolean flag_alterar) {
        this.flag_alterar = flag_alterar;
    }

    public Boolean getFlag_excluir() {
        return flag_excluir;
    }

    public void setFlag_excluir(Boolean flag_excluir) {
        this.flag_excluir = flag_excluir;
    }

    public Boolean getFlag_inserir() {
        return flag_inserir;
    }

    public void setFlag_inserir(Boolean flag_inserir) {
        this.flag_inserir = flag_inserir;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getGrupoUsuarioID() {
        return grupoUsuarioID;
    }

    public void setGrupoUsuarioID(Long grupoUsuarioID) {
        this.grupoUsuarioID = grupoUsuarioID;
    }

    public List<GrupoUsuario> getGrupoUsuarios() {
        return grupoUsuarios;
    }

    public void setGrupoUsuarios(List<GrupoUsuario> grupoUsuarios) {
        this.grupoUsuarios = grupoUsuarios;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
