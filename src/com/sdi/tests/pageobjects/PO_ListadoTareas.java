package com.sdi.tests.pageobjects;

import static org.junit.Assert.assertEquals;
import alb.util.date.DateUtil;

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

		// Comprobamos que cualquier celda de la columna de categorias
		// sea la cadena vacía
		WebElement celdaCategoria = SeleniumUtils.ClickCelda(driver,
				"form-usuarios:tablalistado_data", 4, 3);
		assertEquals("", celdaCategoria.getText());
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
		List<WebElement> celdaFecha = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-usuarios:tablalistado:0:filaStatus", 10);

		// Comprobamos que la fecha es la primera que hay (11/03/2017)
		assertEquals(fechaEsperada, celdaFecha.get(0).getText());
	}

	public static void testFiltrado(WebDriver driver)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, "user1", "user1");

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, "cbInbox");

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();
		Thread.sleep(500);

		// Esperamos a que se cargue la pagina del listado de tareas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-usuarios:tablalistado:headerTitulo:filter", 10);

		// Clickamos en la categoria para ordenarlos
		elementos.get(0).click();
		elementos.get(0).sendKeys("10");
		Thread.sleep(500);
		
		// Comprobamos que la tarea 10 está en la página
		SeleniumUtils.textoPresentePagina(driver, "Tarea10");
	}

	public static void testOrdenarPorCategoria(WebDriver driver,
			String usuario, String contraseña, String categoriaEsperada,
			String cbLista) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista
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
		List<WebElement> celdaCategoria = SeleniumUtils.EsperaCargaPagina(
				driver, "id", "form-usuarios:tablalistado:0:filaCategoria", 10);

		// Comprobamos que la categoria es la esperada
		assertEquals(categoriaEsperada, celdaCategoria.get(0).getText());
	}

	public static void testOrdenarPorNombre(WebDriver driver, String usuario,
			String contraseña, String cbLista) throws InterruptedException {
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
		List<WebElement> celdaCategoria = SeleniumUtils.EsperaCargaPagina(
				driver, "id", "form-usuarios:tablalistado:0:filaNombre", 10);

		// Comprobamos que el nombre de la tarea es el esperado
		// después de la ordenación
		assertEquals("Tarea1", celdaCategoria.get(0).getText());
	}

	public static void testTareasHoyEnRojo(WebDriver driver, String usuario,
			String contraseña) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, "cbHoy");

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);

		// Obtenemos todos los elementos de la tabla que estén en rojo,
		// es decir, todos los que estén retrasados
		List<WebElement> elementosRojos = SeleniumUtils.EsperaCargaPagina(
				driver, "class", "colorRed", 10);

		// Comprobamos que ninguna de las tareas retrasadas tenga la fecha
		// del día de hoy
		for (int i = 0; i < elementosRojos.size(); i++)
			assertEquals(
					false,
					elementosRojos.get(i).getText()
							.contains(DateUtil.toString(DateUtil.now())));
	}

	public static void testComprobarTareasEnRojo(WebDriver driver,
			String usuario, String contraseña) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, "cbHoy");

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);

		// Obtenemos todos los elementos de la tabla que estén en rojo,
		// es decir, todos los que estén retrasados
		List<WebElement> elementosRojos = SeleniumUtils.EsperaCargaPagina(
				driver, "class", "colorRed", 10);

		// Comprobamos que las tareas en rojo son las que tienen que ser
		for (int i = 0; i < elementosRojos.size(); i++)
			assertEquals(true,
					elementosRojos.get(i).getText()
							.contains("Tarea" + (21 + i)));
	}

	public static void testTareasHoyYFuturas(WebDriver driver, String usuario,
			String contraseña) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, "cbSemana");

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);

		// Obtenemos todos los elementos de la tabla que estén en rojo,
		// es decir, todos los que estén retrasados
		List<WebElement> elementosRojos = SeleniumUtils.EsperaCargaPagina(
				driver, "class", "colorRed", 10);

		// Comprobamos que ninguna de las tareas retrasadas tenga la fecha
		// del día de hoy en el listado de semana
		for (int i = 0; i < elementosRojos.size(); i++)
			assertEquals(
					false,
					elementosRojos.get(i).getText()
							.contains(DateUtil.toString(DateUtil.now())));
	}

	public static void testConfirmacionTarea(WebDriver driver, String usuario,
			String contraseña, String nombreTarea) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, "cbInbox");

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-listadoInbox", 10);

		// Clickamos en la tarea a finalizar
		driver.findElement(By.id("form-usuarios:tablalistado:4:filaNombre"))
				.click();

		// Pinchamos en el boton finalizar
		By boton2 = By.id("form-usuarios:eliminar");
		driver.findElement(boton2).click();
		Thread.sleep(500);

		// Pinchamos la opción de filtrar la lista de inbox
		driver.findElement(
				By.xpath("//div[@id='form-listadoInbox:"
						+ "filtrarTodas']/div[2]/span")).click();

		By boton3 = By.id("form-listadoInbox:filtrar");
		driver.findElement(boton3).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-listadoInbox", 10);

		Thread.sleep(500);

		// Cargamos la navegación del listado de inbox
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "ui-paginator-last", 10);

		// Clickamos en la navegación para ir a la última página
		elementos.get(0).click();
		Thread.sleep(500);

		// Comprobamos que está la tarea finalizada
		SeleniumUtils.textoPresentePagina(driver,
				DateUtil.toString(DateUtil.now()));

		// Obtenemos todos los elementos de la tabla que estén en rojo,
		// es decir, todos los que estén retrasados
		List<WebElement> elementosVerdes = SeleniumUtils.EsperaCargaPagina(
				driver, "class", "colorVerde", 10);

		// Comprobamos que las tareas en rojo son las que tienen que ser
		assertEquals(true, elementosVerdes.get(0).getText().contains("Tarea5"));
	}

}
