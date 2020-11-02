package supermercado;

public class Cliente implements Runnable {


    private CheckoutQueue checkoutQueue;

    public Cliente(CheckoutQueue checkoutQueue){
        this.checkoutQueue=checkoutQueue;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s ha llegado al supermercado\n",Thread.currentThread().getName());
            checkoutQueue.addCheckout();
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}
