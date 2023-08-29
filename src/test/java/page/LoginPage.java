package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement buttonContinue = $("[data-test-id=action-login]");
    private SelenideElement errorNote = $("[data-test-id=error-notification] .notification__content");

    private void fieldsFill(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        buttonContinue.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        fieldsFill(info);
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo auth) {
        fieldsFill(auth);
    }

    public void errorNote() {
        errorNote.shouldBe(visible);
    }
}
