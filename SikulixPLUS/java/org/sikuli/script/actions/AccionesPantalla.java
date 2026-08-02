package org.sikuli.script.actions;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;

/**
 * Fachada simple sobre Screen/Region pensada para llamarse directamente
 * (ej. desde ClickTest), sin pasar por el patron Screenplay/Serenity.
 * Las imagenes se referencian solo por nombre (sin ".png") y se resuelven
 * contra src/main/resources.
 */
public final class AccionesPantalla {

  private static final File CARPETA_IMAGENES = new File("src/main/resources");
  private static final String RUTA_IMAGENES = CARPETA_IMAGENES.getAbsolutePath();
  private static final int SEGUNDOS_ESPERA_DEFAULT = 10;

  private static final Screen screen = new Screen();

  private AccionesPantalla() {
  }

  private static Pattern patronPara(String imagen) {
    return new Pattern(RUTA_IMAGENES + File.separator + imagen + ".png").exact();
  }

  /**
   * @param imagen nombre de la imagen (sin ".png")
   * @return true si la imagen esta visible en pantalla ahora mismo
   */
  public static boolean existe(String imagen) {
    return screen.exists(patronPara(imagen)) != null;
  }

  public static void click(String imagen) {
    try {
      screen.click(patronPara(imagen));
    } catch (FindFailed e) {
      throw new RuntimeException("No se pudo hacer click sobre la imagen: " + imagen, e);
    }
  }

  public static void clickDerecho(String imagen) {
    try {
      screen.rightClick(patronPara(imagen));
    } catch (FindFailed e) {
      throw new RuntimeException("No se pudo hacer click derecho sobre la imagen: " + imagen, e);
    }
  }

  public static void dobleClick(String imagen) {
    try {
      screen.doubleClick(patronPara(imagen));
    } catch (FindFailed e) {
      throw new RuntimeException("No se pudo hacer doble click sobre la imagen: " + imagen, e);
    }
  }

  public static void hover(String imagen) {
    try {
      screen.hover(patronPara(imagen));
    } catch (FindFailed e) {
      throw new RuntimeException("No se pudo hacer hover sobre la imagen: " + imagen, e);
    }
  }

  /**
   * Espera hasta "segundos" a que la imagen aparezca. Si no aparece, lanza excepcion.
   */
  public static void esperar(String imagen, int segundos) {
    try {
      screen.wait(patronPara(imagen), segundos);
    } catch (FindFailed e) {
      throw new RuntimeException(
          "No se encontro la imagen esperada '" + imagen + "' despues de " + segundos + " segundos", e);
    }
  }

  /**
   * Espera hasta SEGUNDOS_ESPERA_DEFAULT a que la imagen sea visible y le hace click.
   * Si no aparece a tiempo, no lanza excepcion: simplemente no hace nada.
   */
  public static void clickSiVisible(String imagen) {
    clickSiVisible(imagen, SEGUNDOS_ESPERA_DEFAULT);
  }

  public static void clickSiVisible(String imagen, int segundosEspera) {
    Match match = screen.exists(patronPara(imagen), segundosEspera);
    if (match == null) {
      Debug.log(1, "AccionesPantalla.clickSiVisible: imagen no encontrada, se continua sin click: %s", imagen);
      return;
    }
    match.click();
  }

  public static void escribir(String texto) {
    screen.type(texto);
  }
}
