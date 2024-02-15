
//fibonacci 4 = fibonacci (4-1) + fibonacci (4-2) = fibonacci (3) + fibonacci (2) == 2 + 1
//fibonacci 4 = 3;

//fibonacci 3 = fibonacci (3-1) + fibonacci (3-2) = fibonacci (2) + fibonacci (1) == 1 + 1
//fibonacci 3 = 2;

//fibonacci 2 = fibonacci (2-1) + fibonacci (2-2) == fibonacci (1) +fibonacci (0) = 1 + 0
//fibonacci 2 = 1;

public class Fiboex {
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1){
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        int n = 7; // puedes cambiar este valor para obtener diferentes términos de la serie de Fibonacci
        System.out.println("El término " + n + " de la serie de Fibonacci es: " + fibonacci(n));
    }
}