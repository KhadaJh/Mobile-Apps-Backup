import java.util.Scanner;

public class PotenciaRecursiva {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el numero: ");
        int num = sc.nextInt();
        System.out.print("Introduce el exponente: ");
        int exp = sc.nextInt();

        int resultado = calcularPotencia(num, exp);
        System.out.println("La potencia de " + num + " elevado a " + exp + " es: " + resultado);
    }

    public static int calcularPotencia(int base, int exp) {
        if (exp == 0) {
            return 1;
        } else if (exp > 0) {
            
            return base * calcularPotencia(base, exp - 1);
        } else {
            
            return 1 / base * calcularPotencia(base, exp + 1);
        }
    }
}
