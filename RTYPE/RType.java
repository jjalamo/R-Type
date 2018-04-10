import naves.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** ********************************************************************************************* */
/** ********************************************************************************************* */

public class RType extends JFrame {
/** Clase principal del juego con herencia a JFrame */

    /** definicion de elementos de la barra de menu */
    JMenuBar barra_menu;
    JMenuItem elemento_facil;
    JMenuItem elemento_normal;
    JMenuItem elemento_complicado;
    JMenuItem elemento_imposible;
    JMenuItem elemento_jugar;
    JMenuItem elemento_inicio;
    JMenuItem elemento_salir;
    BATALLA batalla; /** desarrolla el funcionamiento del juego */ 
    
/** ********************************************************************************* */
    
    public RType() { 
        /** Constructor de la clase RType */
        /** Crea la barra de menu */
        /** Definimos para cada elemento un Listener que controla las acciones de cada elemento de menu */
        barra_menu = new JMenuBar();
        setJMenuBar(barra_menu);
        
        elemento_facil = new JMenuItem("FACIL");
        barra_menu.add(elemento_facil);
        elemento_facil.addActionListener(new FacilActionListener());
        
        elemento_normal = new JMenuItem("NORMAL");
        barra_menu.add(elemento_normal);
        elemento_normal.addActionListener(new NormalActionListener());
        
        elemento_complicado = new JMenuItem("COMPLICADO");
        barra_menu.add(elemento_complicado);
        elemento_complicado.addActionListener(new ComplicadoActionListener());
        
        elemento_imposible = new JMenuItem("IMPOSIBLE");
        barra_menu.add(elemento_imposible);
        elemento_imposible.addActionListener(new ImposibleActionListener());
        
        elemento_jugar = new JMenuItem("JUGAR");
        barra_menu.add(elemento_jugar);
        elemento_jugar.addActionListener(new JugarActionListener());
        
        elemento_inicio = new JMenuItem("INICIO");
        barra_menu.add(elemento_inicio);
        elemento_inicio.addActionListener(new InicioActionListener());
        
        elemento_salir = new JMenuItem("SALIR");
        barra_menu.add(elemento_salir);
        elemento_salir.addActionListener(new SalirActionListener());
        
        /** Crea la clase BATALLA, donde se desarrolla el juego y lo lanza */
        batalla = new BATALLA();
        add(batalla);
        
        /** Definimos el Frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("R-TYPE - BLUEJ Version");
        setResizable(false);
        setVisible(true);
    }

/** ********************************************************************************************* */

    public static void main () {
        new RType();
    }

/** ********************************************************************************************* */

    private class FacilActionListener implements ActionListener {
    /** Listener de la opcion Facil del menu */
    /** pone el nivel del juego a 10 (numero de aliens) y la velocidad a 1 */
        public void actionPerformed(ActionEvent e) {
            batalla.set_nivel(10);
            batalla.set_velocidad(1);
        }
    }

/** ********************************************************************************************* */

    private class NormalActionListener implements ActionListener {
    /** Listener de la opcion Normal del menu */
    /** pone el nivel del juego a 15 (numero de aliens) y la velocidad a 2 */
        public void actionPerformed(ActionEvent e) {
            batalla.set_nivel(15);
            batalla.set_velocidad(2);
        }
    }

/** ********************************************************************************************* */

    private class ComplicadoActionListener implements ActionListener {
    /** Listener de la opcion Complicado del menu */
    /** pone el nivel del juego a 20 (numero de aliens) y la velocidad a 3 */
        public void actionPerformed(ActionEvent e) {
            batalla.set_nivel(20);
            batalla.set_velocidad(3);
        }
    }

/** ********************************************************************************************* */

    private class ImposibleActionListener implements ActionListener {
    /** Listener de la opcion Imposible del menu */
    /** pone el nivel del juego a 30 (numero de aliens) y la velocidad a 4 */
        public void actionPerformed(ActionEvent e) {
            batalla.set_nivel(30);
            batalla.set_velocidad(4);
        }
    }

/** ********************************************************************************************* */

    private class JugarActionListener implements ActionListener {
    /** Listener de la opcion Jugar del menu */
    /** Si no estamos jugando y esta listo para una nueva partida, pone las banderas de control */
    /** del juego a verdadero, inicia los parametros del juego e inicia la partida */
        public void actionPerformed(ActionEvent e) {
            if(!batalla.get_jugando() && batalla.get_nueva_partida()) {
                batalla.set_nueva_partida(true);
                batalla.set_partida_ganada(true);
                batalla.iniciar_parametros();
                batalla.set_jugando(true);
            }
        }
    }

/** ********************************************************************************************* */

    private class InicioActionListener implements ActionListener {
    /** Listener de la opcion Inicio del menu */
    /** Si no estamos jugando, inicia los parametros nivel y velocidad a los valores */
    /** del modo facil (por defecto) y activa la bandera de nueva partida */
        public void actionPerformed(ActionEvent e) {
            if(!batalla.get_jugando()) {  
                batalla.set_nivel(10);
                batalla.set_velocidad(1);
                batalla.set_nueva_partida(true);
            }
        }
    }

/** ********************************************************************************************* */

    private class SalirActionListener implements ActionListener {
    /** Listener de la opcion Salir del menu. Termina la ejecucion del programa. */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
/** ********************************************************************************************* */
/** ********************************************************************************************* */

