

public class Waiter implements Runnable {
    public volatile boolean continueWorking = true;
    private final Manager manager = Manager.getInstance();
    private static final Object object = new Object();
    private static int counter = 0;

    @Override
    public void run() {
        while (continueWorking) {
            synchronized (object) {
                if (manager.getOrderInProcessQueue().size() < 2 && counter < 10) {                   // Take a new order
                    counter++;
                    Table table = manager.getNextTable();
                    Order order = table.getOrder();
                    System.out.println(String.format("An order was received from table %d by waiter %s ", order.getTableNumber(), Thread.currentThread().getName()));
                    manager.getOrderQueue().add(order);
                }
            }
            try {
                Thread.sleep(100);  // Walking to the next table
            } catch (InterruptedException ignored) {
            }
        }

    }
}


