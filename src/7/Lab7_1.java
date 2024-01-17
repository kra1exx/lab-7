import java.util.*;
import java.io.*;
public class Lab7_1 {
    public static void main(String[] args) throws IOException, EOFException {
        Scanner sc = new Scanner(System.in);
        try{
            File f1 = new File("avto.txt");
            if(f1.exists()) f1.delete();
            f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            rf.seek(0);

            System.out.println("Avto_1 " + rf.length());

            System.out.println("Avto povtor>> ");
            int count = sc.nextInt();

            for(int i = 0; i < count; i++){
                System.out.println("Model>> ");
                String model = sc.next();
                rf.writeUTF(model);
                for(int j = 0; j < 20-model.length(); j++){
                    rf.writeByte(1);
                }
                System.out.println("Reg_nomer>> ");
                int nomer = sc.nextInt();
                rf.writeInt(nomer);
                System.out.println("God_vipuska>> ");
                int god = sc.nextInt();
                rf.writeInt(god);
                System.out.println("Probeg>> ");
                int probeg = sc.nextInt();
                rf.writeInt(probeg);
                System.out.println("Stoimost>> ");
                int stoimost = sc.nextInt();
                rf.writeInt(stoimost);
            }

            System.out.println("Avto_2 " + rf.length());

            File f2 = new File("avto1.txt");
            if(f2.exists()) f2.delete();
            f2.createNewFile();
            RandomAccessFile rf2 = new RandomAccessFile(f2, "rw");
            rf.seek(0);
            rf2.seek(0);
            System.out.println("Avto_3 " + rf2.length());

            int found = 0;
            for (int i = 0; i < count; i++){
                rf.seek(52*i);
                String model = rf.readUTF();
                rf.seek(52*i + 22);

                int god = rf.readInt();
                rf.seek(52*i + 48);
                int nomer = rf.readInt();
                rf.seek(52*i + 48);
                int stoimost = rf.readInt();
                rf.seek(52*i + 48);
                int probeg = rf.readInt();

                if (god > 2009){
                    rf2.writeUTF(model);
                    for(int j = 0; j < 20-model.length(); j++){
                        rf2.writeByte(1);
                    }
                    rf2.writeInt(nomer);
                    rf2.writeInt(god);
                    rf2.writeInt(probeg);
                    rf2.writeInt(stoimost);
                    found++;
                }
            }
            System.out.println("Avto_4 " + rf2.length());
            System.out.println("записи>> " + found);
            rf2.seek(0);
            for(int i = 0; i < found; i++){
                rf2.seek(52*i);
                String model = rf2.readUTF();
                rf2.seek(52*i + 22);

                int nomer = rf2.readInt();
                rf2.seek(52*i + 48);
                int god = rf2.readInt();
                rf2.seek(52*i + 48);
                int probeg = rf2.readInt();
                rf2.seek(52*i + 48);
                int stoimost = rf2.readInt();
                System.out.println("Model: " + model + " Reg_nomer: " + nomer + " God_vipuska: " + god + " Probeg: " + probeg + " Stoimost: " + stoimost);
            }
            rf.close();
            rf2.close();
        } catch (EOFException eof) {
            System.out.println("EOF");
        } catch (IOException ioe) {
            System.out.println("IOE");
        }
    }
}