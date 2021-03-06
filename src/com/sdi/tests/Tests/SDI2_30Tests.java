package com.sdi.tests.Tests;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import alb.util.date.DateUtil;

import com.sdi.tests.pageobjects.*;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SDI2_30Tests {

	static WebDriver driver = getDriver();
	static String URL = "http://localhost:8280/sdi2-30";
	List<WebElement> elementos = null;

	public SDI2_30Tests() {
	}

	// Metodo para obtencion de driver estatico

	public static WebDriver getDriver() {
		File pathToBinary = new File("S:\\firefox\\FirefoxPortable.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		return driver;
	}

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// @Before
	// public void run() {
	// // Este código es para ejecutar con la versión portale de Firefox 46.0
	// File pathToBinary = new File("S:\\firefox\\FirefoxPortable.exe");
	// FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	// FirefoxProfile firefoxProfile = new FirefoxProfile();
	// driver = new FirefoxDriver(ffBinary, firefoxProfile);
	// driver.get("http://localhost:8280/sdi2-30/");
	// // Este código es para ejecutar con una versión instalada de Firex 46.0
	// // driver = new FirefoxDriver();
	// // driver.get("http://localhost:8180/sdi2-n");
	// }

	// @After
	// public void end() {
	// // Cerramos el navegador
	// //driver.quit();
	// }

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	// PRUEBAS
	// ADMINISTRADOR
	// PR01: Autentificar correctamente al administrador.
	@Test
	public void prueba01() {
		PO_Login.testLoginParametros(driver, "form-admin", "admin1", "admin1");
	}

	// PR02: Fallo en la autenticación del administrador por introducir mal
	// el login.
	@Test
	public void prueba02() throws InterruptedException {
		PO_Login.testLoginErroneoParametros(driver, "form-admin",
				"administradormal", "admin1", "Usuario o clave no valida");
	}

	// PR03: Fallo en la autenticación del administrador por introducir mal
	// la password.
	@Test
	public void prueba03() throws InterruptedException {
		PO_Login.testLoginErroneoParametros(driver, "form-admin", "admin1",
				"administradormal", "Usuario o clave no valida");
	}

	// PR04: Probar que la base de datos contiene los datos insertados con
	// conexión correcta a la base de datos.
	@Test
	public void prueba04() throws SQLException, InterruptedException {
		PO_BaseDeDatos.testComprobarDatosEnLaBase(driver);
	}

	// PR05: Visualizar correctamente la lista de usuarios normales.
	@Test
	public void prueba05() {
		PO_Usuarios.testVisualizarUsuarios(driver, "form-admin", "admin1",
				"admin1");
	}

	// PR06: Cambiar el estado de un usuario de ENABLED a DISABLED. Y tratar
	// de entrar con el usuario que se desactivado.
	@Test
	public void prueba06() throws InterruptedException {
		PO_Usuarios.testModificarStatus(driver, "form-admin", "admin1",
				"admin1", "DISABLED");
		// Volvemos a arrancar el driver en la pagina inicial
		driver.navigate().to(URL);

		PO_Login.testLoginErroneoParametros(driver, "form-login", "user1",
				"user1", "Usuario o clave no valida");
	}

	// PR07: Cambiar el estado de un usuario a DISABLED a ENABLED. Y Y tratar
	// de entrar con el usuario que se ha activado.
	@Test
	public void prueba07() throws InterruptedException {
		PO_Usuarios.testModificarStatus(driver, "form-login", "admin1",
				"admin1", "ENABLED");
		// Volvemos a arrancar el driver en la pagina inicial
		driver.navigate().to(URL);

		PO_Login.testLoginParametros(driver, "form-usuario", "user1", "user1");
	}

	// PR08: Ordenar por Login
	@Test
	public void prueba08() throws InterruptedException {
		PO_Usuarios.testOrdenar(driver, "form-login", "admin1", "admin1",
				"form-usuarios:tablalistado:headerNombre", "user1");
	}

	// PR09: Ordenar por Email
	@Test
	public void prueba09() throws InterruptedException {
		PO_Usuarios.testOrdenar(driver, "form-login", "admin1", "admin1",
				"form-usuarios:tablalistado:headerEmail", "user1");
	}

	// PR10: Ordenar por Status
	@Test
	public void prueba10() throws InterruptedException {
		PO_Usuarios.testOrdenar(driver, "form-login", "admin1", "admin1",
				"form-usuarios:tablalistado:headerStatus", "user1");
	}

	// PR11: Borrar una cuenta de usuario normal y datos relacionados.
	@Test
	public void prueba11() throws InterruptedException {
		PO_Usuarios.testBorrarCuenta(driver, "form-login", "admin1", "admin1",
				"form-usuarios:tablalistado:2:filaId", "");
	}

	// PR12: Crear una cuenta de usuario normal con datos válidos.
	@Test
	public void prueba12() throws InterruptedException {
		PO_Usuarios.testRegistroCorrecto(driver, "usuarioprueba1",
				"usuarioprueba1@gmail.com", "usuarioprueba1", "usuarioprueba1");
	}

	// PR13: Crear una cuenta de usuario normal con login repetido.
	@Test
	public void prueba13() throws InterruptedException {
		PO_Usuarios.testRegistroIncorrecto(driver, "usuarioprueba1",
				"usuarioprueba1@gmail.com", "usuarioprueba1", "usuarioprueba1",
				"El nombre repetido en el sistema");
	}

	// PR14: Crear una cuenta de usuario normal con Email incorrecto.
	@Test
	public void prueba14() throws InterruptedException {
		PO_Usuarios.testRegistroIncorrecto(driver, "usuariopruebaemail",
				"emailincorrecto", "usuariopruebaemail1",
				"usuariopruebaemail1", "El campo Email presenta "
						+ "formato inválido (usuario@servidor.dominio)");
	}

	// PR15: Crear una cuenta de usuario normal con Password incorrecta.
	@Test
	public void prueba15() throws InterruptedException {
		PO_Usuarios.testRegistroIncorrecto(driver, "usuariocontraseña",
				"usuarioprueba1@gmail.com", "usuarioprueba1", "usuarioprueba2",
				"Las contraseñas deben coincidir");
	}

	// USUARIO
	// PR16: Comprobar que en Inbox sólo aparecen listadas las tareas sin
	// categoría y que son las que tienen que. Usar paginación navegando por
	// las tres páginas.
	@Test
	public void prueba16() throws InterruptedException {
		PO_ListadoTareas.testListarInbox(driver, "user1", "user1");
	}

	// PR17: Funcionamiento correcto de la ordenación por fecha planeada.
	@Test
	public void prueba17() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -9);
		PO_ListadoTareas.testOrdenarPorFecha(driver, "user1", "user1",
				DateUtil.toString(calendar.getTime()), "cbSemana");
	}

	// PR18: Funcionamiento correcto del filtrado.
	@Test
	public void prueba18() throws InterruptedException {
		PO_ListadoTareas.testFiltrado(driver);
	}

	// PR19: Funcionamiento correcto de la ordenación por categoría.
	// @Test
	public void prueba19() throws InterruptedException {
		PO_ListadoTareas.testOrdenarPorCategoria(driver, "user1", "user1", "1",
				"cbHoy");
	}

	// PR20: Funcionamiento correcto de la ordenación por fecha planeada.
	@Test
	public void prueba20() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -9);
		PO_ListadoTareas.testOrdenarPorFecha(driver, "user1", "user1",
				DateUtil.toString(calendar.getTime()), "cbHoy");
	}

	// PR21: Comprobar que las tareas que no están en rojo son las de hoy y
	// además las que deben ser.
	@Test
	public void prueba21() throws InterruptedException {
		PO_ListadoTareas.testTareasHoyEnRojo(driver, "user1", "user1");
	}

	// PR22: Comprobar que las tareas retrasadas están en rojo y son las que
	// deben ser.
	@Test
	public void prueba22() throws InterruptedException {
		PO_ListadoTareas.testComprobarTareasEnRojo(driver, "user1", "user1");
	}

	// PR23: Comprobar que las tareas de hoy y futuras no están en rojo y que
	// son las que deben ser.
	@Test
	public void prueba23() throws InterruptedException {
		PO_ListadoTareas.testTareasHoyYFuturas(driver, "user1", "user1");
	}

	// PR24: Funcionamiento correcto de la ordenación por día.
	@Test
	public void prueba24() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -9);
		PO_ListadoTareas.testOrdenarPorFecha(driver, "user1", "user1",
				DateUtil.toString(calendar.getTime()), "cbSemana");
	}

	// PR25: Funcionamiento correcto de la ordenación por nombre.
	@Test
	public void prueba25() throws InterruptedException {
		PO_ListadoTareas.testOrdenarPorNombre(driver, "user1", "user1",
				"cbSemana");
	}

	// PR26: Confirmar una tarea, inhabilitar el filtro de tareas terminadas,
	// ir a la pagina donde está la tarea terminada y comprobar que se
	// muestra en verde.
	@Test
	public void prueba26() throws InterruptedException {
		PO_ListadoTareas.testConfirmacionTarea(driver, "user1", "user1",
				DateUtil.toString(DateUtil.now()));
	}

	// PR27: Crear una tarea sin categoría y comprobar que se muestra en la
	// lista Inbox.
	@Test
	public void prueba27() throws InterruptedException {
		PO_Tarea.testCrearTareaInbox(driver, "user1", "user1",
				"Tarea de Prueba1", "", "", "form-listadoInbox", "cbInbox");
	}

	// PR28: Crear una tarea con categoría categoria1 y fecha planeada Hoy y
	// comprobar que se muestra en la lista Hoy.
	@Test
	public void prueba28() throws InterruptedException {
		PO_Tarea.testCrearTarea(driver, "user1", "user1", "Tarea de Prueba2",
				"1", DateUtil.toString(DateUtil.now()), "form-usuarios",
				"cbHoy");
	}

	// PR29: Crear una tarea con categoría categoria1 y fecha planeada
	// posterior a Hoy y comprobar que se muestra en la lista Semana.
	@Test
	public void prueba29() throws InterruptedException {
		PO_Tarea.testCrearTarea(driver, "user1", "user1", "Tarea de Prueba3",
				"1", DateUtil.toString(DateUtil.now()), "form-usuarios",
				"cbSemana");
	}

	// PR30: Editar el nombre, y categoría de una tarea (se le cambia a
	// categoría1) de la lista Inbox y comprobar que las tres pseudolista se
	// refresca correctamente.
	@Test
	public void prueba30() throws InterruptedException {
		PO_Tarea.testEditarTarea(driver, "user1", "user1", "Tarea editada",
				"1", "cbHoy", "0");
	}

	// PR31: Editar el nombre, y categoría (Se cambia a sin categoría) de una
	// tarea de la lista Hoy y comprobar que las tres pseudolistas se
	// refrescan correctamente.
	@Test
	public void prueba31() throws InterruptedException {
		PO_Tarea.testEditarTarea(driver, "user1", "user1", "Tarea editada", "",
				"cbHoy", "20");
	}

	// PR32: Marcar una tarea como finalizada. Comprobar que desaparece de las
	// tres pseudolistas.
	@Test
	public void prueba32() throws InterruptedException {
		PO_Tarea.testFinalizarTarea(driver, "user1", "user1", "Tarea30",
				"cbSemana");
	}

	// PR33: Salir de sesión desde cuenta de administrador.
	@Test
	public void prueba33() throws InterruptedException {
		PO_CerrarSesion.testCerrarSesion(driver, "admin1", "admin1",
				"form-admin");
	}

	// PR34: Salir de sesión desde cuenta de usuario normal.
	@Test
	public void prueba34() throws InterruptedException {
		PO_CerrarSesion.testCerrarSesion(driver, "user1", "user1",
				"form-usuario");
	}

	// PR35: Cambio del idioma por defecto a un segundo idioma. (Probar algunas
	// vistas)
	@Test
	public void prueba35() throws InterruptedException {
		PO_Internacionalizacion.testCambioSegundoIdioma(driver);
	}

	// PR36: Cambio del idioma por defecto a un segundo idioma y vuelta al
	// idioma por defecto. (Probar algunas vistas)
	@Test
	public void prueba36() throws InterruptedException {
		PO_Internacionalizacion.testCambioIdiomaYVuelta(driver);
	}

	// PR37: Intento de acceso a un URL privado de administrador con un usuario
	// autenticado como usuario normal.
	@Test
	public void prueba37() {
		PO_Seguridad.testSeguridadUsuarioAutenticado(driver, "user1", "user1");
	}

	// PR38: Intento de acceso a un URL privado de usuario normal con un usuario
	// no autenticado.
	@Test
	public void prueba38() {
		PO_Seguridad.testSeguridadUsuarioNoAutenticado(driver);
	}

}