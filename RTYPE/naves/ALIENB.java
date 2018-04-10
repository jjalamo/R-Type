package naves;

import java.awt.*;
import javax.swing.*;
import java.util.Random;

/** ********************************************************************************************* */
/** ********************************************************************************************* */

public class ALIENB {
/** Clase que implemenmta las naves alien tipo B */

    private int x;                  /** posicion x en pantalla de la nave alienB */
    private int y;                  /** posicion y en pantalla de la nave alienB */
    private int velocidad;          /** velocidad de la nave alienB */
    private int retardo;            /** retardo de la nave, en aparecer por primera vez en pantalla */
    private int direccion_ejey;     /** direccion en que se mueve la nave en el eje y. +1 se mueve hacia abajo. -1 hacia arriba */
    private int movimiento_ejey;    /** Distancia, aleatoria, que se mueve la nave en el eje y, antes de cambiar de direccion */
    private int alto;               /** alto de la imagen de la nave alienB */
    private int ancho;              /** ancho de la imagen de la nave alienB */
    private Image imagen_alienb;    /** imagen de la nave alienB */
    boolean visible;                /** indica si el alienB es visible o no */
    
/** ********************************************************************************************* */

    public ALIENB(int x, int y, int velocidad, int retardo) {
        /** Constructor de la clase ALIENB.
         * Se le pasa como parametros la posicion de la nave en pantalla (x,y)
         * la velocidad de la nave y el retardo, el tiempo que tarda en aparecer por primera vez en pantalla */
        ImageIcon ii = new ImageIcon(this.getClass().getResource("../images/alienb.png"));
        imagen_alienb = ii.getImage();
        alto = imagen_alienb.getHeight(null);
        ancho = imagen_alienb.getWidth(null);
        visible = true;
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.retardo = retardo;
        direccion_ejey = 1;
        movimiento_ejey = 0;
    }
    
/** ********************************************************************************************* */

    public Image get_image_alienb() {
        /** devuelve la imagen de la nave alienB */
        return imagen_alienb;
    }
    
/** ********************************************************************************************* */

    public int get_posx_alienb() {
        /** devuelve la posicion x de la nave alienB */
        return x;
    }
    
/** ********************************************************************************************* */

    public int get_posy_alienb() {
        /** devuelve la posicion y de la nave alienB */
        return y;
    }
    
/** ********************************************************************************************* */

    public boolean visible_alienb() {
        /** indica si la nave alienB es visible o no */
        return visible;
    }
    
/** ********************************************************************************************* */

    public int get_alto_alienb() {
        /** devuelve el alto de la imagen de la nave alienB */
        return alto;
    }
    
/** ********************************************************************************************* */

    public int get_ancho_alienb() {
        /** devuelve el ancho de la imagen de la nave alienB */
        return ancho;
    }
    
/** ********************************************************************************************* */

    public void set_visible_alienb(boolean v) {
        /** actualiza el indicador de si la nave es visible o no */
        visible = v;
    }
    
/** ********************************************************************************************* */
    
    public void mover_alienb() {
        /** mueve la nave por la pantalla. Si la nave tiene retardo, espera y reduce el retardo.
         * El movimiento sobre el eje y lo realiza en zig zag, pero con distancias de desplazamiento
         * aleatorias, de esta forma, la nave se desplaza hacia arriba una distancia aleatoria,
         * despues hacia abajo, tambien una distancia aleatoria.
         * Si la nave llega al limite inferior o superior de la pantalla y no ha completado la distancia, cambia de direccion
         * Si la nave llega al final de la pantalla (izquierda), aparece de nuevo por la derecha. */
        Random r = new Random();
        if (retardo <= 0) {
            x = x - velocidad;
            if (x < -30) {
                x=800;
            }
            if (movimiento_ejey < 0) {
                movimiento_ejey = r.nextInt(150);
                direccion_ejey = direccion_ejey * (-1);
            }
            y = y + direccion_ejey;
            movimiento_ejey = movimiento_ejey - 1;
            if (y < 0) {
                y = 0;
                direccion_ejey = direccion_ejey * (-1);
            }
            if (y > 515) {
                y = 515;
                direccion_ejey = direccion_ejey * (-1);
            }
            
        } else {
            retardo = retardo - velocidad;

        }
    }
}

/** ********************************************************************************************* */
/** ********************************************************************************************* */

