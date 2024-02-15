// 3^5 = 3 * 3^4
// 3^4 = 3 * 3^3
// 3^3 = 3 * 3^2 
// 3^2 = 3 * 3 
// 3^1 = 3 * 1

//exp(n, 3-1) * n = exp (n, 2) * n
//exp(n, 2-1) * n = exp (n, 1) * n
//exp(n, 1-1) * n = exp (n, 0) * n
//el if reemplaza el valor de exp (n,0), lo que significa que exp (n,0) = 1 * n

import java.util.Scanner;

public class H1 {

	public static class Exp {
    		static int exp(int n, int x) {
        		if (x == 0) {
            			return 1;
        		} else {
            			return exp(n, x - 1) * n ;
        		}
    		}
	}

	public static void main(String[] args) {

	        Scanner scanner = new Scanner(System.in);
		
	        System.out.print("Por favor, ingrese la base del numero que desee potenciar: ");
        	int n = scanner.nextInt();

	        System.out.print("Ahora ingrese un numero entero que quiera como exponente: ");
			int x = scanner.nextInt();
		
       		System.out.println("El producto de " + n + " elevado a la " + x + " es: " + Exp.exp(n,x));
	}
}