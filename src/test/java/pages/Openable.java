package pages;

public interface Openable {

    public BasePage openPage(String path);

    //    public default boolean isPageOpened() {
//        return false;
//    }
    public BasePage isPageOpened();
}
