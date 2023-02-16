package principal.implementaciones;

import principal.uno.OperacionInterface;

public class Sumar implements OperacionInterface{

	@Override
	public double sumar(double a, double b) {
		return a + b;
	}

	@Override
	public double restar(double a, double b) {
		return a - b;
	}

	@Override
	public double multiplicar(double a, double b) {
		return a * b;
	}

	@Override
	public double dividir(double a, double b) {
		return a / b;
	}
	
}
