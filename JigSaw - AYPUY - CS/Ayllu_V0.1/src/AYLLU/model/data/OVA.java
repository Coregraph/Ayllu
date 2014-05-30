package AYLLU.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDREA
 */
public class OVA implements Serializable{

    private String id;
    private String name;
    private String filename;
    private List<String> metadata;
    private boolean hasBeenDownloaded;

    public OVA(String id, String name, String filename) {
        this.id = id;
        this.name = name;
        this.filename=filename;
        this.metadata = new ArrayList<String>();
        this.hasBeenDownloaded = false;
    }

    public OVA(String id, String name, String filename, List<String> metadata) {
        this.id = id;
        this.name = name;
        this.filename=filename;
        this.metadata = metadata;
        this.hasBeenDownloaded = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public boolean getHasBeenDownloaded() {
        return hasBeenDownloaded;
    }

    public void setHasBeenDownloaded(boolean hasBeenDownloaded) {
        this.hasBeenDownloaded = hasBeenDownloaded;
    }

}
