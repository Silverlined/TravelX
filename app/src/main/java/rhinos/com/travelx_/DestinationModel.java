package rhinos.com.travelx_;

import java.io.Serializable;

class DestinationModel implements Serializable {
    private String name;
    private int image;

    public int getImage() {
        return image;
    }

    DestinationModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }
}
