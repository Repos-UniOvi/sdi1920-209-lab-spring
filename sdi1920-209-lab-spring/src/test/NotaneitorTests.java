package com.uniovi.tests;
import static org.junit.Assert.*;
import org.junit.*;
public class NotaneitorTests {

    //En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas)):
//static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//static String Geckdriver024 = "C:\\Path\\geckodriver024win64.exe";
//En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas):
//static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
//static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
//Común a Windows y a MACOSX
static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
static String URL = "http://localhost:8090";

public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
System.setProperty("webdriver.firefox.bin", PathFirefox);
System.setProperty("webdriver.gecko.driver", Geckdriver);
WebDriver driver = new FirefoxDriver();
return driver;
}

@Before
public void setUp() throws Exception {
}
@After
public void tearDown() throws Exception {
}
@Test
public void test() {
fail("Not yet implemented");
}
}