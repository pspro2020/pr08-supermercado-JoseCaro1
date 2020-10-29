package supermercado;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Checkout {

    private int numCaja;
    private Random random = new Random();
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm:ss");

    public Checkout(int numCaja){

        this.numCaja=numCaja;
    }

    protected void paying() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(5)+1);
        System.out.printf("%s - %s ha terminado su compra en la caja %d\n", LocalTime.now().format(formatter),Thread.currentThread().getName(),numCaja);
    }
}
