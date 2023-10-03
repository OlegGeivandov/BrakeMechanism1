import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static BrakeMechanism1 bm;

    public static void main(String[] args) {
        String fileName = "1.txt";
        readFromFile(fileName);
        bm.calculateMinimumBrakeLength();
        System.out.println(bm.minimumLegalBrakeLength + " bm.minimumLegalBrakeLength");
    }

    static void readFromFile (String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            bm = new BrakeMechanism1(getArrayListFromString(scanner.nextLine()), getArrayListFromString(scanner.nextLine()));
            scanner.close();
            System.out.println(bm.firstBrakePad + " bm.firstBrakePad");
            System.out.println(bm.secondBrakePad + " bm.secondBrakePad");
        }
        catch (FileNotFoundException e) {
            System.out.println("упс, файл "+fileName+" не найден");
        }
        catch (InputMismatchException e){
            System.out.println("в файле не совсем число записано");
        }
    }

    public static ArrayList<Integer> getArrayListFromString(String str) {
        ArrayList<Integer> list = new ArrayList<>();
        char[] charArray = str.toCharArray();
        for (Character ch : charArray) {
            list.add(Integer.parseInt(String.valueOf(ch)));
        }
        return list;
    }
}