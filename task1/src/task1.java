import java.util.ArrayList;
import java.util.List;

public class task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов. Укажите два аргумента: n и m.");
            return;
        }

        int n;
        int m;

        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Аргументы должны быть целыми числами.");
            return;
        }

        if (n <= 0 || m <= 0) {
            System.out.println("Ошибка: Значения n и m должны быть положительными.");
            return;
        }

        // Круговой массив от 1 до n
        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        List<Integer> path = new ArrayList<>();
        int currentIndex = 0;

        // Поиск пути
        while (true) {
            path.add(circularArray[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n; // Сдвиг на m элементов
            if (currentIndex == 0) break; // Завершение
        }

        // Вывод результата
        System.out.print("Путь: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
