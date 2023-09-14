import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTest  extends TestBase{

    @DisplayName("заполнение формы с помощью steps")
    @Owner("HoruzhayaMarinaa")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("simple")
    @Test
    void fillFormTest() {
        step("Open main page",() -> {
                    open("/automation-practice-form");
                    executeJavaScript("$('#fixedban').remove()");
                    executeJavaScript("$('footer').remove()");
                }
        );

        step("Fill the registration-form",() -> {
                    $("#firstName").setValue("Marina");
                    $("#lastName").setValue("Horuzhaya");
                    $("#userEmail").setValue("marinakh192@gmail.com");
                    $("#genterWrapper").$(byText("Female")).click();
                    $("#userNumber").setValue("8076009123");
                    $("#dateOfBirthInput").click();
                    $("select.react-datepicker__month-select").selectOption("August");
                    $(".react-datepicker__year-select").click();
                    $(".react-datepicker__year-select").selectOption("2002");
                    $(".react-datepicker__day.react-datepicker__day--019").click();
                    $("#subjectsInput").setValue("Maths").pressEnter();
                    $("#hobbiesWrapper").$(byText("Sports")).click();
                    $("#uploadPicture").uploadFromClasspath("homework1.png");
                    $("#currentAddress").setValue("Bobryiskaya");
                    $("#state").click();
                    $("#react-select-3-option-1").click();
                    $("#city").click();
                    $("#react-select-4-option-0").click();
                    $("#submit").click();
                }
        );

        step("Check results",() -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Marina Horuzhaya"));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("marinakh192@gmail.com"));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("8076009123"));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("19 August,2002"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("homework1.png"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Bobryiskaya"));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Agra"));
            $("#closeLargeModal").click();
        }
        );
    }
}
