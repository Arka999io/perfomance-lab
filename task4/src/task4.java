import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class task4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Не указан аргумент");
            return;
        }

        String filePath = args[0];
        int[] nums = readNumbersFromFile(filePath);
        if (nums == null || nums.length == 0) {
            System.out.println("Ошибка чтения данных из файла или файл пуст.");
            return;
        }

        int minMoves = calculateMinMoves(nums);
        System.out.println(minMoves);
    }

    private static int[] readNumbersFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int calculateMinMoves(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2]; // медиана для отсортированного массива
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}
