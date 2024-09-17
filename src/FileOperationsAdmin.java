import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileOperationsAdmin {
    public static void writeToFile(ArrayList<Admin> admins) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admins.ser"))) {
            oos.writeObject(admins);
            System.out.println("Data successfully written to Admins.ser");
        } catch (IOException e) {
            System.out.println("Error in file writing.");
           // e.printStackTrace();
        }
    }

    public static ArrayList<Admin> readAllFromFile() {
        ArrayList<Admin> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Admins.ser"))) {
            list = (ArrayList<Admin>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error reading from Admins.ser");
            //e.printStackTrace();
        }
        return list;
    }
}

