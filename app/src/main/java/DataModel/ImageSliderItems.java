package DataModel;

public class ImageSliderItems {

    String strImagesUrls;

    public ImageSliderItems() {
    }
    public ImageSliderItems(String strImagesUrls){
        this.strImagesUrls=strImagesUrls;
    }

    public String getImage() {
        return strImagesUrls;
    }

    public void setImage(String strImagesUrls) {
        this.strImagesUrls = strImagesUrls;
    }

}
