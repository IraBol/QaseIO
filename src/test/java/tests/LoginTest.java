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
                clickSignInButton();
        loginPage.
                waitTillMissingEmailErrorAppears().
                waitTillMissingPasswordErrorAppears();
    }

    @Test(description = "Sign in w/ password that appeared to be data leak")
    public void loginWithDataLeakPasswordErrorMessageShouldAppear() {

        loginPage.
                signOut().
                fillOutLoginForm("marta@gmail.com", "blaBla123").
                clickSignInButton();
        loginPage.
                waitTillDataLeakPasswordErrorAppears();
    }

    @Test(description = "Sign in w/ credentials from another system")
    public void loginWithCredentialsFromAnotherSystemErrorMessageShouldAppear() {

        loginPage.
                signOut().
                fillOutLoginForm("marta@gmail.com", "blaBla123$$$").
                clickSignInButton();
        loginPage.
                waitTillCredentialsMismatchErrorAppears();
    }
}
