public class Cook implements Runnable {
    public volatile boolean continueWorking = true;
    private final Manager manager = Manager.getInstance();
    private Order order;
    private final static Object o = new Object();
    private static int counter;

    @Override
    public void run() {
        boolean needToWait;
        while ((continueWorking || !manager.getOrderQueue().isEmpty()) && counter < 10) {
            try {
                needToWait = !needToCookOrders();
                if (!needToWait) {
                    cook();
                }
                if (continueWorking && needToWait) {
                    System.out.println(String.format("The cook %s is resting",Thread.currentThread().getName()));
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private boolean needToCookOrders() {
        synchronized (o) {
            if (manager.getOrderQueue().isEmpty()) {
                return false;
            } else {
                order = manager.getOrderQueue().poll();
                counter = counter + 1;
                return true;
            }
        }
    }

    private void cook() throws InterruptedException {
        // The cook takes an order from the queue
        System.out.println(String.format("The order will be prepared in %d ms for table %d by cook %s", order.getTime(), order.getTableNumber(), Thread.currentThread().getName()));
        Manager.getInstance().getOrderInProcessQueue().add(order);
        Thread.sleep(order.getTime());     // Prepare the dish

        Dish dish = new Dish(order.getTableNumber());       // This is a prepared dish
        System.out.println(String.format("The order for table %d is ready said cook %s", dish.getTableNumber(), Thread.currentThread().getName()));
        Manager.getInstance().getOrderInProcessQueue().poll();
        manager.getDishQueue().add(dish);
    }
}