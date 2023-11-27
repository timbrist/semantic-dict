package fi.coin.semanticdict.dto;

import java.util.List;

public class ImageDto {
    String imageName;
    String imageDescription;
    List<ObjectCoorDto> imageItems;

    public ImageDto() {
        this.imageName = "NULL";
        this.imageDescription = "NULL";
        imageItems = null;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void setImageItems(List<ObjectCoorDto> imageItems) {
        this.imageItems = imageItems;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public List<ObjectCoorDto> getImageItems() {
        return imageItems;
    }
}
