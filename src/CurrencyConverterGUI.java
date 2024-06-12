import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterGUI extends JFrame {
    private JPanel panelMonedaOrigen;
    private JPanel panelMonedaDestino;
    private JTextField txtCantidad;
    private JLabel lblResultado;
    private JLabel lblMensaje;

    private static final String[] MONEDAS = {
            "ARS - Peso argentino",
            "BOB - Boliviano boliviano",
            "BRL - Real brasileño",
            "CLP - Peso chileno",
            "COP - Peso colombiano",
            "USD - Dólar estadounidense"
    };

    public CurrencyConverterGUI() {
        setTitle("Challenge Conversor");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear panel principal sin layout manager
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cambiar el color de fondo del panel principal
                setBackground(new Color(50, 50, 50)); // Gris oscuro
            }
        };
        mainPanel.setLayout(null);

        // Agregar título
        JLabel titleLabel = new JLabel("Challenge Conversor");
        titleLabel.setBounds(200, 10, 200, 30);
        titleLabel.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
        mainPanel.add(titleLabel);

        // Columna de monedas de origen
        panelMonedaOrigen = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMonedaOrigen.setBorder(BorderFactory.createTitledBorder("Divisa origen"));
        panelMonedaOrigen.setBounds(50, 60, 200, 200);
        panelMonedaOrigen.setBackground(new Color(100, 100, 100)); // Gris medio
        TitledBorder titledBorderOrigen = BorderFactory.createTitledBorder("Divisa origen");
        titledBorderOrigen.setTitleColor(Color.WHITE); // Texto en blanco
        panelMonedaOrigen.setBorder(titledBorderOrigen);
        mainPanel.add(panelMonedaOrigen);

        // Columna de monedas de destino
        panelMonedaDestino = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMonedaDestino.setBorder(BorderFactory.createTitledBorder("Divisa destino"));
        panelMonedaDestino.setBounds(300, 60, 200, 200);
        panelMonedaDestino.setBackground(new Color(100, 100, 100)); // Gris medio
        TitledBorder titledBorderDestino = BorderFactory.createTitledBorder("Divisa destino");
        titledBorderDestino.setTitleColor(Color.WHITE); // Texto en blanco
        panelMonedaDestino.setBorder(titledBorderDestino);
        mainPanel.add(panelMonedaDestino);

        // Cantidad a convertir
        JLabel lblCantidad = new JLabel("Cantidad a convertir:");
        lblCantidad.setBounds(50, 270, 200, 30);
        lblCantidad.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
        txtCantidad = new JTextField(10);
        txtCantidad.setBounds(50, 310, 200, 30);
        mainPanel.add(lblCantidad);
        mainPanel.add(txtCantidad);

        // Botón Convertir
        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setBounds(50, 370, 450, 35);
        mainPanel.add(btnConvertir);

        // Mensaje "Resultado"
        lblMensaje = new JLabel("Resultado: ");
        lblMensaje.setBounds(300, 270, 200, 30);
        lblMensaje.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
        mainPanel.add(lblMensaje);

        // Resultado de la conversion
        lblResultado = new JLabel("0.00");
        lblResultado.setBounds(300, 310, 200, 30);
        lblResultado.setForeground(Color.BLACK); // Cambiar el color del texto a negro
        mainPanel.add(lblResultado);

        // Panel blanco para el resultado
        JPanel panelResultado = new JPanel();
        panelResultado.setBackground(Color.WHITE);
        panelResultado.setBounds(298, 310, 200, 30);
        mainPanel.add(panelResultado);

        // Establecer el panel principal como contenido del marco
        setContentPane(mainPanel);

        // Acción del botón
        btnConvertir.addActionListener(e -> convertirMoneda());

        // Inicializar componentes
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        for (String moneda : MONEDAS) {
            JRadioButton btnMonedaOrigen = new JRadioButton(moneda);
            btnMonedaOrigen.setBounds(10, 30, 80, 20);
            panelMonedaOrigen.add(btnMonedaOrigen);
            new ButtonGroup().add(btnMonedaOrigen);

            JRadioButton btnMonedaDestino = new JRadioButton(moneda);
            btnMonedaDestino.setBounds(10, 30, 80, 20);
            panelMonedaDestino.add(btnMonedaDestino);
            new ButtonGroup().add(btnMonedaDestino);
        }
    }

    private void convertirMoneda() {
        String monedaOrigen = obtenerMonedaSeleccionada(panelMonedaOrigen);
        String monedaDestino = obtenerMonedaSeleccionada(panelMonedaDestino);
        double cantidad = 0;
        try {
            cantidad = Double.parseDouble(txtCantidad.getText());
        } catch (NumberFormatException e) {
            lblResultado.setText("Por favor, ingrese una cantidad válida.");
            return;
        }

        // Obtener la tasa de cambio utilizando el API de ExchangeRate-API
        CurrencyConverter converter = new CurrencyConverter();
        double tasaCambio = converter.obtenerTasaDeCambio(monedaOrigen, monedaDestino);

        // Verificar si se obtuvo la tasa de cambio correctamente
        if (tasaCambio != -1) {
            // Realizar la conversión
            double cantidadConvertida = cantidad * tasaCambio;
            // Mostrar el resultado
            lblResultado.setText(cantidad + " " + monedaOrigen + " es igual a " + cantidadConvertida + " " + monedaDestino);
        } else {
            // Mensaje de error si no se pudo obtener la tasa de cambio
            lblResultado.setText("No se pudo obtener la tasa de cambio.");
        }
    }

    private String obtenerMonedaSeleccionada(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JRadioButton && ((JRadioButton) comp).isSelected()) {
                return ((JRadioButton) comp).getText().substring(0, 3);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverterGUI().setVisible(true);
        });
    }
}

