package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test(description = "Sign out from Qase")
    public void signOutFromTheSystemShouldBeSuccessful() {

        loginPage.
                signOut().
                isPageOpened();
    }

    @Test(description = "Sign in w/o credentials")
    public void missingCredentialsErrorMessageShouldAppear() {

        loginPage.
                signOut().
                fillOutLoginForm("", "").
                waitTillMissingEmailErrorAppears().
                waitTillMissingPasswordErrorAppears();
    }
}
