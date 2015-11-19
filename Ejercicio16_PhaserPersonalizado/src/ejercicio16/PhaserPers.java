package ejercicio16;

import java.util.concurrent.Phaser;

public class PhaserPers extends Phaser {
	
	public PhaserPers(int parties){
		super(parties);
	}
	
	public boolean onAdvance(int fase, int estudiantes){
		switch(fase){
		case 0:
			return todosSentados();
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			return ejercicioAcabado(fase);
		}
		return true;
	}
	
	public boolean todosSentados(){
		System.out.println("\t\t\t TODOS LOS ALUMNOS ESTAN SENTADOS.");
		return false; // Significa que avance, que no ha acabado todas las fases del phaser. True, que ha acabado. 
	}
	
	public boolean ejercicioAcabado(int numEj){
		System.out.println("\t\t\t TODOS LOS ALUMNOS HAN ACABADO EL EJ: "+ numEj);
		return false;
	}
}
