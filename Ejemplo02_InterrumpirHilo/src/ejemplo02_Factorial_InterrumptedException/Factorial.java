package ejemplo02_Factorial_InterrumptedException;

public class Factorial extends Thread {
	private double num = 1;

	public void run() {
		boolean salir = false;
		do {
			try {
				System.out.printf("- factorial de %.0f: %.0f",num ,hacerFactorial(num));
			} catch (InterruptedException e) {
				System.out.println("Se ha interrumpido la ejecución del hilo.");
				salir = true;
			}
			num++;
		} while (!salir);
	}

	private double hacerFactorial(double num) throws InterruptedException {
		double r;
		if (isInterrupted())
			throw new InterruptedException();

		if (num == 1){
			r = 1;
			System.out.println("");
		} else
			r = num * hacerFactorial(num - 1);
		return r;
	}
}
