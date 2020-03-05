package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_TeacherForm extends PO_NavView{

	static public void fillForm(WebDriver driver, String dnip, String namep, String lastnamep, String categoryp) {
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("surname"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);
		WebElement catergory = driver.findElement(By.name("category"));
		catergory.click();
		catergory.clear();
		catergory.sendKeys(categoryp);
	
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
