package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_Tarea {

	public static void testFinalizarTarea(WebDriver driver, String usuario,
			String contraseña, String nombreTarea, String cbLista)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();
		Thread.sleep(500);

		// Clickamos en la tarea a finalizar
		driver.findElement(By.id("form-usuarios:tablalistado:27:filaNombre"))
				.click();

		// Pinchamos en el boton finalizar
		By boton2 = By.id("form-usuarios:eliminar");
		driver.findElement(boton2).click();
		Thread.sleep(500);

		// Comprobamos que la categoria no aparece al estar finalizada
		SeleniumUtils.textoNoPresentePagina(driver, nombreTarea);

		// Vamos a la pseudolista Hoy para comprobar que tampoco aparece
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:opciones",
				"form-cabecera:misTareas");
		Thread.sleep(500);
		SeleniumUtils.ClickCheckbox(driver, cbLista);
		SeleniumUtils.ClickCheckbox(driver, "cbHoy");

		By boton3 = By.id("form-usuario:filtrar");
		driver.findElement(boton3).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);
		
		// Comprobamos que la categoria no aparece al estar finalizada
		SeleniumUtils.textoNoPresentePagina(driver, nombreTarea);
	}
}
