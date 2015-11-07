package ejercicio13;

public class Ejercicio13 {

	public static void main(String[] args) {
		
		Videoconferencia videoCon = new Videoconferencia(5);
		Thread hilo1 = new Thread(videoCon);
		hilo1.start();
		
		Pais espania = new Pais(2, "España", videoCon);
		Thread hilo2 = new Thread(espania);
		hilo2.start();
		
		Pais italia = new Pais(2, "Italia", videoCon);
		Thread hilo3 = new Thread(italia);
		hilo3.start();
		
		Pais francia = new Pais(2, "Francia", videoCon);
		Thread hilo4 = new Thread(francia);
		hilo4.start();
		
		Pais portugal = new Pais(2, "Portugal", videoCon);
		Thread hilo5 = new Thread(portugal);
		hilo5.start();
		
		Pais alemania = new Pais(2, "Alemania", videoCon);
		Thread hilo6 = new Thread(alemania);
		hilo6.start();
		
		new Participante(espania, "antonio").start();
		new Participante(alemania, "manolo").start();
		new Participante(francia, "pedro").start();
		new Participante(espania, "lola").start();
		new Participante(portugal, "maria").start();
		new Participante(italia, "juana").start();
		new Participante(italia, "paloma").start();
		new Participante(alemania, "juanito").start();
		new Participante(francia, "vero").start();
		new Participante(portugal, "cris").start();
	}

}
