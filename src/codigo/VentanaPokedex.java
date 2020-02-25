package codigo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Alberto Sanz
 */
public class VentanaPokedex extends javax.swing.JFrame {

    BufferedImage buffer1 = null;
    Image imagen1 = null;
    int contador = 0;

    Graphics2D g2, jpanelGraphics;

    //Efectuamos la conexion con la base de datos en este caso estando en la maquina virtual
    Statement estado;
    ResultSet resultadoConsulta;
    Connection conexion;

    //Estructura para guardar todo el contenido de la base de datos de golpe
    HashMap<String, Pokemon> listaPokemon = new HashMap();

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) imagenPokemon.getGraphics();
        g2.drawImage(buffer1, 0, 0,
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight(), null);
    }

    /**
     * Creates new form VentanaPokedex
     */
    public VentanaPokedex() {
        initComponents();

        Color bgColor = Color.GREEN;
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(bgColor));
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        descripcion.putClientProperty("Nimbus.Overrides", defaults);
        descripcion.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        descripcion.setBackground(bgColor);

        try {
            imagen1 = ImageIO.read(getClass()
                    .getResource("/imagenes/black-white.png"));
        } catch (IOException ex) {
        }

        buffer1 = (BufferedImage) imagenPokemon.createImage(
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight());

        g2 = buffer1.createGraphics();

        g2.setColor(Color.black);
        g2.fillRect(0, 0, imagenPokemon.getWidth(), imagenPokemon.getHeight());

        jpanelGraphics = (Graphics2D) imagenPokemon.getGraphics();

        dibujaElPokemonQueEstaEnLaPosicion(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager
                    .getConnection("jdbc:mysql://192.168.192.132/pokedex",
                            "root",
                            "");
            estado = conexion.createStatement();
            resultadoConsulta = estado.executeQuery("Select * From pokemon");

            //Recorremos el array del resultado uno a uno 
            //para ir cargandolo en el HashMap
            while (resultadoConsulta.next()) {
                Pokemon p = new Pokemon();
                p.nombre = resultadoConsulta.getString("nombre");
                p.altura = resultadoConsulta.getString("altura");
                p.descripcion = resultadoConsulta.getString("descripcion");
                p.peso = resultadoConsulta.getString("peso");
                p.id = resultadoConsulta.getString("id");
                listaPokemon.put(resultadoConsulta.getString("id"), p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Hay un error");
        }
    }

    private void primeraPantalla() {
        //Título e icono
        setTitle("Pokedex");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pokemoniconpng.png")).getImage());

        try {
            imagen1 = ImageIO.read(getClass().getResource("/imagenes/black-white.png"));
        } catch (IOException ex) {

        }

        //Ponemos logo Pokemon
        ImageIcon miImagen = new ImageIcon((new ImageIcon(getClass().getResource("/imagenes/pokemon.png"))
                .getImage()
                .getScaledInstance(346, 71, Image.SCALE_SMOOTH)));

        pokedex.setIcon(miImagen);

        ImageIcon miImagen2 = new ImageIcon((new ImageIcon(getClass().getResource("/imagenes/pkdx.png"))
                .getImage()
                .getScaledInstance(346, 71, Image.SCALE_SMOOTH)));
    }

    private void dibujaElPokemonQueEstaEnLaPosicion(int posicion) {
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer1.getGraphics();
        g2.setColor(Color.black);
        g2.fillRect(0, 0, //pinta el fondo del jpanel negro
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight());
        g2.drawImage(imagen1,
                0, //posicion X inicial dentro del jpanel 
                0, // posicion Y inicial dentro del jpanel
                imagenPokemon.getWidth(), //ancho del jpanel
                imagenPokemon.getHeight(), //alto del jpanel
                columna * 96, //posicion inicial X dentro de la imagen de todos los pokemon
                fila * 96, //posicion inicial Y dentro de la imagen de todos los pokemon
                columna * 96 + 96, //posicion final X
                fila * 96 + 96, //posicion final Y
                null //si no lo pones no va
        );
        repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descripcion = new javax.swing.JTextPane();
        imagenPokemon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        peso = new javax.swing.JLabel();
        altura = new javax.swing.JLabel();
        izq = new javax.swing.JButton();
        der = new javax.swing.JButton();
        nombrePokemon = new javax.swing.JLabel();
        pokedex = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        descripcion.setEditable(false);
        getContentPane().add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 260, 120));

        javax.swing.GroupLayout imagenPokemonLayout = new javax.swing.GroupLayout(imagenPokemon);
        imagenPokemon.setLayout(imagenPokemonLayout);
        imagenPokemonLayout.setHorizontalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        imagenPokemonLayout.setVerticalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(imagenPokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 270, 180));

        jLabel1.setText("ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 80, 50, 40));
        getContentPane().add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 70, 40));
        getContentPane().add(peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 480, 100, 50));
        getContentPane().add(altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 110, 50));

        izq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izqActionPerformed(evt);
            }
        });
        getContentPane().add(izq, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 50, 20));

        der.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derActionPerformed(evt);
            }
        });
        getContentPane().add(der, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 50, 20));

        nombrePokemon.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(nombrePokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 120, 60));

        pokedex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pokedex.jpg"))); // NOI18N
        pokedex.setText("jLabel1");
        getContentPane().add(pokedex, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void izqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqActionPerformed
        Pokemon p = listaPokemon.get(String.valueOf(contador - 1));
        if (p != null) {
            nombrePokemon.setText(p.nombre);
            descripcion.setText(p.descripcion);
            altura.setText(p.altura);
            peso.setText(p.peso);
            id.setText(p.id);
        } else {
            nombrePokemon.setText("NO HAY DATOS");
        }
        contador--;
        if (contador <= 0) {
            contador = 1;
        }
        dibujaElPokemonQueEstaEnLaPosicion(contador - 1);
    }//GEN-LAST:event_izqActionPerformed

    private void derActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derActionPerformed
        Pokemon p = listaPokemon.get(String.valueOf(contador + 1));
        if (p != null) {
            nombrePokemon.setText(p.nombre);
            descripcion.setText(p.descripcion);
            altura.setText(p.altura);
            peso.setText(p.peso);
            id.setText(p.id);
        } else {
            nombrePokemon.setText("NO HAY DATOS");
        }
        contador++;
        if (contador >= 649) {
            contador = 649;
        }
        dibujaElPokemonQueEstaEnLaPosicion(contador - 1);
    }//GEN-LAST:event_derActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel altura;
    private javax.swing.JButton der;
    private javax.swing.JTextPane descripcion;
    private javax.swing.JLabel id;
    private javax.swing.JPanel imagenPokemon;
    private javax.swing.JButton izq;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nombrePokemon;
    private javax.swing.JLabel peso;
    private javax.swing.JLabel pokedex;
    // End of variables declaration//GEN-END:variables
}
