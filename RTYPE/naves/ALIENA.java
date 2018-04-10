package naves;

import java.awt.*;
import javax.swing.*;

/** ********************************************************************************************* */
/** ********************************************************************************************* */

public class ALIENA {
/** Clase que implementa las naves alien tipo A. */
    private int x;                  /** Posicion x de la nave alienA */
    private int y;                  /** posicion y de la nave alienA */
    private int velocidad;          /** velocidad de la nave alienA */
    private int retardo;            /** retardo en la aparicion de la nave en pantalla por primera vez */
    private int alto;               /** alto de la imagen de la nave alienA */
    private int ancho;              /** ancho de la imagen de la nave alienA */
    private Image imagen_aliena;    /** imagen de la nave alienA */
    boolean visible;                /** indica si la nave alienA es visible o no */
    
/** ********************************************************************************************* */

    public ALIENA(int x, int y, int velocidad, int retardo) {
        /** Constructor de la nave alienA. Se le pasan como parametros la posicion inicial
         * en pantalla (x,y), la velocidad de la nave y el retardo que tiene la nave para
         * aparecer en pantalla. */
        ImageIcon ii = new ImageIcon(this.getClass().getResource("../images/aliena.png"));
        imagen_aliena = ii.getImage();
        alto = imagen_aliena.getHeight(null);
        ancho = imagen_aliena.getWidth(null);
        visible = true;
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.retardo = retardo;
    }
    
/** ********************************************************************************************* */

    public Image get_image_aliena() {
        /** Devuelve la imagen de la nave alienA */
        return imagen_aliena;
    }
    
/** ********************************************************************************************* */

    public int get_posx_aliena() {
        /** devuelve la posicion x de la nave alienA */
        return x;
    }
    
/** ********************************************************************************************* */

    public int get_posy_aliena() {
        /** Devuelve la posicion y de la nave alienA */
        return y;
    }
    
/** ********************************************************************************************* */

    public boolean visible_aliena() {
        /** Indica si la nave alienA es visible en pantalla o no */
        return visible;
    }
    
/** ********************************************************************************************* */

    public int get_alto_aliena() {
        /** devuelve el alto de la imagen de la nave alienA */
        return alto;
    }
    
/** ********************************************************************************************* */

    public int get_ancho_aliena() {
        /** Devuelve el ancho de la imagen de la nave alienA */
        return ancho;
    }
    
/** ********************************************************************************************* */

    public void set_visible_aliena(boolean v) {
        /** Actualiza el indicador de nave visible */
        visible = v;
    }

/** ********************************************************************************************* */
    
    public void mover_aliena() {
        /** Mueve la nave alienA, solo por el eje x de derch a izq. Si la nave tiene retardo, no la muestra 
         * en pantalla y reduce el retardo, esta accion evita que al salir las naves en pantalla por primera
         * vez se solapen. */
        if (retardo <= 0) {
            x = x - velocidad;
            if (x < -30) {
                x=800;
            }
        } else {
            retardo = retardo - velocidad;
        }
    }
}
/** ********************************************************************************************* */

