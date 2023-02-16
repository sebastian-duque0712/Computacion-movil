package principal;

import java.util.Scanner;

import principal.implementaciones.Sumar;
import principal.uno.OperacionInterface;

public class Menu {

	Scanner sc = new Scanner(System.in);

	public void mostrarMenu() {
		System.out.println("Digite el primer numero");
		double a = sc.nextDouble();

		System.out.println("Digite el segundo numero");
		double b = sc.nextDouble();

		System.out.println("Que operacion desea hacer?");
		int seleccion = sc.nextInt();
		
		Sumar sm = new Sumar();
		switch (seleccion) {
		case 1:
			System.out.println(sm.sumar(a, b));
			break;
		case 2:
			System.out.println(sm.restar(a, b));
			break;
		case 3:
			System.out.println(sm.multiplicar(a, b));
			break;
		case 4:
			System.out.println(sm.dividir(a, b));
			break;
		default:
			System.err.println("Opcion no valida");
			break;
		}
	}
}
