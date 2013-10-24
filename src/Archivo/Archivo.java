package Archivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {

    private File archivo;
    private FileWriter fw;

    public Archivo() {
        archivo = new File("/e:/prueba.txt");//"/home/nico/prueba.txt"
        try {
            fw = new FileWriter(archivo,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirEnArchivo(String mensaje) {
        try {
            fw.append(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarFW() {
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
