import Service.Servicio;
import Utils.UtilsSistema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UtilsSistema us = new UtilsSistema();
        Servicio s = new Servicio();
        opciones();
        if (us.isLinux()){
            s.ejecutarLinux();
        }
        if (us.isWindows()){
            s.ejecutarWindows();
        }
}
    private static void opciones () {
        System.out.println("Por favor introduzca el número del comando a ejecutar:");
        System.out.println("1 \uF0E0 Ping");
        System.out.println("2 \uF0E0 Listado de un archivo");
        System.out.println("3 \uF0E0 Lista de procesos activos y/o cerrar uno por PID");
        System.out.println("4 \uF0E0 Abrir una URL");
        System.out.println("Su sistema operativo es \uF0E0 "+ System.getProperty("os.name"));
    }
}