
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoEstudiante extends JDialog {

    private JTextField jtfNombre, jtfApellido, jtfPromedio;
    private JButton jbtAceptar, jbtCancelar;
    private Estudiante estudiante;

    public DialogoEstudiante(Estudiante e) {
        this.estudiante = e;

        JPanel p1 = new JPanel(new GridLayout(3, 2));

        p1.add(new JLabel("Nombre"));
        p1.add(jtfNombre = new JTextField(15));

        p1.add(new JLabel("Apellido"));
        p1.add(jtfApellido = new JTextField(15));

        p1.add(new JLabel("Promedio"));
        p1.add(jtfPromedio = new JTextField(6));

        this.add(p1, BorderLayout.CENTER);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(jbtAceptar = new JButton("Aceptar"));
        p2.add(jbtCancelar = new JButton("Cancelar"));

        add(p2, BorderLayout.SOUTH);
        setTitle("Estudiante");
        setModal(true);

        if (estudiante != null) {
            // Vamos a actualizar
            jtfNombre.setText(estudiante.getNombre());
            jtfApellido.setText(estudiante.getApellido());
            jtfPromedio.setText(Double.toString(estudiante.getPromedio()));
        }

        jbtAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre = jtfNombre.getText();
                String apellido = jtfApellido.getText();

                try {
                    double promedio = Double.parseDouble(jtfPromedio.getText());
                    if (promedio >= 0 && promedio <= 5) {
                        if (estudiante == null) {
                            estudiante = new Estudiante(nombre, apellido, promedio);
                        } else {
                            estudiante.setNombre(nombre);
                            estudiante.setApellido(apellido);
                            estudiante.setPromedio(promedio);
                        }
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "promedio debe estar entre 0 y 5", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Promedio inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        jbtCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });

        setSize(350, 200);
        setLocationRelativeTo(rootPane);
        setVisible(true);
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
}
