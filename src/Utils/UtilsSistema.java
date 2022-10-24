package Utils;

public class UtilsSistema {
String os = System.getProperty("os.name").toLowerCase();

    public boolean isLinux() {
        return os.contains("linux");
    }

    public boolean isWindows() {
        return os.contains("win");
    }
}
