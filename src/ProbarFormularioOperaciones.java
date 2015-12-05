
import javax.swing.JFrame;

public class ProbarFormularioOperaciones {

    public static void main(String[] args) {
        JFrame formularioOperaciones = new FormularioOperaciones();
        
        formularioOperaciones.setVisible(true);
        formularioOperaciones.setSize(600, 500);
        formularioOperaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formularioOperaciones.setTitle("Operaciones con Lista");
    }
    
}
