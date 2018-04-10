import naves.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

/** ********************************************************************************************* */
/** ********************************************************************************************* */

public class BATALLA extends JPanel implements ActionListener {
/** Esta clase implementa el funcionamiento principal del juego.
 * Implementa un panel, por herencia de la clase JPanel.
 * Implementa un Listener para controlar las pulsaciones de teclado
 * y un timer para repintar los cambios en las posiciones de las naves y misiles. */
    private Timer timer; /** Timer utilizado para repintar los elementos de pantalla */
    private ALIADO aliado; /** Nave aliada */
    private int NIVEL = 10; /** Nivel de dificultad del juego, indica el numero de naves alienA y AlienB que aparecen, por defecto 10 */
    private int VELOCIDAD = 1; /** indica la velocidad a la que se desplazan las naves, por defecto 1 */
    private Image fondo; /** almacena las imagenes de fondo, menu, partida ganada y partida perdida */
    private boolean jugando = false; /** indica si estamos jugando o no */
    private boolean nueva_partida = true; /** indica si esperamos una nueva partida o no */
    private boolean partida_ganada = true; /** indica si la partida ha sido ganada o no */

/** ********************************************************************************************* */

    public BATALLA() {
        /** Constructor de la clase BATALLA. Inicia el listener, el JPanel, el Timer y crea la nave aliada */
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        aliado = new ALIADO();
        timer = new Timer(1, this);
        timer.start();
    }
    
/** ********************************************************************************************* */

    public void set_jugando(boolean j) {
        /** Actualiza el indicador de si se esta jugando o no 
         * true - esta jugando, false - no esta jugando */
        jugando = j;
    }
    
/** ********************************************************************************************* */

    public boolean get_jugando() {
        /** Devuelve el indicador de si se esta jugando o no */
        return jugando;
    }
    
/** ********************************************************************************************* */

    public void set_nueva_partida(boolean n) {
        /** actualiza el indicador de si se espera nueva partida o no 
         * true espera nueva partida, false no espera nueva partida */
        nueva_partida = n;
    }
    
/** ********************************************************************************************* */

    public boolean get_nueva_partida() {
        /** Devuelve el indicador de si se espera una nueva partida o no */
        return nueva_partida;
    }
    
/** ********************************************************************************************* */

    public void set_partida_ganada(boolean pg) {
        /** Actualiza el indicador de si se ha ganado la partida o no
         * true - se ha ganado la partida, false - no se ha ganado la partida */
        partida_ganada = pg;
    }
    
/** ********************************************************************************************* */

    public boolean get_partida_ganada() {
        /**Devuelve el indicador de si se ha ganado la partida o no */
        return partida_ganada;
    }
    
/** ********************************************************************************************* */
    
    public void set_nivel(int n) {
        /** Actualiza el nivel de dificultad del juego */
        NIVEL = n;
    }
    
/** ********************************************************************************************* */

    public void set_velocidad(int v) {
        /** Actualiza la velocidad de movimiento de las naves y misiles */
        VELOCIDAD = v;
    }
    
/** ********************************************************************************************* */
        
