import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class H2 extends JFrame{

    public H2 () {
        setTitle ("Conga Time");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(498, 438);

        JLabel gif = new JLabel(new ImageIcon("Imagen y Sonido/DDitto.gif"));

        add(gif);

        Thread audioThread = new Thread (() -> Conga("Imagen y Sonido/Conga.wav"));
        audioThread.start();

        setVisible(true);
    }

    private void Conga(String a){
        try{

        Clip clip = AudioSystem.getClip();

        AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(a));

        clip.open(inputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("Error al reproducir el audio: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new H2());
    }

}
