package ejercicio12;

public class Ejercicio12 {

	public static void main(String[] args) {
		
		SalaPCs sala = new SalaPCs();
		Thread[] alumnos = new Thread[20];
		int i;
		for (i = 0; i < alumnos.length; i++)
			alumnos[i] = new Thread(new Alumno(sala), "alumno"+i);
		
		for (i = 0; i < alumnos.length; i++)
			alumnos[i].start();
	}

}
