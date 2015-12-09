package ca.jbrains.pos;

public class SystemOutPostOffice implements PostOffice {
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
