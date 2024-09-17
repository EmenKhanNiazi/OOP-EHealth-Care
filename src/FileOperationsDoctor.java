import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

    public class FileOperationsDoctor {
            public static void writeToFile(ArrayList<Doctor> doctors) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Doctors.ser"))) {
                    oos.writeObject(doctors);
                    System.out.println("Data successfully written to Doctors.ser");
                } catch (IOException e) {
                    System.out.println("Error in file writing.");
                    //e.printStackTrace();
                }
            }

            public static ArrayList<Doctor> readAllFromFile() {
                ArrayList<Doctor> list = new ArrayList<>();
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Doctors.ser"))) {
                    list = (ArrayList<Doctor>) ois.readObject();
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Error reading from Doctors.ser");
                    //e.printStackTrace();
                }
                return list;
            }
      /*  public static void writeToFile(Doctor d) {
            try {
                File f = new File("Doctors.ser");
                ObjectOutputStream write_Object;
                if (f.exists())
                    write_Object = new ObjectOutputStream(new FileOutputStream(f, true));
                else {
                    write_Object = new ObjectOutputStream(new FileOutputStream(f));
                }
                write_Object.writeObject(d);
                write_Object.close();
            } catch (IOException e) {
                System.out.println("Error in file writing.");
                e.printStackTrace();
            }
        }
        public static void readFromFile() {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Doctors.ser"));
                while (true) {
                    Doctor d = (Doctor) ois.readObject();
                    System.out.println(d.toString());
                }

            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
            } catch (EOFException e) {
                System.out.println("end of file");
                return;
            } catch (IOException e) {
                System.out.println("exception e");

            }
        }
        public static ArrayList<Doctor> readAllFromFile() {
            ArrayList<Doctor> list = new ArrayList<Doctor>();
            try {
                ObjectInputStream ios = new ObjectInputStream(new FileInputStream("Doctors.ser"));
                while (true) {
                    Doctor d= (Doctor) ios.readObject();
                    list.add(d);
                }
            } catch (ClassNotFoundException e)

            {
                System.out.println("1");
            } catch (EOFException e) {
                return list;
            } catch (IOException i) {
                System.out.println("3");
                i.printStackTrace();
            }
            return list;
        }*/
}
