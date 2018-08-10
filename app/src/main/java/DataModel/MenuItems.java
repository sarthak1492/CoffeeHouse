package DataModel;

public class MenuItems {

    private String strImageUrls, strMainCategory, strHeadline;

    public MenuItems() {
    }

    public MenuItems(String strImageUrls, String strMainCategory, String strHeadline) {
        this.strImageUrls = strImageUrls;
        this.strMainCategory = strMainCategory;
        this.strHeadline = strHeadline;
    }

    public String getStrImageUrls() {
        return strImageUrls;
    }

    public void setStrImageUrls(String strImageUrls) {
        this.strImageUrls = strImageUrls;
    }

    public String getStrMainCategory() {
        return strMainCategory;
    }

    public void setStrMainCategory(String strMainCategory) {
        this.strMainCategory = strMainCategory;
    }

    public String getStrHeadline() {
        return strHeadline;
    }

    public void setStrHeadline(String strHeadline) {
        this.strHeadline = strHeadline;
    }
}
