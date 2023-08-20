package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement buttonVerify = $("[data-test-id=action-verify]");
    private SelenideElement errorNote = $("[data-test-id=error-notification] .notification__content");

    public void isVerificationPageVisible() {
        codeField.shouldBe(visible);
    }

    public void isNotificationErrorVisible() {
        errorNote.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        buttonVerify.click();
        return new DashboardPage();
    }

    public SelenideElement invalidVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        buttonVerify.click();
        return errorNote;
    }
}
