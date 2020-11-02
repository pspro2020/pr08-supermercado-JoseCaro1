package supermercado;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CheckoutQueue {

    private Checkout[] cajas = new Checkout[4];
    private boolean cajasAvailable[] = new boolean[4];
    private Random random = new Random();
    private Semaphore semaphore = new Semaphore(4);
    private Lock lock = new ReentrantLock();
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    public CheckoutQueue() {
        initialStage();

    }

    private void searchArticles() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(4) + 1);
        System.out.printf("%s - %s ha terminado de buscar articulos\n", LocalTime.now().format(format), Thread.currentThread().getName());
    }

    protected void addCheckout() throws InterruptedException {
        searchArticles();
        System.out.printf("%s - %s se ha colocado en la cola unica\n", LocalTime.now().format(format), Thread.currentThread().getName());
        try {
            semaphore.acquire();
            int checkoutNumber = selectCheckout();
            System.out.printf("%s - %s ha comenzado a ser atendido en la caja %d\n", LocalTime.now().format(format), Thread.currentThread().getName(), checkoutNumber+1);

            cajas[checkoutNumber].paying();
            cajasAvailable[checkoutNumber] = true;
        } finally {
            semaphore.release();
        }


    }

    private int selectCheckout() {
        lock.lock();
        try {
            for (int i = 0; i < cajas.length; i++) {
                if (cajasAvailable[i]) {
                    cajasAvailable[i] = false;
                    return i;
                }
            }
        } finally {
            lock.unlock();
        }


        return -1;
    }

    private void initialStage() {
        for (int i = 0; i < 4; i++) {
            cajas[i] = new Checkout(i + 1);
            cajasAvailable[i] = true;
        }
    }
}
