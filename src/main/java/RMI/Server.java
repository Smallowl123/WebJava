package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private final static int PORT = 8000;
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("day", new Stub());
            System.err.println("Server ready \n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
