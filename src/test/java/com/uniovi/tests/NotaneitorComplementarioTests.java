package com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.services.TeachersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_TeacherForm;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementarioTests {

// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas)):
//static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//static String Geckdriver024 = "C:\\Path\\geckodriver024win64.exe";

	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
//static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
//static String Geckdriver024 = "/home/delacal/selenium/geckodriver024mac";

	// LINUX
	static String PathFirefox65 = "/usr/bin/firefox";
	// static String Geckdriver024 = "/usr/bin/geckodriver"; //Repo
	static String Geckdriver024 = "/home/danielm/Descargas/PL-SDI-Sesión5-material/PL-SDI-Sesión5-material/geckodriver";
//Común a Windows y a MACOSX

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Creacion exitosa de profesor
	@Test
	public void PR01() {

		String dniNuevoProfesor = "99899880A";

		// Iniciar sesion como administrador
		PO_PrivateView.fillFormLogin(driver, "login", "class", "btn btn-primary", "99999988F", "123456", "text",
				"99999988F");

		// Pinchamos en la opción de menu de Profesores: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'teachers-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de añadir profesores.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/teacher/add')]");
		elementos.get(0).click();

		PO_TeacherForm.fillForm(driver, dniNuevoProfesor, "Josefino", "Perez", "Becario");
		PO_View.getP();

		// COmprobamos que se haya añadido el profesor
		PO_View.checkElement(driver, "text", dniNuevoProfesor);
	}

	// Creacion erronea de profesor
	@Test
	public void PR02() {

		String dniNuevoProfesor = "99899880A";

		// Iniciar sesion como administrador
		PO_PrivateView.fillFormLogin(driver, "login", "class", "btn btn-primary", "99999988F", "123456", "text",
				"99999988F");

		// Pinchamos en la opción de menu de Profesores: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'teachers-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de añadir profesores.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/teacher/add')]");
		elementos.get(0).click();

		PO_TeacherForm.fillForm(driver, dniNuevoProfesor, "Josefino", "Perez", "Becario");
		PO_TeacherForm.checkKey(driver, "Error.teacher.dni.duplicate", PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_TeacherForm.fillForm(driver, "99999990B", "Jose",  "Perez", "Becario");
		// COmprobamos el error de Nombre corto .
		PO_TeacherForm.checkKey(driver, "Error.teacher.name.length", PO_Properties.getSPANISH());
		//Rellenamos el formulario
		PO_TeacherForm.fillForm(driver, "999999900", "Jose",  "Perez", "Becario");
		// COmprobamos el error de DNI sin letra .
		PO_TeacherForm.checkKey(driver, "Error.teacher.dni.finalChar", PO_Properties.getSPANISH());
	}
	
	//Comprobar que solo usuarios autorizados pueden agregar profesores
	@Test
	public void PR03() {

		// Iniciar sesion como alumno
		PO_PrivateView.fillFormLogin(driver, "login", "class", "btn btn-primary", "99999990A", "123456", "text",
				"99999990A");

		// Pinchamos en la opción de menu de Profesores: /
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'teachers-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de añadir profesores.
		driver.navigate().to("http://localhost:8090/teacher/add");

		assertEquals("HTTP Status 403 – Forbidden", driver.getTitle());
		
		
	}
}
