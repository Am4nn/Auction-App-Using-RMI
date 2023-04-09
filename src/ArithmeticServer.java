import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class ArithmeticServer extends UnicastRemoteObject implements Arithmetic {
    public ArithmeticServer() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    public double divide(double a, double b) throws RemoteException {
        return a / b;
    }

    public static void main(String[] args) throws Exception {
        ArithmeticServer server = new ArithmeticServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Arithmetic", server);
        System.out.println("Arithmetic Server Started !");
    }
}
