package observer;

public class DeliveryAgentObserver implements observer.Observer {

    @Override
    public void update(String status) {

        System.out.println("Delivery Agent Notification: Order status changed to " + status);
    }
}