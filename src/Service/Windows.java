package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Windows {
    Scanner sc = new Scanner(System.in);
    ProcessBuilder pb = new ProcessBuilder();
    int respuesta;

    public void comandosWindows() {
        respuesta = sc.nextInt();
        eleccionWindows(respuesta);
    }

    public void eleccionWindows ( int respuesta){
        switch (respuesta) {
            case 1: PingWindows();
           // case 2: ListadoLinux();
        }
    }
    public void PingWindows () {
        System.out.println("Introduce dirección para realizar el ping");
        String direccion = sc.next();
        pb.command("cmd.exe","/c", "ping -n 3 "+direccion);
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
}
