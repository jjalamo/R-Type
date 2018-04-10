package naves;

import java.awt.*;
import javax.swing.*;

/** ********************************************************************************************* */
/** ********************************************************************************************* */

public class MISIL {
/** Implementa los misiles que dispara la nave aliada */
/** los misiles se desplazan de izq a derch con velocidad constante y sin variar su trayectoria */

    private int x;                  /** Posicion x del misil */
    private int y;                  /** posicion y del misil */
    private int velocidad;          /** velocidad del misil */
    private int alto;               /** almacena el alto de la imagen del misil */
    private int ancho;              /** almacena el ancho de la imagen del misil */
    private Image imagen_misil;     /** imagen del misil */
    boolean visible;                /** indica si el misil es visible o no, si esta dentro de los limites de la pantalla */

/** ********************************************************************************************* */
    
    public MISIL(int x, int y, int velocidad) {
    /** Constructor de la clase MISIL. Se le pasan como parametros la posion incial del misil
     * en pantalla (x,y) y la velocidad del misil*/
     
        /** cargamos la imagen del misil, almacenamos su alto y ancho, lo hacemos visible
         * y actualizamos sus componentes de coordenadas (x,y) y velocidad */
        ImageIcon ii = new ImageIcon(this.getClass().getResource("../images/misil.png"));
        imagen_misil = ii.getImage();
        alto = imagen_misil.getHeight(null);
        ancho = imagen_misil.getWidth(null);
        visible = true;
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
    }
    
/** ********************************************************************************************* */

    public Image get_image_misil() {
        /** devuelve la imagen del misil */
        return imagen_misil;
    }
    
/** ********************************************************************************************* */

    public int get_posx_misil() {
        /** devuelve la posion x del misil */
        return x;
    }
    
/** ********************************************************************************************* */

    public int get_posy_misil() {
        /** devuelve la posiicion y del misil */
        return y;
    }
    
/** ********************************************************************************************* */

    public boolean visible_misil() {
        /** indica si el misil es visible o no */
        return visible;
    }
    
/** ********************************************************************************************* */

    public int get_alto_misil() {
        /** devuelve el alto de la imagen del misil */
        return alto;
    }
    
/** ********************************************************************************************* */

    public int get_ancho_misil() {
        /** devuelve el ancho de la imagen del misil */
        return ancho;
    }
    
/** ********************************************************************************************* */

    public void set_visible_misil(boolean v) {
        /** actualiza el marcador visible del misil */
        visible = v;
    }
    
/** ********************************************************************************************* */
    
    public void mover_misil() {
        /** mueve el misil por la pantalla hacia la derecha.
         * comprueba si se ha salido de la pantalla, en cuyo caso
         * actualiza visible a falso para que el misil sea eliminado */
        x = x+1+velocidad;
        if (x > 800) {
            visible = false;
        }
    }
}
/** ********************************************************************************************* */
