import java.io.IOException;
import java.util.Scanner;
import java.io.*;
public class RandAccNumb{
    public static void main(String[] args) {
        try {
            File folder = new File("E:\\My");
            if (!folder.exists())
                folder.mkdir();
            File f1 = new File("E:\\My\\num1Mart.txt");
            f1.createNewFile();
            System.out.print("Введите любое дробное число: ");
            Scanner scan = new Scanner(System.in);
            long count = scan.nextLong();
            System.out.println ("Вы ввели число " + count);

            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            System.out.println("Исходный размер файла в байтах =" + rf.length() + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Введите числа:");
            for (long i = 0; i < count; i++) {
                rf.writeLong(scan.nextLong());
            }
            System.out.println("Новый размер файла в байтах =" + rf.length() + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Количество байт на 1 число = " + rf.length() / count);
            rf.close();

            rf = new RandomAccessFile(f1, "r");
            System.out.println("\n Числа в файле:");
            for (long i = 0; i < count; i++) {
                rf.seek(i * 5);
                System.out.println("Число" + i + ": " +rf.readLong());
            }
            rf.seek(rf.getFilePointer() - 5);
            System.out.println(" Количество чисел в файле= " + rf.length()/5 + ", последнее число= " + rf.readLong());

            System.out.print("\nВведите число, которое нужно найти в файле => ");
            long x = scan.nextLong();
            long kol = 0;
            for (long i = 0; i < count; i++){
                rf.seek(i * 5);
                long number = rf.readLong();
                if (number == x){
                    kol++;
                    System.out.print("номер " + i + ", ");
                }
            }
            System.out.println(" количество искомых чисел = " + kol);
            rf.close();
            rf = new RandomAccessFile(f1, "rw");
            for (long k = 0; k < count; k++) {
                for (int i = 0; i < count - k -1; i++) {
                    rf.seek(i * 5);
                    long number1 = rf.readLong();
                    long number2 = rf.readLong();
                    if (number1 > number2) {
                        rf.seek(i * 4);
                        rf.writeLong(number2);
                        rf.writeLong(number1);
                    }
                }
            }
            System.out.println("\n Числа, отсортированные в файле:");
            for (long i = 0; i < count; i++) {
                rf.seek(i * 4);
                System.out.print(" " + rf.readLong());
            }
            rf.close();
        }
        catch (IOException e){
            System.out.println("End of file "+e);}
    }
}