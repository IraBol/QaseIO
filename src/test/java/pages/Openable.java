package pages;

public interface Openable {

    public BasePage openPage();

    //    public default boolean isPageOpened() {
//        return false;
//    }
    public BasePage isPageOpened();
}
