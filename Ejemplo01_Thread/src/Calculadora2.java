/**
 * Created by xCristina_S on 23/09/2015.
 */
public class Calculadora2 extends Thread{
    private int numero;

    public Calculadora2(int numero, String nombre){
        super(nombre);
        this.numero = numero;
    }
    public void run(){
        for (int i = 1; i <= 10; i++)
           // System.out.printf("- %s:%d * %d = %d\n",getName(),numero,i,numero*i);
        System.out.println(getName());
    }
}
