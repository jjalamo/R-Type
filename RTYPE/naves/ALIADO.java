package naves;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.Random;

/** ********************************************************************************************* */
/** ********************************************************************************************* */

public class ALIADO {
/** Clase que implementa la nave aliada. */

    private int dx;                     /** direccion en la coordenada x en que se movera la nave aliada, positiva hacia la derch, negativa hacia la izq */
    private int dy;                     /** direccion en la coordenada y en que se movera la nave aliada, positiva hacia abajo, negativa hacia arriba */
    private int x;                      /** posicion x actual de la nave aliada */
    private int y;                      /** posicion y actual de la nave aliada */
    private int velocidad;              /** velocidad a la que se desplaza la nave aliada */ 
    private int nivel;                  /** nivel de dificultad de juego */
    private Image image_aliado;         /** imagen de la nave aliada */
    private int alto;                   /** alto de la imagen de la nave aliada */
    private int ancho;                  /** ancho de la imagen de la nave aliada */
    private ArrayList<MISIL> misiles;   /** Array de misiles disparados y activos */
    private ArrayList<ALIENA> alienA;   /** Array de naves alienA */
    private ArrayList<ALIENB> alienB;   /** Array de naves alienB */
    
/** ********************************************************************************************* */

    public ALIADO() {
        /** Constructor de la clase ALIADO */
        /** carga la imagen de la nave aliada y obtiene su alto y ancho */
        ImageIcon ii = new ImageIcon(this.getClass().getResource("../images/aliado.png"));
        image_aliado = ii.getImage();
        alto = image_aliado.getHeight(null);
        ancho = image_aliado.getWidth(null);
        
        /** Crea el Array de misiles, naves alienA y naves alienB*/
        misiles = new ArrayList<MISIL>();
        alienA = new ArrayList<ALIENA>();
        alienB = new ArrayList<ALIENB>();
        
        /** inicia la posicion inicial de la nave aliada y la velocidad y nivel de juego por defecto */
        x = 20;
        y = 250;
        velocidad = 1;
        nivel = 10;
    }
    
/** ********************************************************************************************* */

    public void mover_aliado() {
        /** actualiza las posiciones x y de la nave aliada en la pantalla
         * y comprueba que no se salga de los limites */
        x = x + dx;
        y = y + dy;
        
        if (x < 0) {
            x = 0;
        }
        if (x > 760) {
            x = 760;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 515) {
            y = 515;
        }
    }

/** ********************************************************************************************* */

    public void set_velocidad(int v) {
        /** actualiza la velocidad de la nave aliada */
        velocidad = v;
    }
    
    public void set_nivel(int n) {
        /** actualiza el nivel de dificultad de la partida */
        nivel = n;
    }
    
    public void set_posx(int px) {
        /** actualiza la posicion x de la nave aliada */
        x = px;
    }
    
    public void set_posy(int py) {
        /** actualiza la posicion y de la nave aliada */
        y = py;
    }
        
/** ********************************************************************************************* */
    
    public int get_posx_aliado() {
        /** devuelve la posicion x en la que se encuentra la nave aliada */
        return x;
    }

/** ********************************************************************************************* */
    
    public int get_posy_aliado() {
        /** devuelve la posicion y en la que se encuentra la nave aliada */
        return y;
    }
    
/** ********************************************************************************************* */

    public int get_alto_aliado() {
        /** devuelve el alto de la imagen de la nave aliada */
        return alto;
    }
    
/** ********************************************************************************************* */

    public int get_ancho_aliado() {
        /** devuelve el ancho de la imagen de la nave aliada */
        return ancho;
    }
    
/** ********************************************************************************************* */

    public Image get_image_aliado() {
        /** devuelve la imagen de la nave aliada */
        return image_aliado;
    }

/** ********************************************************************************************* */

    public ArrayList<MISIL> get_misiles() {
        /** devuelve el Array de misiles */
        return misiles;
    }
    
/** ********************************************************************************************* */

    public ArrayList<ALIENA> get_aliena() {
        /** devuelve el Array de naves alienA */
        return alienA;
    }
    
/** ********************************************************************************************* */

    public ArrayList<ALIENB> get_alienb() {
        /** devuelve el Array de naves alienB */
        return alienB;
    }
    
/** ********************************************************************************************* */

    public void tecla_presionada(KeyEvent e) {
        /** detecta la tecla pulsada y actualiza la direccion x o y 
         * correspondiente en la que se movera la nave aliada */    
        int key = e.getKeyCode();
        if (key == 32) {
            disparar();
        }
        if (key == 79) {
            dx = -1 * velocidad;
        }
        if (key == 80) {
            dx = 1 * velocidad;
        }
        if (key == 81) {
            dy = -1 * velocidad;
        }
        if (key == 65) {
            dy = 1 * velocidad;
        }
    }
    
/** ********************************************************************************************* */

    public void tecla_soltada(KeyEvent e) {
        /** detecta la tecla soltada y actualiza la direccion x o y a 0 */    
        int key = e.getKeyCode();
        if (key == 79) {
            dx = 0;
        }
        if (key == 80) {
            dx = 0;
        }
        if (key == 81) {
            dy = 0;
        }
        if (key == 65) {
            dy = 0;
        }
    }
    
/** ********************************************************************************************* */

    public void disparar() {
        /** dipara un misil. Crea un nuevo misil, se le asigna su posicion en pantalla
         * en funcion de la posicion de la nave aliada y se introduce en el Array de misiles */
        misiles.add(new MISIL(x+32, y+8, velocidad));
    }
    
/** ********************************************************************************************* */

    public void crear_aliena(int num, int v) {
        /** Crea las naves alienA y las introduce en el Array de naves alienA.
         * La posicion x de la nueva nave se pone al final de la pantalla, la posicion y
         * se calcula de forma aleatoria. Se pone un valor de retardo para la aparicion
         * de la nave en pantalla, de manera que hace que las naves aparezcan una detras
         * de otra en lugar de hacerlas todas a la vez. */
        int aleatorio;
        int rt;
        
        Random rand = new Random();
        for(int i = 0; i < num; i++) {
            aleatorio = rand.nextInt(515);
            rt = 40 * i;
            alienA.add(new ALIENA(800,aleatorio,v,rt));
        }
    }
    
/** ********************************************************************************************* */

    public void crear_alienb(int num, int v) {
        /** Crea las naves alienB y las introduce en el Array de naves alienB.
         * La posicion x de la nueva nave se pone al final de la pantalla, la posicion y
         * se calcula de forma aleatoria. Se pone un valor de retardo para la aparicion
         * de la nave en pantalla, de manera que hace que las naves aparezcan una detras
         * de otra en lugar de hacerlas todas a la vez. */
        int aleatorio;
        int rt;
        
        Random rand = new Random();
        for(int i = 0; i < num; i++) {
            aleatorio = rand.nextInt(515);
            rt = ((num * 40) + (40 * i));
            alienB.add(new ALIENB(800,aleatorio,v,rt));
        }
    }
    
/** ********************************************************************************************* */

    public void borrar_arrays() {
        /** borra los objetos de los Arrays de misiles, naves alienA y naves alienB */
        int i;
        ArrayList<ALIENA> ala = get_aliena();
        ArrayList<ALIENB> alb = get_alienb();
        ArrayList<MISIL> ms = get_misiles();
        
        i = 0;
        while (i < ala.size()) {
            ala.remove(i);
        }
        i = 0;
        while (i < alb.size()) {
            alb.remove(i);
        }
        i = 0;
        while (i < ms.size()) {
            ms.remove(i);
        }
    }
}

/** ********************************************************************************************* */
/** ********************************************************************************************* */
