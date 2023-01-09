package com.example.seleniumtestcases;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.headless=true;
        Configuration.timeout=60;
    }

    @Test
    public void adminLogin(){
        open("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/login");
        //go to admin login
        $(By.xpath("//*[@id=\"login\"]/a[2]")).click();

        //verify if admin login page opens
        String adminlogin=  webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/admin_login",adminlogin);

        //login as admin
        $(By.xpath("//*[@id=\"login\"]/input[2]")).setValue("admin");
        $(By.xpath("//*[@id=\"login\"]/input[3]")).setValue("admin");
        $(By.id("login_button")).click();

        //test if admin is loggedIn
        String loggedIn = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/admin_profile",loggedIn);

        webdriver().driver().clearCookies();



    }
    @Test
    public void userSignup() throws InterruptedException {
        open("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/home");
        //go to home page
        $(By.id("logintag")).click();

        //verifying if login page opens
        String login = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/login",login);

        //click on the signUp button
        $(By.xpath("//*[@id=\"login\"]/a[3]")).click();

        //verifying if signup page opens
        String signup = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/signup",signup);

        //signup by entering details in the given fields
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        $(By.xpath("//*[@id=\"login\"]/input[2]")).setValue("username"+randomInt);
        $(By.xpath("//*[@id=\"login\"]/input[3]")).setValue("bukhari");
        $(By.xpath("//*[@id=\"login\"]/input[4]")).setValue("username"+randomInt+"@gmail.com");
        $(By.xpath("//*[@id=\"password\"]")).setValue("hussain");
        $(By.xpath("//*[@id=\"confirm_password\"]")).setValue("hussain");
        $(By.xpath("//*[@id=\"login_button\"]")).click();

        //Check if the user is stored or not
        String store = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/store",store);


        webdriver().driver().clearCookies();
    }

    @Test
    public void userLogin() throws InterruptedException {
        //open login page after signup
        open("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/login");

        //login the new signed up user
        $(By.xpath("//*[@id=\"login\"]/input[2]")).setValue("username1@gmail.com");
        $(By.xpath("//*[@id=\"login\"]/input[3]")).setValue("hussain");
        $(By.xpath("//*[@id=\"login_button\"]")).click();

        String userprofile=  webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/user_profile",userprofile);
    }

    @Test
    public  void insertcar(){
        open("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/login");
        //go to admin login
        $(By.xpath("//*[@id=\"login\"]/a[2]")).click();

        //verify if admin login page opens
        String adminlogin=  webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/admin_login",adminlogin);

        //login as admin
        $(By.xpath("//*[@id=\"login\"]/input[2]")).setValue("admin");
        $(By.xpath("//*[@id=\"login\"]/input[3]")).setValue("admin");
        $(By.id("login_button")).click();

        //test if admin is loggedIn
        String loggedIn = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/admin_profile",loggedIn);

        $(By.id("carsdata")).click();

        //test if show cars data page opens
        String showcars = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/showcars",showcars);

        //click the insert button
        $(By.id("insertbutton")).click();

        //test if cars data page opens
        String carsdata = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/cars_data",carsdata);

        //Entering data in the fields
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        $(By.xpath("//*[@id=\"addcar\"]/form/div[1]/input")).setValue("TestCar"+randomInt);
        $(By.xpath("//*[@id=\"addcar\"]/form/div[2]/input")).setValue("Suv");
        $(By.xpath("//*[@id=\"addcar\"]/form/div[3]/input")).setValue("https://assets.whichcar.com.au/image/private/s--hnUExv2S--/ar_2.304921968787515,c_fill,f_auto,g_xy_center,q_auto:good,x_2262,y_1902/c_scale,w_768/v1/Wheels/Reviews/2021_Chevrolet_Silverado_1500_LTZ_Premium_black_dynamic_front_road_CBrunelli.jpg");
        $(By.xpath("//*[@id=\"addcar\"]/form/div[4]/input")).setValue("10000");
        $(By.xpath("//*[@id=\"addcar\"]/form/div[5]/input")).setValue("Yes");
        $(By.xpath("//*[@id=\"addcar\"]/form/input[2]")).click();

        String storecar = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/storecar",storecar);
    }

//    @Test
//    public void SedanCars() throws InterruptedException {
//        //open login page after signup
//        open("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/login");
//
//        //login the new signed up user
//        $(By.xpath("//*[@id=\"login\"]/input[2]")).setValue("username1@gmail.com");
//        $(By.xpath("//*[@id=\"login\"]/input[3]")).setValue("hussain");
//        $(By.xpath("//*[@id=\"login_button\"]")).click();
//
//        String userprofile=  webdriver().driver().getCurrentFrameUrl();
//        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/user_profile",userprofile);
//        //Click browse car tag
//        $(By.xpath("//*[@id=\"navbarCollapse\"]/div[1]/a[2]")).click();
//
//        //test if the browse cars page opens
//        String browsecars = webdriver().driver().getCurrentFrameUrl();
//        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/browse_cars", browsecars);
//
//        //Select Sedan category
//        $(By.xpath("//*[@id=\"sedan\"]")).click();
//
//        //Test if the page with sedan cars opens
//        String sedan = webdriver().driver().getCurrentFrameUrl();
//        assertEquals("http://ec2-52-196-122-219.ap-northeast-1.compute.amazonaws.com:8181/showsedan",sedan);
//
//        webdriver().driver().clearCookies();
//    }
}
