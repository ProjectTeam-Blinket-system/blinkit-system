package observer;

public class CustomerObserver implements observer.Observer {

    @Override
    public void update(String status) {

        System.out.println("Customer Notification: Order status changed to " + status);
    }
}