    public void paint(Graphics g) {
        /** Pinta los elementos en pantalla, menu inicial, pantalla de partida ganada,
         * pantalla de partida perdida, fondo del juego, naves alienA, naves alienB, nave aliada y misiles*/
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;        
        /** si se esta jugando dibuja el fondo de juego, nave aliada, misiles, alienA y alienB */
        if (jugando) {
            dibujar_fondo(g);
            dibujar_aliado(g);
            dibujar_misiles(g);
            dibujar_aliena(g);
            dibujar_alienb(g);
        }
        /** si no se esta jugando y se espera una nueva partida, dibuja el menu pinncipal */
        if (!jugando && nueva_partida) {
            dibujar_menu(g);
        }
        /** si no se esta jugando y no se espera una nueva partida y se ha ganado la partida
         * dibuja el fondo de partida ganada */
        if (!jugando && !nueva_partida && partida_ganada) {
            dibujar_fondo_partida_ganada(g);
        }
        /** si no se esta jugando y no se espera una nueva partida y no se ha ganado la partida
         * dibuja el fondo de partida perdida */
        if (!jugando && !nueva_partida && !partida_ganada) {
            dibujar_fondo_partida_perdida(g);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
/** ********************************************************************************************* */

    public void actionPerformed(ActionEvent e) {
        /** actionPerformed se ejecuta cada vez que se produce un evento, ya sea de Timer o pulsacion de tecla 
         * Implementa las acciones principales del juego, mover las naves, disparar misiles, comprobar colisiones,
         * comprobar si se ha llegado al final del juego y se ha ganado o no la partida. */
         
        /** comprueba si se esta jugando o no */ 
        if (jugando) {
            /**Movemos los misiles cuando se produzca un evento. Recorre todo el Array de misiles.
             * Si el misil esta fuera de la pantalla de juego, lo pone como no visible */
            ArrayList<MISIL> ms = aliado.get_misiles();
            for (int i = 0; i < ms.size(); i++) {
                MISIL m = (MISIL) ms.get(i);
                if(m.visible_misil()) {
                    m.mover_misil();
                } else {
                    m.set_visible_misil(false);
                }
            }
                     
            /** movemos el aliado */
            aliado.mover_aliado();

            /** movemos las naves alienA. Recorre todo el Array de naves alienA. Si la nave alienA no es visible
             * (ha sido destruida), la elimina del Array */
            ArrayList<ALIENA> ala = aliado.get_aliena();
            for (int i = 0; i< ala.size(); i++) {
                ALIENA aa = (ALIENA) ala.get(i);
                if(aa.visible_aliena()) {
                    aa.mover_aliena();
                } else {
                    ala.remove(i);
                }
            }

            /** movemos las naves alienB. Recorre todo el Array de naves alienA. Si la nave alienA no es visible
             * (ha sido destruida), la elimina del Array */
            ArrayList<ALIENB> alb = aliado.get_alienb();
            for (int i = 0; i< alb.size(); i++) {
                ALIENB ab = (ALIENB) alb.get(i);
                if(ab.visible_alienb()) {
                    ab.mover_alienb();
                } else {
                    alb.remove(i);
                }
            }
                
            /** Detectamos colisiones de misil con naves alienA. Para ello, por cada misil 
             * comprueba todas la naves del Array alienA en busca de colision. Si hay colision 
             * pone el indicador visible de la nave alienA y misil a falso indicando que no es visible */
            ArrayList<MISIL> ms1 = aliado.get_misiles();
            ArrayList<ALIENA> aa1 = aliado.get_aliena();

            for (int i = 0; i < ms1.size(); i++) {
                MISIL m = (MISIL) ms1.get(i);
            
                for (int j = 0; j < aa1.size(); j++) {
                    ALIENA a = (ALIENA) aa1.get(j);
                    
                    int mx1 = m.get_posx_misil();
                    int my1 = m.get_posy_misil();
                    int mx2 = mx1 + m.get_ancho_misil();
                    int my2 = my1 + m.get_alto_misil();
                            
                    int ax1 = a.get_posx_aliena();
                    int ay1 = a.get_posy_aliena();
                    int ax2 = ax1 + a.get_ancho_aliena();
                    int ay2 = ay1 + a.get_alto_aliena();
                    
                    if (impacto(mx1, my1, mx2, my2, ax1, ay1, ax2, ay2)) {
                        a.set_visible_aliena(false);
                        m.set_visible_misil(false);
                    }
                }
            }


            /** Detectamos colisiones de misil con naves alienB. Para ello, por cada misil 
             * comprueba todas la naves del Array alienB en busca de colision. Si hay colision 
             * pone el idicador visible de la nave alienB y misil a falso indicando que no es visible */
            ArrayList<MISIL> ms2 = aliado.get_misiles();
            ArrayList<ALIENB> ab1 = aliado.get_alienb();

            for (int i = 0; i < ms2.size(); i++) {
                MISIL m = (MISIL) ms2.get(i);
            
                for (int j = 0; j < ab1.size(); j++) {
                    ALIENB b = (ALIENB) ab1.get(j);
                    
                    int mx1 = m.get_posx_misil();
                    int my1 = m.get_posy_misil();
                    int mx2 = mx1 + m.get_ancho_misil();
                    int my2 = my1 + m.get_alto_misil();

                    int ax1 = b.get_posx_alienb();
                    int ay1 = b.get_posy_alienb();
                    int ax2 = ax1 + b.get_ancho_alienb();
                    int ay2 = ay1 + b.get_alto_alienb();
                    
                    if (impacto(mx1, my1, mx2, my2, ax1, ay1, ax2, ay2)) {
                        b.set_visible_alienb(false);
                        m.set_visible_misil(false);
                    }
                }
            }


            if (jugando) {
                /** Detectamos colisiones de nave aliada con naves alienA.
                 * Si detecta una colision pone la nave alienA a no visible,
                 * el indicador de jugando a falso indicando que se ha terminado
                 * la partida, el indicador de partida ganada a falso y nueva partida a falso */
                ArrayList<ALIENA> aa2 = aliado.get_aliena();
                for (int j = 0; j < aa2.size(); j++) {
                    ALIENA a = (ALIENA) aa2.get(j);

                    int mx1 = aliado.get_posx_aliado();
                    int my1 = aliado.get_posy_aliado();
                    int mx2 = mx1 + aliado.get_ancho_aliado();
                    int my2 = my1 + aliado.get_alto_aliado();

                    int ax1 = a.get_posx_aliena();
                    int ay1 = a.get_posy_aliena();
                    int ax2 = ax1 + a.get_ancho_aliena();
                    int ay2 = ay1 + a.get_alto_aliena();
        
                    if (impacto(mx1, my1, mx2, my2, ax1, ay1, ax2, ay2)) {
                        a.set_visible_aliena(false);
                        jugando = false;
                        partida_ganada = false;
                        nueva_partida = false;
                        aliado.borrar_arrays();
                    }
                }
            }

            if (jugando) {
                /** Detectamos colisiones de nave aliada con naves alienB.
                 * Si detecta una colision pone la nave alienB a no visible,
                 * el indicador de jugando a falso indicando que se ha terminado
                 * la partida, el indicador de partida ganada a falso y nueva partida a falso */
                ArrayList<ALIENB> ab2 = aliado.get_alienb();
                for (int j = 0; j < ab2.size(); j++) {
                    ALIENB b = (ALIENB) ab2.get(j);
              
                    int mx1 = aliado.get_posx_aliado();
                    int my1 = aliado.get_posy_aliado();
                    int mx2 = mx1 + aliado.get_ancho_aliado();
                    int my2 = my1 + aliado.get_alto_aliado();
                
                    int ax1 = b.get_posx_alienb();
                    int ay1 = b.get_posy_alienb();
                    int ax2 = ax1 + b.get_ancho_alienb();
                    int ay2 = ay1 + b.get_alto_alienb();

                    if (impacto(mx1, my1, mx2, my2, ax1, ay1, ax2, ay2)) {
                        b.set_visible_alienb(false);
                        jugando = false;
                        partida_ganada = false;
                        nueva_partida = false;
                        aliado.borrar_arrays();
                    }
                }
            }

            /** Limpiamos el array de misiles no validos, misiles que han
             * salido de la pantalla o han hecho un impacto, para ello recorre
             * el Array de misiles y elimina aquellos que no son visibles */
            ArrayList<MISIL> ms3 = aliado.get_misiles();
            int k1 = 0;
            while (k1 < ms3.size()) {
                MISIL m = (MISIL) ms3.get(k1);
                if(m.visible_misil()) {
                    k1++;
                } else {
                    ms3.remove(k1);
                }
            }

            /** Limpiamos el array de alienA no validas, naves alienA que han sido destruidas
             * para ello, recorre el Array de naves alienA y elimina aquellas que no son visibles */
            ArrayList<ALIENA> ala3 = aliado.get_aliena();
            int k2 = 0;
            while (k2 < ala3.size()) {
                ALIENA aa = (ALIENA) ala3.get(k2);
                if(aa.visible_aliena()) {
                    k2++;
                } else {
                    ala.remove(k2);
                }
            }

            /** Limpiamos el array de alienB no validas, naves alienB que han sido destruidas
             * para ello, recorre el Array de naves alienB y elimina aquellas que no son visibles */
            ArrayList<ALIENB> alb3 = aliado.get_alienb();
            int k3 = 0;
            while (k3 < alb3.size()) {
                ALIENB ab = (ALIENB) alb3.get(k3);
                if(ab.visible_alienb()) {
                    k3++;
                } else {
                    alb.remove(k3);
                }
            }

            if (jugando) {
                /** comprobamos el numero de aliena y alienb en juego de manera que si es 0, 
                 * temina la partida, ganando el juego. Actualiza el indicador jugando a falso
                 * indicando que se ha terminado el juego, el indicador partida ganada a true
                 * indicando que se ha ganado la partida y el indicador nueva partida a falso
                 * indicando que no se espera una nueva partida */
                ArrayList<ALIENA> ala4 = aliado.get_aliena();
                int num_aliena = ala4.size();
                ArrayList<ALIENB> alb4 = aliado.get_alienb();
                int num_alienb = alb4.size();
                if (num_aliena == 0 && num_alienb == 0) {
                    aliado.borrar_arrays();
                    jugando = false;
                    partida_ganada = true;
                    nueva_partida = false;
                }
            }
        }
        /** repintamos todos los elementos del juego a sus nuevas posiciones */
        repaint();
    }
    
/** ********************************************************************************************* */

    public boolean impacto (int mpx1, int mpy1, int mpx2, int mpy2, int apx1, int apy1, int apx2, int apy2) {
        /** comprueba si ha habido un impacto o no entre dos elementos.
         * para ello comprueba si los rectangulos de los dos elementos estan solapados o no.
         * Se pasan como parametros los puntos superior izquierdo e inferior derecho de
         * los 2 elementos que queremos comprobar. 
         * (mpx1,mpy1) punto sup izq del primer elemento
         * (mpx2,mpy2) punto inf drch del primer elemnto.
         * (apx1,apy1) punto sup izq del segundo elemnto.
         * (apx2,apy2) punto inf drch del segundo elemento.
         * Devuelve true si los 2 rectangulos se solapan, hay impacto, y false en caso contrario */
        boolean res = false;
        
        if ( (mpx1 <= apx2) && (apx2 <= mpx2) ) {
            if ( (mpy1 <= apy2) && (apy2 <= mpy2) ) {
                res = true;
            }
        }
        
        if ( (mpx1 <= apx1) && (apx1 <= mpx2) ) {
            if ( (mpy1 <= apy2) && (apy2 <= mpy2) ) {
                res = true;
            }
        }
        
        if ( (mpx1 <= apx2) && (apx2 <= mpx2) ) {
            if ( (mpy1 <= apy1) && (apy1 <= mpy2) ) {
                res = true;
            }
        }

        if ( (apx1 <= mpx1) && (mpx1 <= apx2) ) {
            if ( (apy1 <= mpy1) && (mpy1 <= apy2) ) {
                res = true;
            }
        }
        
        if ( (apx1 <= mpx2) && (mpx2 <= apx2) ) {
            if ( (apy1 <= mpy2) && (mpy2 <= apy2) ) {
                res = true;
            }
        }
        
        return res;
        
    }
    
/** ********************************************************************************************* */

    public void dibujar_menu(Graphics g){
        /** dibujamos el menu */
        Graphics2D g2d = (Graphics2D)g;        

        ImageIcon ii = new ImageIcon(this.getClass().getResource("./images/menu.jpg"));
        fondo = ii.getImage();  
        g2d.drawImage(fondo, 0, 0, this);          
    }

/** ********************************************************************************************* */

    public void dibujar_fondo(Graphics g){
        /** dibujamos el fondo de la pantalla de juego */
        Graphics2D g2d = (Graphics2D)g;        
    
        ImageIcon ii = new ImageIcon(this.getClass().getResource("./images/fondo.jpg"));
        fondo = ii.getImage();  
        g2d.drawImage(fondo, 0, 0, this);
    }

/** ********************************************************************************************* */

    public void dibujar_aliado(Graphics g){
        /** dibuja el aliado en las coordenas (x,y) en las que se encuentra */
        Graphics2D g2d = (Graphics2D)g;        
        g2d.drawImage(aliado.get_image_aliado(), aliado.get_posx_aliado(), aliado.get_posy_aliado(), this);
    }
    
/** ********************************************************************************************* */

    public void dibujar_misiles(Graphics g){
        /** dibuja los misiles disparados en las posiciones (x,y) en las que se encuentran */
        Graphics2D g2d = (Graphics2D)g;        
        ArrayList<MISIL> ms =aliado.get_misiles();
        for (int i = 0; i < ms.size(); i++) {
            MISIL m = (MISIL) ms.get(i);
            g2d.drawImage(m.get_image_misil(), m.get_posx_misil(), m.get_posy_misil(), this);
        }
    }
    
/** ********************************************************************************************* */

    public void dibujar_aliena(Graphics g){
        /** dibuja los alienA en las posiciones (x,y) en las que se encuentran */
        Graphics2D g2d = (Graphics2D)g;        
        ArrayList<ALIENA> ala = aliado.get_aliena();
        for (int i = 0; i < ala.size(); i++) {
            ALIENA aa = (ALIENA) ala.get(i);
            g2d.drawImage(aa.get_image_aliena(), aa.get_posx_aliena(), aa.get_posy_aliena(), this);
        }
    }
    
/** ********************************************************************************************* */

    public void dibujar_alienb(Graphics g){
        /** dibuja los alienB en las posiciones (x,y) en las que se encuentran */
        Graphics2D g2d = (Graphics2D)g;        
        ArrayList<ALIENB> alb = aliado.get_alienb();
        for (int i = 0; i < alb.size(); i++) {
            ALIENB ab = (ALIENB) alb.get(i);
            g2d.drawImage(ab.get_image_alienb(), ab.get_posx_alienb(), ab.get_posy_alienb(), this);
        }
    }

/** ********************************************************************************************* */

    public void dibujar_fondo_partida_ganada(Graphics g){
        /** dibuja el fondo de partida ganada al terminar y ganar la partida */
        Graphics2D g2d = (Graphics2D)g;        
        ImageIcon ii = new ImageIcon(this.getClass().getResource("./images/partida_ganada.jpg"));
        fondo = ii.getImage();  
        g2d.drawImage(fondo, 0, 0, this);
     }
    
/** ********************************************************************************************* */

    public void dibujar_fondo_partida_perdida(Graphics g){
        /** dibuja el fondo de partida perdida al terminar y perder la partida */
        Graphics2D g2d = (Graphics2D)g;        
        ImageIcon ii = new ImageIcon(this.getClass().getResource("./images/partida_perdida.jpg"));
        fondo = ii.getImage();  
        g2d.drawImage(fondo, 0, 0, this);
    }

/** ********************************************************************************************* */
    
    public void iniciar_parametros() {
        /** Inicia los parametros del juego.
         * Borra los Arrays de misiles, naves alienA y naves alienB.
         * Inicia el nivel del juego al nivel de dificultad seleccionado.
         * Inicia la velocidad del juego a la velocidad seleccionada
         * en funcion del nivel de dificultad.
         * Inicia la posicion inicial (x,y) de la nave aliada.
         * Crea los Arrays alienA y alienB con el numero de naves especificadas
         * por el nivel de dificultad seleccionado en el menu principal */
        aliado.borrar_arrays();
        aliado.set_nivel(NIVEL);
        aliado.set_velocidad(VELOCIDAD);
        aliado.set_posx(20);
        aliado.set_posy(250);
        aliado.crear_aliena(NIVEL, VELOCIDAD);
        aliado.crear_alienb(NIVEL, VELOCIDAD);
    }
    
/** ********************************************************************************************* */

    private class TAdapter extends KeyAdapter {
        /** Implementa una clase privada para capturar las pulsaciones en teclado
         * y la liberacion de la tecla pulsada (tecla soltada). */
        public void keyReleased(KeyEvent e) {
            aliado.tecla_soltada(e);
        }
        
/** ********************************************************************************************* */

        public void keyPressed(KeyEvent e) {
            aliado.tecla_presionada(e);
        }
    }
}

/** ********************************************************************************************* */
/** ********************************************************************************************* */
    