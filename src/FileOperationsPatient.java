import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileOperationsPatient {
    public static void writeToFile(ArrayList<Patient> patients) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Patients.ser"))) {
            oos.writeObject(patients);
            System.out.println("Data successfully written to Patients.ser");
        } catch (IOException e) {
            System.out.println("Error in file writing.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Patient> readAllFromFile() {
        ArrayList<Patient> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Patients.ser"))) {
            list = (ArrayList<Patient>) ois.readObject();
            System.out.println("Data successfully read from Patients.ser");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error reading from Patients.ser");
            e.printStackTrace();
        }
        return list;
    }
}
        /*try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Patients.ser"))) {
            oos.writeObject(patients);
            System.out.println("Data successfully written to Patients.ser");
        } catch (IOException e) {
            System.out.println("Error in file writing.");
         //   e.printStackTrace();
        }
    }

    public static ArrayList<Patient> readAllFromFile() {
        ArrayList<Patient> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Patients.ser"))) {
            list = (ArrayList<Patient>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error reading from Patients.ser");
           // e.printStackTrace();
        }
        return list;
    }
}*/
