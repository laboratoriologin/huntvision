package login.com.huntvision.models;

import java.io.Serializable;

/**
 * Created by Ricardo on 19/01/2015.
 */
public abstract class Base  implements Serializable {

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getServiceName();

    public abstract void setServiceName(String serviceName);

}
