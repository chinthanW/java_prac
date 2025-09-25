import java.io.*;

public class VulnerableExample {

    public static void main(String[] args) {
        VulnerableExample example = new VulnerableExample();
        example.readFile("test.txt");
        example.sqlInjectionExample("1 OR 1=1");
        example.weakRandomExample();
    }

    // Vulnerability 1: Using FileInputStream without proper resource closing (resource leak)
    public void readFile(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            byte[] data = new byte[1024];
            fis.read(data);
            // Not closing the FileInputStream -> resource leak
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vulnerability 2: SQL Injection due to string concatenation in query
    public void sqlInjectionExample(String userInput) {
        String query = "SELECT * FROM users WHERE id = " + userInput;
        System.out.println("Executing query: " + query);
        // Imagine this is executed on a real DB -> vulnerable to SQL Injection
    }

    // Vulnerability 3: Use of weak random number generator
    public void weakRandomExample() {
        int randomNumber = (int) (Math.random() * 100);
        System.out.println("Weak random number: " + randomNumber);
    }
}
