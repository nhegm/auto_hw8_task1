package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import data.SQLHelper;

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

    private void fieldsFill(String verificationCode) {
        codeField.setValue(verificationCode);
        buttonVerify.click();
    }
    public DashboardPage validVerify() {
        fieldsFill(SQLHelper.getVerificationCode().getVerificationCode());
        return new DashboardPage();
    }

    public void invalidVerify() {
        fieldsFill(DataHelper.generateRandomCode().getVerificationCode());
    }
}
