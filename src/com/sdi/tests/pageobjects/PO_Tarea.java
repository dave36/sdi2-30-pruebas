package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_Tarea {

	public static void testCrearTareaInbox(WebDriver driver, String usuario,
			String contraseña, String nombreTarea, String categoria,
			String fecha, String form, String cbLista)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Seleccionamos la opción de menú para añadir tarea
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:opciones",
				"form-cabecera:añadirTarea");

		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-añadirTarea", 10);

		new PO_AltaForm().rellenaFormularioAñadirTarea(driver, nombreTarea, "",
				categoria, fecha);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Cargamos la navegación
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "ui-paginator-last", 10);

		// Clickamos en la navegación
		for (int i = 0; i < elementos.size(); i++)
			elementos.get(i).click();

		// Comprobamos que está la tarea recién creada
		SeleniumUtils.textoPresentePagina(driver, nombreTarea);
	}

	public static void testCrearTarea(WebDriver driver, String usuario,
			String contraseña, String nombreTarea, String categoria,
			String fecha, String form, String cbLista)
			throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Seleccionamos la opción de menú para añadir tarea
		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:opciones",
				"form-cabecera:añadirTarea");

		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-añadirTarea", 10);

		new PO_AltaForm().rellenaFormularioAñadirTarea(driver, nombreTarea, "",
				categoria, fecha);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();

		// Cargamos la página del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", form, 10);

		// Comprobamos que está la tarea recién creada
		SeleniumUtils.textoPresentePagina(driver, nombreTarea);
	}

	public static void testEditarTarea(WebDriver driver, String usuario,
			String contraseña, String nombreTarea, String categoria,
			String cbLista, String fila) throws InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);

		// Pinchamos la opción de filtrar la lista de inbox
		SeleniumUtils.ClickCheckbox(driver, cbLista);

		By boton = By.id("form-usuario:filtrar");
		driver.findElement(boton).click();
		Thread.sleep(250);

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);
		Thread.sleep(250);

		// Seleccionamos la primera tarea de la fila
		driver.findElement(
				By.id("form-usuarios:tablalistado:" + fila + ":filaNombre"))
				.click();

		// Pinchamos en el boton editar tarea
		By boton2 = By.id("form-usuarios:editarTarea");
		driver.findElement(boton2).click();

		// Esperamos a que cargue la página de editar tarea
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-editarTarea", 10);

		// Rellenamos el formulario para la edición de la tarea
		new PO_AltaForm().rellenaFormularioEditarTarea(driver, nombreTarea,
				"Editando tarea", categoria, "");

		Thread.sleep(500);

		// Comprobamos que la tarea se editó correctamente
		SeleniumUtils.textoPresentePagina(driver, nombreTarea);
	}

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
		SeleniumUtils.ClickCheckbox(driver, "cbHoy");

		By boton3 = By.id("form-usuario:filtrar");
		driver.findElement(boton3).click();

		// Esperamos a que se cargue la pagina del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);

		// Comprobamos que la categoria no aparece al estar finalizada
		SeleniumUtils.textoNoPresentePagina(driver, nombreTarea);
	}
}
