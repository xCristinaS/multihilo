/**
 * Created by xCristina_S on 21/09/2015.
 */
public class Calculadora implements Runnable{
    private int numero;

    public Calculadora(int numero){
        this.numero = numero;
    }
    public void run(){
        for (int i = 1; i <= 10; i++)
            //System.out.printf("- %s:%d * %d = %d\n",Thread.currentThread().getName(),numero,i,numero*i);
        System.out.println(Thread.currentThread().getName());
    }
}
