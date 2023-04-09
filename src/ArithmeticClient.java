import java.rmi.*;

public class ArithmeticClient {
    public static void main(String[] args) {
        try {
            Arithmetic arithmetic = (Arithmetic) Naming.lookup("rmi://localhost/Arithmetic");
            double a = 10, b = 20;
            System.out.println("Result of " + a + " + " + b + " is " + arithmetic.add(a, b));
            System.out.println("Result of " + a + " - " + b + " is " + arithmetic.subtract(a, b));
            System.out.println("Result of " + a + " * " + b + " is " + arithmetic.multiply(a, b));
            System.out.println("Result of " + a + " / " + b + " is " + arithmetic.divide(a, b));
        } catch (Exception e) {
            System.out.println("ArithmeticClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
