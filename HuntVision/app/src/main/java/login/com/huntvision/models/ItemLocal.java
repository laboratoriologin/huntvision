package login.com.huntvision.models;

import com.j256.ormlite.field.DatabaseField;


/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class ItemLocal extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private Integer local_id;

    @DatabaseField
    private Integer item_id;

    private String serviceName;

    public ItemLocal() {

        setServiceName("itens_locais");

    }

    public String getId() {
        return id;
    }

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

    public Integer getLocal_id() {
        return local_id;
    }

    public void setLocal_id(Integer local_id) {
        this.local_id = local_id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }
}
