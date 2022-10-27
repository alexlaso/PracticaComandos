package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Windows {
    Scanner sc = new Scanner(System.in);
    ProcessBuilder pb = new ProcessBuilder();
    int respuesta;
    String textoAqui;

    public void comandosWindows() {
        respuesta = sc.nextInt();
        eleccionWindows(respuesta);
    }

    public void eleccionWindows (int respuesta){
        switch (respuesta) {
            case 1: PingWindows();
            break;
            case 2: lsWindows();
            break;
            case 3: procesosWindows();
            break;
            case 4: navegadorWindows();
            break;
            default:
                System.out.println("El número introducido no es ninguna de las opciones");
                eleccionWindows(respuesta= sc.nextInt());
                break;
        }
    }
    public void PingWindows () {
        System.out.println("Introduce dirección para realizar el ping");
        String direccion = sc.next();
        pb.command("powershell.exe","/c", "ping -n 3 "+direccion);
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

    public void lsWindows(){
        System.out.println("Introduce la ruta completa del directorio a listar");
        String ruta= sc.next();
        pb.command("powershell.exe", "/c", "ls "+ruta);
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

    public void procesosWindows(){
        ProcessBuilder listaProcesos = new ProcessBuilder();
        listaProcesos.command("powershell.exe", "/c", "Get-Process");
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
        pb.command("powershell.exe", "/c", "Stop-Process -Id "+id);
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

    public void navegadorWindows(){
        System.out.println("Introduce el nombre de la página a abrir (Ejemplo www.google.es)");
        textoAqui = sc.next();
        pb.command("powershell.exe", "/c","start chrome "+textoAqui);
        try{
            Process proceso = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
