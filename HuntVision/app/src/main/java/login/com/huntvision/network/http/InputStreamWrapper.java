package login.com.huntvision.network.http;

import java.io.InputStream;

/**
 * Created by login on 26/03/15.
 */
public class InputStreamWrapper {

    private InputStream stream;
    private String filename;

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
