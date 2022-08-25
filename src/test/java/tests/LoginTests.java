package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {

    @Test
    public void loginTest() {
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mae_90@mail.ru");
        $("#inputPassword").setValue("2506898qaz");
        $("#btnLogin").click();
        $(".col-lg-12").shouldHave(text("Projects"));
    }

    @Test
    public void testCaseTest() {
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mae_90@mail.ru");
        $("#inputPassword").setValue("2506898qaz");
        $("#btnLogin").click();
        $("#createButton").click();
        $(".col-lg-12").shouldHave(text("New Project"));
        $("[name='title']").setValue("Selenide");
        $("[name='code']").setValue("code");
        $("#public-access-type").click();
        $(".btn.btn-primary").click();
        $("#create-case-button").shouldBe(text("Case")).click();
        $("#title").setValue("Selenide");
        $("#save-case").click();
        $(".OL6rtE").shouldHave(text("Test case was created successfully!"));
    }

    @Test
    public void defectTest() {
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mae_90@mail.ru");
        $("#inputPassword").setValue("2506898qaz");
        $("#btnLogin").click();
        $(By.xpath("//*[@class='project-row'] //a[text()='Demo Project']")).click();
        $("[title='Defects']").click();
        $(".btn.btn-primary").shouldBe(text("Create new defect")).click();
        $("#title").setValue("Defect");
        $(By.xpath("//p[@class='empty-node']")).setValue("Result");
        $("[type='submit']").click();
        $(".UqeSuA.NYzx5I").shouldHave(text("Defect was created successfully!"));

    }
    @Test
    public void deleteDefectTest(){
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mae_90@mail.ru");
        $("#inputPassword").setValue("2506898qaz");
        $("#btnLogin").click();
        $(By.xpath("//*[@class='project-row'] //a[text()='Demo Project']")).click();
        $("[title='Defects']").click();
        $(".fa.fa-ellipsis-h").click();
        $(".text-danger").click();
        $(By.xpath("//*[@class='UdZcu9' and text()='Delete']")).click();
        $(".UqeSuA.NYzx5I").shouldHave(text("Defect [D-5] was successfully deleted."));
    }
}
