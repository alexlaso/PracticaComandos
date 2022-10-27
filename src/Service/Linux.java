package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Linux {
    Scanner sc = new Scanner(System.in);
    ProcessBuilder pb = new ProcessBuilder();

    int respuesta;
    String textoAqui;
    public void comandosLinux(){
        respuesta = sc.nextInt();
        eleccionLinux(respuesta);
    }

    public void eleccionLinux ( int respuesta){
        switch (respuesta) {
            case 1: PingLinux();
            break;
            case 2: lsLinux();
            break;
            case 3: procesosLinux();
            break;
            case 4: navegadorLinux();
            break;
            default:
                System.out.println("El número introducido no es ninguna de las opciones");
                eleccionLinux(respuesta= sc.nextInt());
            break;
        }
    }
    public void PingLinux () {
        System.out.println("Introduce dirección para realizar el ping");
        String direccion = sc.next();
        pb.command("bash","-c", "ping -c 3 "+direccion);
        try {
            Process proceso = pb.start();
            BufferedReader processOnGoing = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line;
            while ((line = processOnGoing.readLine()) != null){
                System.out.println(line);
            }
            int exitCode = proceso.waitFor();
            System.out.println("\n\tCodigo de error: " + exitCode);

            System.out.println("Error: " +exitCode);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);

        }
    }

    public void lsLinux(){
        System.out.println("Introduce el directorio a listar o introduce la ruta completa");
        String ruta= sc.next();
        pb.command("bash", "-c", "ls "+ruta);
        try{
            Process proceso = pb.start();
            BufferedReader processOnGoing = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line;
            while ((line = processOnGoing.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = proceso.waitFor();
            System.out.println("Exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void procesosLinux(){
        ProcessBuilder listaProcesos = new ProcessBuilder();
        listaProcesos.command("bash", "-c", "ps -j");
        try {
            Process procesoLista = listaProcesos.start();
            BufferedReader readerProcesos = new BufferedReader(new InputStreamReader(procesoLista.getInputStream()));
            String line;
            while ((line = readerProcesos.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("¿Le gustaría cerrar un proceso?");
            System.out.println("Y \uF0E0 Sí");
            System.out.println("N \uF0E0 No");
            textoAqui = sc.next();
            switch(textoAqui){
                case "Y": cerrarProceso();
                case "N": System.exit(0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarProceso(){
        System.out.println("Introduce el ID del proceso a cerrar");
        int id = sc.nextInt();
        pb.command("bash", "-c", "kill -9 "+id);
        try {
            Process proceso = pb.start();
            BufferedReader processOnGoing = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line;
            while ((line = processOnGoing.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Proceso cerrado correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void navegadorLinux(){
        System.out.println("Introduce el nombre de la página a abrir (Ejemplo www.google.es)");
        textoAqui = sc.next();
        pb.command("bash", "-c","gio open "+textoAqui);
        try{
            Process proceso = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
