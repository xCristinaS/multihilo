package ejercicio10;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Producto {
	
	private float precio;
	private ReadWriteLock cerrojo = new ReentrantReadWriteLock();
	private Random rnd = new Random();
	
	public Producto(float precio){
		this.precio = precio;
	}
	
	public float getPrecio(){
		cerrojo.readLock().lock();
		 try {
			 TimeUnit.SECONDS.sleep(rnd.nextInt(5));
			 System.out.printf("%s consultando precio.\n", Thread.currentThread().getName());
		 } catch (InterruptedException e){
			 
		 } finally {
			 System.out.printf("\t%s ha acabado de consultar precio.\n", Thread.currentThread().getName());
			 cerrojo.readLock().unlock();
		 }
		return precio;
		 
	}
	
	public void setPrecio(float precio){
		cerrojo.writeLock().lock();
		 try {
			 TimeUnit.SECONDS.sleep(rnd.nextInt(5));
			 System.out.printf("%s cambiando precio.\n", Thread.currentThread().getName());
		 } catch (InterruptedException e){
			 
		 } finally {
			 System.out.printf("\t%s ha acabado de cambiar el precio.\n", Thread.currentThread().getName());
			 cerrojo.writeLock().unlock();
		 }
	}
}
