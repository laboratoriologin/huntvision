package login.com.huntvision.models;

import com.j256.ormlite.field.DatabaseField;


import java.util.List;

/**
 * Created by Ricardo on 19/01/2015.
 */

public class Menu extends Base{

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String descricao;

    @DatabaseField
    private String uRL;

    @DatabaseField
    private Boolean flag_ativo;

    @DatabaseField
    private String managedBeanReset;

    private String serviceName;

    @DatabaseField
    private Long ordem;

    @DatabaseField
    private Long menuId;

    public Menu() {

        setServiceName("menus");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }



    public String getManagedBeanReset() {
        return managedBeanReset;
    }

    public void setManagedBeanReset(String managedBeanReset) {
        this.managedBeanReset = managedBeanReset;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getOrdem() {
        return ordem;
    }

    public void setOrdem(Long ordem) {
        this.ordem = ordem;
    }

    public Long getMenuId() {
        return menuId;
    }

    private List<Menu> menus;

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Boolean getFlag_ativo() {
        return flag_ativo;
    }

    public void setFlag_ativo(Boolean flag_ativo) {
        this.flag_ativo = flag_ativo;
    }
}
