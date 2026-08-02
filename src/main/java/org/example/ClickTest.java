
package org.example;

import org.sikuli.script.actions.AccionesPantalla;

public class ClickTest {
    public static void main(String[] args) throws Exception {
        Thread.sleep(2000);

        AccionesPantalla.click("img1");
        AccionesPantalla.hover("img2");
        AccionesPantalla.clickSiVisible("img2",2);
        AccionesPantalla.escribir("hola");

        System.out.println("Click realizado sobre: img1");
    }
}
