import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class guardar_como extends JFrame implements ActionListener {

    private JTextField nombreArchivo;
    private JTextArea contenidoArchivo;

    public guardar_como() {
        super("Guardar como");

        // Configuración de la interfaz gráfica
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel etiquetaNombreArchivo = new JLabel("Nombre del archivo:");
        nombreArchivo = new JTextField(20);

        JLabel etiquetaContenidoArchivo = new JLabel("Contenido del archivo:");
        contenidoArchivo = new JTextArea(10, 20);

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);

        panel.add(etiquetaNombreArchivo, BorderLayout.NORTH);
        panel.add(nombreArchivo, BorderLayout.CENTER);
        panel.add(etiquetaContenidoArchivo, BorderLayout.SOUTH);
        panel.add(new JScrollPane(contenidoArchivo), BorderLayout.CENTER);
        panel.add(botonGuardar, BorderLayout.SOUTH);

        add(panel);

        // Configuración de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals("Guardar")) {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.setFileFilter(new FileNameExtensionFilter("OLC files (*.olc)", "olc"));
            int opcion = selectorArchivos.showSaveDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                String nombreArchivo = selectorArchivos.getSelectedFile().getAbsolutePath();
                if (!nombreArchivo.toLowerCase().endsWith(".olc")) {
                    nombreArchivo += ".olc";
                }
                String contenidoArchivo = this.contenidoArchivo.getText();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
                    writer.write(contenidoArchivo);
                    writer.close();
                    JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new guardar_como();
    }

}



