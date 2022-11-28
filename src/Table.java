public class Table {
    private static byte tableNumber;
    private final byte number = ++tableNumber;

    public Order getOrder () {
        return new Order(number);
    }
}
