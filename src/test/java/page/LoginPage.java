package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement buttonContinue = $("[data-test-id=action-login]");
    private SelenideElement errorNote = $("[data-test-id=error-notification] .notification__content");
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        buttonContinue.click();
        return new VerificationPage();
    }

    public SelenideElement invalidLogin(DataHelper.AuthInfo info) {
        info = DataHelper.generateRandomUser();
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        buttonContinue.click();
        return errorNote;
    }

}
