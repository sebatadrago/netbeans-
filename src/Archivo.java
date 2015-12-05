import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Archivo {

    private final String camino;

    public Archivo(String camino) {
        this.camino = camino;
    }//ejemplo

    public void guardar(Lista listaEstudiante) {
        try {
            FileWriter fileWriter = new FileWriter(camino, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Iterator iterator = listaEstudiante.iterator(); iterator.hasNext();) {
                Estudiante estudiante = (Estudiante) iterator.next();
                printWriter.println(estudiante);
            }

            printWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public Lista leer() {
        Lista listaEstudiante = new Lista();

        FileReader fileReader;
        try {

            File file = new File(camino);

            if (file.exists()) {
                fileReader = new FileReader(file);

                BufferedReader bufferReader = new BufferedReader(fileReader);
                String linea;

                while ((linea = bufferReader.readLine()) != null) {
                    String[] campoEstudiante = linea.split(" ");
                    Estudiante estudiante = new Estudiante(campoEstudiante[0], campoEstudiante[1], Double.parseDouble(campoEstudiante[2]));
                    listaEstudiante.insertarAlfinal(estudiante);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return listaEstudiante;
    }

}
