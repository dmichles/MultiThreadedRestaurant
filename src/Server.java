public class Server implements Runnable {
    public volatile boolean continueworking = true;
    private final Manager manager = Manager.getInstance();
    private final static Object object = new Object();
    private Dish dish;


    @Override
    public void run() {
        while (continueworking || !manager.getOrderInProcessQueue().isEmpty() || !manager.getDishQueue().isEmpty()) {
            if (!checkDishQueue()) {       // Bring out the prepared dish
                System.out.println(String.format("The server %s delivered the order for table %d ", Thread.currentThread().getName(), dish.getTableNumber()));

            }
        }
    }

    public boolean checkDishQueue() {
        synchronized (object) {
            if (!manager.getDishQueue().isEmpty()) {
                dish = manager.getDishQueue().poll();
                return false;
            } else {

                return true;
            }
        }
    }
}