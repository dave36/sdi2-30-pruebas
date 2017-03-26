package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_Internacionalizacion {

	public static void testCambioSegundoIdioma(WebDriver driver)
			throws InterruptedException {
		// Comprobamos que estamos en la vista de login en español
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesión");

		// Cambiamos el idioma a inglés
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:idioma",
				"form-cabecera:ingles");

		// Esperamos a que se actualice la pagina
		Thread.sleep(250);

		// Comprobamos que los textos están ahora en inglés
		SeleniumUtils.textoPresentePagina(driver, "Log in");
		SeleniumUtils.textoPresentePagina(driver, "Task management system");
		SeleniumUtils.textoPresentePagina(driver, "User");

		// Nos registramos con un usario para probar otra vista
		new PO_AltaForm().rellenaFormularioLogin(driver, "user1", "user1");
		
		// Esperamos a que cargue la página del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);
		Thread.sleep(250);

		// Comprobamos que los textos están ahora en inglés
		SeleniumUtils.textoPresentePagina(driver, "Welcome user1");
		SeleniumUtils.textoPresentePagina(driver, "Filtering mode");
		SeleniumUtils.textoPresentePagina(driver, "Today");
	}

	public static void testCambioIdiomaYVuelta(WebDriver driver)
			throws InterruptedException {
		// Comprobamos que estamos en la vista de login en español
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesión");

		// Cambiamos el idioma a inglés
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:idioma",
				"form-cabecera:ingles");

		// Esperamos a que se actualice la pagina
		Thread.sleep(250);

		// Comprobamos que los textos están ahora en inglés
		SeleniumUtils.textoPresentePagina(driver, "Log in");
		SeleniumUtils.textoPresentePagina(driver, "Task management system");
		SeleniumUtils.textoPresentePagina(driver, "User");

		// Cambiamos el idioma a español
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:idioma",
				"form-cabecera:español");

		// Esperamos a que se actualice la pagina
		Thread.sleep(250);

		// Comprobamos que los textos vuelven a estar en español
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesión");
		SeleniumUtils.textoPresentePagina(driver,
				"Sistema de gestión de tareas");
		SeleniumUtils.textoPresentePagina(driver, "Usuario");

		// Nos registramos con un usario para probar otra vista
		new PO_AltaForm().rellenaFormularioLogin(driver, "user1", "user1");
		
		// Esperamos a que cargue la página del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);
		Thread.sleep(250);
		
		// Cambiamos el idioma a inglés
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:idioma",
				"form-cabecera:ingles");

		// Esperamos a que se actualice la pagina
		Thread.sleep(250);

		// Comprobamos que los textos están ahora en inglés
		SeleniumUtils.textoPresentePagina(driver, "Welcome user1");
		SeleniumUtils.textoPresentePagina(driver, "Filtering mode");
		SeleniumUtils.textoPresentePagina(driver, "Today");

		// Cambiamos el idioma a español
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:idioma",
				"form-cabecera:español");

		// Esperamos a que se actualice la pagina
		Thread.sleep(250);

		// Comprobamos que los textos vuelven a estar en español
		SeleniumUtils.textoPresentePagina(driver, "Bienvenido user1");
		SeleniumUtils.textoPresentePagina(driver, "Modo de filtrado");
		SeleniumUtils.textoPresentePagina(driver, "Hoy");
	}

}
