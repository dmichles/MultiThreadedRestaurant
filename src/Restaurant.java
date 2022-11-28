
    /*
Restaurant

*/

    public class Restaurant {

    public static void main(String[] args) throws Exception {
        Waiter waiterTarget = new Waiter();

        Server serverTarget = new Server();

        Thread waiter = new Thread(waiterTarget);
        Thread waiter2 = new Thread(waiterTarget);
        Thread server = new Thread(serverTarget);
        Thread server2 = new Thread(serverTarget);


        Cook cookTarget = new Cook();

        Thread cook = new Thread(cookTarget);
        Thread cook2 = new Thread(cookTarget);
        Thread cook3 = new Thread(cookTarget);

        waiter.start();
        waiter2.start();
        cook.start();
        cook2.start();
        cook3.start();

        server.start();
        server2.start();


        Thread.sleep(1200);
        cookTarget.continueWorking = false;
        waiterTarget.continueWorking = false;
        serverTarget.continueworking = false;


    }
}

