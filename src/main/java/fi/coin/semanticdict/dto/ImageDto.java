package fi.coin.semanticdict.dto;

import java.util.List;

public class ImageDto {
    String imageName;
    String imageDescription;
    List<String> imageItems;

    public ImageDto() {
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void setImageItems(List<String> imageItems) {
        this.imageItems = imageItems;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public List<String> getImageItems() {
        return imageItems;
    }
}
