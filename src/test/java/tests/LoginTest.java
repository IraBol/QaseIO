package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(description = "Login w/ the correct credentials")
    public void successfulLogin() {

        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm(USERNAME, PASSWORD).
                clickSignInButton();

        projectsListPage.
                isPageOpened();
    }

    @Test(description = "Sign out from Qase")
    public void successfulSignOut() {

        loginPage.
                openPage("/login").
                isPageOpened().
                signOut().
                isPageOpened();
    }

    @Test(description = "Sign in w/o credentials")
    public void missingCredentialsErrorMessageShouldAppear() {

        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm("", "").
                clickSignInButton().
                waitTillMissingEmailErrorAppears().
                waitTillMissingPasswordErrorAppears();
    }

    @Test(description = "Sign in w/ password that appeared to be data leak")
    public void loginWithDataLeakPasswordErrorMessageShouldAppear() {

        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm("marta@gmail.com", "blaBla123").
                clickSignInButton().
                waitTillDataLeakPasswordErrorAppears();
    }

    @Test(description = "Sign in w/ credentials from another system")
    public void loginWithNonQaseCredentialsErrorMessageShouldAppear() {

        loginPage.
                openPage("/login").
                isPageOpened().
                fillOutLoginForm("marta@gmail.com", "blaBla123$$$").
                clickSignInButton().
                waitTillCredentialsMismatchErrorAppears();
    }
}
