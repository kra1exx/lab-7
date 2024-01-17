import java.io.*;
import java.util.*;
public class Lab7_2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream oin = null;
        try{
            Scanner sc = new Scanner(System.in);
            File f1 = new File("avto3.txt");
            if(f1.exists()) f1.delete();
            f1.createNewFile();
            fos = new FileOutputStream(f1);
            oos = new ObjectOutputStream(fos);
            System.out.println("Avto povtor>> ");
            int count = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < count; i++) {
                Avto avto = new Avto();
                System.out.println("Model>> ");
                avto.model = sc.nextLine();
                System.out.println("Reg_nomer>> ");
                avto.nomer = sc.nextLine();
                System.out.println("God_vipuska>> ");
                avto.god = sc.nextInt();
                System.out.println("Probeg>> ");
                avto.probeg = sc.nextInt();
                System.out.println("Stoimost>> ");
                avto.stoimost = sc.nextInt();
                sc.nextLine();
                oos.writeObject(avto);
            }

            fis = new FileInputStream(f1);
            oin = new ObjectInputStream(fis);
            Avto m = null;
            for (int i = 0; i<count; i++) {
                m = (Avto) oin.readObject();
                if (m.god > 2009){
                    System.out.println(m);
                }
            }

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        finally {
            oos.flush();
            oos.close();
            fos.close();
            fis.close();
            oin.close();
        }


    }
}