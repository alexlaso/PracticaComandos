package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Linux {
    Scanner sc = new Scanner(System.in);
    ProcessBuilder pb = new ProcessBuilder();

    int respuesta;
    public void comandosLinux(){
        respuesta = sc.nextInt();
        eleccionLinux(respuesta);
    }

    public void eleccionLinux ( int respuesta){
        switch (respuesta) {
            case 1: PingLinux();
            case 2: ListadoLinux();
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

    public void ListadoLinux(){

    }
}
