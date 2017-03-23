package com.sdi.tests.pageobjects;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_ListadoTareas {

	public static void testListarInbox(WebDriver driver, String usuario,
			String contraseña) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, "cbInbox");

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-listadoInbox", 10);

		// Comprobamos que no esté las categorias:
		// categoria1, categoria2 y categoria3
		SeleniumUtils.textoNoPresentePagina(driver, "categoria1");
		SeleniumUtils.textoNoPresentePagina(driver, "categoria2");
		SeleniumUtils.textoNoPresentePagina(driver, "categoria3");

		// También comprobamos que cualquier celda de la columna de categorias
		// sea la cadena vacía
		WebElement celdaCategoria = SeleniumUtils.ClickCelda(driver,
				"form-usuarios:tablalistado_data", 4, 3);
		assertEquals("", celdaCategoria);
	}

	public static void testOrdenarPorFecha(WebDriver driver, String usuario,
			String contraseña, String fechaEsperada, String cbLista)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();
		Thread.sleep(500);

		// Esperamos a que se cargue la pagina del listado de tareas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-usuarios:tablalistado:headerFecha", 10);

		// Clickamos en la fecha planeada para ordenarlos
		elementos.get(0).click();
		Thread.sleep(500);

		// Obtenemos el valor de la celda que contiene la fecha planeada
		List<WebElement> celdaFecha = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaStatus", 10);

		// Comprobamos que la fecha es la primera que hay (11/03/2017)
		assertEquals(fechaEsperada, celdaFecha.get(0).getText());
	}
	
	public static void testOrdenarPorCategoria(WebDriver driver, String usuario,
			String contraseña, String categoriaEsperada, String cbLista)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();
		Thread.sleep(500);

		// Esperamos a que se cargue la pagina del listado de tareas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-usuarios:tablalistado:headerCategory", 10);

		// Clickamos en la categoria para ordenarlos
		elementos.get(0).click();
		Thread.sleep(500);

		// Obtenemos el valor de la celda que contiene la categoria
		List<WebElement> celdaCategoria = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaCategoria", 10);

		// Comprobamos que la categoria es la esperada
		assertEquals(categoriaEsperada, celdaCategoria.get(0).getText());
	}
	
	public static void testOrdenarPorNombre(WebDriver driver, String usuario,
			String contraseña, String[] nombresEsperados, String cbLista)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();
		Thread.sleep(500);

		// Esperamos a que se cargue la pagina del listado de tareas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-usuarios:tablalistado:headerTitulo", 10);

		// Clickamos en la categoria para ordenarlos
		elementos.get(0).click();
		Thread.sleep(500);

		// Obtenemos el valor de la primera celda que contiene el nombre
		List<WebElement> celdaCategoria = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaNombre", 10);
		
		// Obtenemos el valor de la ultima celda que contiene el nombre
		List<WebElement> celdaCategoria2 = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:27:filaNombre", 10);

		// Comprobamos que la categoria es la esperada
		assertEquals(nombresEsperados[0], celdaCategoria.get(0).getText());
		assertEquals(nombresEsperados[1], celdaCategoria2.get(0).getText());
	}

}
