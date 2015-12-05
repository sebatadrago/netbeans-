
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormularioOperaciones extends JFrame {

    private JButton jbtAgregar, jbtActualizar, jbtBorrar, jbtBuscar;
    private JList jlstEstudiante;
    private DefaultListModel defaultListModel;
    private DialogoEstudiante dialogoEstudiante;
    private Lista listaEstudiante;
    private JTextField jtfNombre;
    private Archivo archivo;

    public FormularioOperaciones() {
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(jbtAgregar = new JButton("Agregar"));
        p1.add(jbtActualizar = new JButton("Actualizar"));
        p1.add(jbtBorrar = new JButton("Borrar"));
        p1.add(jbtBuscar = new JButton("Buscar"));
        p1.add(jtfNombre = new JTextField(15));

        this.add(p1, BorderLayout.NORTH);

        defaultListModel = new DefaultListModel();
        listaEstudiante = new Lista();

        archivo = new Archivo("c:\\Estudiante.txt");
        listaEstudiante = archivo.leer();
        poblarJList();

        jlstEstudiante = new JList(defaultListModel);
        jlstEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JScrollPane(jlstEstudiante), BorderLayout.CENTER);

        jlstEstudiante.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int indiceSeleccionado = jlstEstudiante.getSelectedIndex();

                //JOptionPane.showMessageDialog(rootPane, "Indice seleccionado " + indiceSeleccionado);
            }
        });

        jbtAgregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dialogoEstudiante = new DialogoEstudiante(null);

                Estudiante estudiante = dialogoEstudiante.getEstudiante();
                listaEstudiante.insertarAlfinal(estudiante);
                poblarJList();
                archivo.guardar(listaEstudiante);

            }
        });

        jbtActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceSeleccionado = jlstEstudiante.getSelectedIndex();

                if (indiceSeleccionado == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un estudiante", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dialogoEstudiante = new DialogoEstudiante(listaEstudiante.infoEnPosicion(indiceSeleccionado + 1));
                    archivo.guardar(listaEstudiante);
                    poblarJList();
                }
            }
        });

        jbtBorrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int indiceSeleccionado = jlstEstudiante.getSelectedIndex();
                if (indiceSeleccionado == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un estudiante", "Recuerde", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (JOptionPane.showConfirmDialog(rootPane, "Realmente desea borrar el estudiante seleccionado", "Confirmar Borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        listaEstudiante.borrar(indiceSeleccionado + 1);
                        archivo.guardar(listaEstudiante);
                        poblarJList();
                    }
                }
            }
        });

        jbtBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre = jtfNombre.getText().trim();
                if (!nombre.isEmpty()) {
                    Estudiante estudiante = buscar(nombre);
                    if (estudiante != null) {
                        dialogoEstudiante = new DialogoEstudiante(estudiante);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "El estudiante " + nombre + " no se encontro en la lista", "Resultado Búsqueda", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void poblarJList() {
        defaultListModel.removeAllElements();
        for (Iterator iterator = listaEstudiante.iterator(); iterator.hasNext();) {
            Estudiante estudiante = (Estudiante) iterator.next();
            defaultListModel.addElement(estudiante);
        }
    }

    private Estudiante buscar(String nombre) {
        for (Iterator iterator = listaEstudiante.iterator(); iterator.hasNext();) {
            Estudiante estudiante = (Estudiante) iterator.next();
            if (estudiante.getNombre().contains(nombre)) {
                return estudiante;
            }
        }
        return null;
    }
}
