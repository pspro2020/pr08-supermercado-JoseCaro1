package supermercado;

public class Main {

    public static void main(String[] args) {
        Thread clientes[]= new Thread[50];
        CheckoutQueue cola= new CheckoutQueue();

        for (int i = 0; i < 50; i++) {
            clientes[i]= new Thread(new Cliente(cola),"Cliente "+i);
        }
        for (int i = 0; i < 50; i++) {
            clientes[i].start();
        }
    }
}
