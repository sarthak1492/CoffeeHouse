package DataModel;

/**
 * Created by Sarthak.Sharma on 19-07-2018.
 */

public class SubCategoryItems {

    String strSubCategoryHeader, getStrSubCategoryDescription, strSubCategoryPrice, getStrSubCategoryImageURL;

    public SubCategoryItems() {
    }

    public SubCategoryItems(String strSubCategoryHeader, String getStrSubCategoryDescription, String strSubCategoryPrice, String getStrSubCategoryImageURL) {
        this.strSubCategoryHeader = strSubCategoryHeader;
        this.getStrSubCategoryDescription = getStrSubCategoryDescription;
        this.strSubCategoryPrice = strSubCategoryPrice;
        this.getStrSubCategoryImageURL = getStrSubCategoryImageURL;
    }

    public String getStrSubCategoryHeader() {
        return strSubCategoryHeader;
    }

    public void setStrSubCategoryHeader(String strSubCategoryHeader) {
        this.strSubCategoryHeader = strSubCategoryHeader;
    }

    public String getGetStrSubCategoryDescription() {
        return getStrSubCategoryDescription;
    }

    public void setGetStrSubCategoryDescription(String getStrSubCategoryDescription) {
        this.getStrSubCategoryDescription = getStrSubCategoryDescription;
    }

    public String getStrSubCategoryPrice() {
        return strSubCategoryPrice;
    }

    public void setStrSubCategoryPrice(String strSubCategoryPrice) {
        this.strSubCategoryPrice = strSubCategoryPrice;
    }

    public String getGetStrSubCategoryImageURL() {
        return getStrSubCategoryImageURL;
    }

    public void setGetStrSubCategoryImageURL(String getStrSubCategoryImageURL) {
        this.getStrSubCategoryImageURL = getStrSubCategoryImageURL;
    }
}
