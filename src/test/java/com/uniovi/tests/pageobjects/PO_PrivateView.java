package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormLogin(WebDriver driver, String elementName, String elementCriteria,
			String elementCriteriaParam, String username, String password, String successfulLoginCriteria,
			String successfullLoginCriteriaParam) {
		
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		
		PO_HomeView.clickOption(driver, elementName, elementCriteria, elementCriteriaParam);

		PO_LoginView.fillForm(driver, username, password);

		// COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, successfulLoginCriteria, successfullLoginCriteriaParam);
	}
	
	static public void goToPage (WebDriver driver, int pageToGo, List<WebElement> elementos) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
				SeleniumUtils.esperarSegundos(driver, 5);
				
	// Esperamos a que se muestren los enlaces de paginación la lista de notas
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
			
			// Nos vamos a la última página
			elementos.get(pageToGo).click();

	}

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}