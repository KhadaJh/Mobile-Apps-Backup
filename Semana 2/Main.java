import javax.swing.*;

class Animal {
    String moverse() {
        return "El animal se mueve.";
    }
}

class Perro extends Animal {
    String moverse() {
        return "El perro corre.";
    }
}

class Pez extends Animal {
    String moverse() {
        return "El pez nada.";
    }
}

class redundant extends Animal {
    String moverse() {
        return super.moverse();
    }
}

public class Main extends JFrame {
    JLabel PerroPrim;
    JLabel PezPrim;
    JLabel AnimalPrim;
    
    public Main() {
        setTitle("Su refrigerador corre?");
        setSize(175, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PerroPrim = new JLabel("1");
        PezPrim = new JLabel("2");
        AnimalPrim = new JLabel("3");

        getContentPane().setLayout(new java.awt.FlowLayout());
        
        PerroPrim.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // 10 píxeles de espacio arriba y abajo para diferenciar entre cada metodo que reemplazara el texto de los labels
        PezPrim.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        AnimalPrim.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        getContentPane().add(PerroPrim);
        getContentPane().add(PezPrim);
        getContentPane().add(AnimalPrim);

    }

    public String enorden(String a, String b) {
        Animal miPerro = new Perro();
        Animal miPez = new Pez();
        
        return miPerro.moverse() + " " + miPez.moverse();
    }

    public String enorden(String a, int b) {
        Animal miPerro = new Perro();
        Animal miPez = new Pez();

        return miPez.moverse() + " " + miPerro.moverse();
    }

    public String enorden(int a) {
        Animal sisoy = new redundant();
        return sisoy.moverse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            
            String mensajePerroPez = ventana.enorden("a", "b");
            String mensajePezPerro = ventana.enorden("a", 1);
            String mensajeAnimal = ventana.enorden(1);
            
            ventana.setVisible(true);
        
            ventana.PerroPrim.setText(mensajePerroPez);
            ventana.PezPrim.setText(mensajePezPerro);
            ventana.AnimalPrim.setText(mensajeAnimal);
        });
    }
}