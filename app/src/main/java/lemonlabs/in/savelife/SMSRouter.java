package lemonlabs.in.savelife;

/**
 * Created by Santhosh.Joseph on 18-07-2016.
 */
public interface SMSRouter {
    public void messageReceived(String messageText);
}
