package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;

public class BankLoginTest {

    @AfterAll
    static void teardown() {
        SQLHelper.cleanDataBase();
    }

    @Test
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var info = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(info);
        verificationPage.isVerificationPageVisible();
        var code = SQLHelper.getVerificationCode();
        verificationPage.validVerify(code);
    }

    @Test
    void shouldThrowErrorNotificationWhenTryToLoginNonExistUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        loginPage.invalidLogin(authInfo);
        loginPage.errorNote();
    }

    @Test
    void shouldNotLoginWhenVerificationCodeIsWrong() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var info = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(info);
        verificationPage.isVerificationPageVisible();
        var code = DataHelper.generateRandomCode();
        verificationPage.invalidVerify(code);
        verificationPage.isNotificationErrorVisible();
    }

}
