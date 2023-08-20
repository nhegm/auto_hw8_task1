package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class BankLoginTest {

    @AfterAll
    static void teardown() {
        SQLHelper.cleanDataBase();
    }

    @Test
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.isVerificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getVerificationCode());
    }

    @Test
    void shouldThrowErrorNotificationWhenTryToLoginNonExistUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    void shouldNotLoginWhenVerificationCodeIsWrong() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.isVerificationPageVisible();
        var verificationCode = DataHelper.generateRandomCode();
        verificationPage.invalidVerify(verificationCode.getVerificationCode());
        verificationPage.isNotificationErrorVisible();
    }

}
