package Service;

public class Servicio {
    public void ejecutarLinux(){
        Linux linux = new Linux();
        linux.comandosLinux();
    }

    public void ejecutarWindows(){
        Windows w = new Windows();
        w.comandosWindows();
    }
}

