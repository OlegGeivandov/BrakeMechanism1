import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BrakeMechanism1 {
    ArrayList<Integer> firstBrakePad;
    ArrayList<Integer> secondBrakePad;
    int minimumLegalBrakeLength;

    public BrakeMechanism1(ArrayList<Integer> firstBrakePad, ArrayList<Integer> secondBrakePad) {
        if (firstBrakePad.size() >= secondBrakePad.size()) {
            this.firstBrakePad = firstBrakePad;
            this.secondBrakePad = secondBrakePad;
        }
        else {
            this.firstBrakePad = secondBrakePad;
            this.secondBrakePad = firstBrakePad;
        }
    }

    int calculateMinimumBrakeLength(){
        int firstPadSize = firstBrakePad.size();
        int secondPadSize = secondBrakePad.size();
        int firstAndSecondSize = firstPadSize + secondPadSize; // размерность = 1-я колодка + вторая
        int firstAndTwoSecondSize = firstPadSize + 2*secondPadSize; // размерность = 1-я колодка + 2 вторых

        // увеличенная первая колодка, заполненная 0
        ArrayList<Integer> extendedFirstPad = new ArrayList<>(Collections.nCopies(firstAndTwoSecondSize-1, 0));

        // заполняем увеличенную первую колодку
        for (int i = secondPadSize; i < firstAndSecondSize; i++) {
            extendedFirstPad.set(i, firstBrakePad.get(i-secondPadSize));
        }
        System.out.println(extendedFirstPad + " extendedFirstPad");

        int minSize = firstAndSecondSize;

        // сдвигаем вторую колодку относительно первой
        for (int i = 0; i < firstAndSecondSize; i++) {
            // увеличенная вторая колодка, заполненная 0
            ArrayList<Integer> extendedSecondPad = new ArrayList<>(Collections.nCopies(firstAndTwoSecondSize-1, 0));

            // заполняем увеличенную вторую колодку
            for (int n = i; n < secondPadSize+i; n++) {
                extendedSecondPad.set(n, secondBrakePad.get(n-i));
            }

            // результат наложения двух расширенных колодок
            ArrayList<Integer> bothPad = new ArrayList<>(Collections.nCopies(firstAndTwoSecondSize-1, 0));
            for (int j = 0; j < firstAndTwoSecondSize-1; j++) {
                bothPad.set((j), (extendedFirstPad.get(j)+extendedSecondPad.get(j)));
            }
            System.out.println(bothPad + " bothPad   i=" + i);

            // расчет размера данной конфигурации наложения
            if (bothPad.stream().allMatch(x->x<=3)){ // если все элементы наложения не больше 3
                int minSizeTemp = 0;

                for (int j = 0; j < firstAndTwoSecondSize-1; j++){ // проходим по всем элементам
                    if (bothPad.get(j)!=0) minSizeTemp++; // если элемент отличается от 0
                }
                if (minSizeTemp < minSize) minSize = minSizeTemp;
                System.out.println(minSizeTemp + " minSizeTemp      " + minSize + " minSize    " + bothPad.stream().allMatch(x->x<=3));
            }
        }
        minimumLegalBrakeLength = minSize;
        return minSize;
    }
}
