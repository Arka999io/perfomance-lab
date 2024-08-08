import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task2 {

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }


        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        try {
            // Считывание данных окружности
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFilePath));
            String[] circleCenterCoords = circleReader.readLine().split(" ");
            double circleX = Double.parseDouble(circleCenterCoords[0]);
            double circleY = Double.parseDouble(circleCenterCoords[1]);
            double radius = Double.parseDouble(circleReader.readLine());
            circleReader.close();

            // Считывание точек и вычисление их положения относительно окружности
            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFilePath));
            String pointLine;

            while ((pointLine = pointsReader.readLine()) != null) {
                String[] pointCoords = pointLine.split(" ");
                double pointX = Double.parseDouble(pointCoords[0]);
                double pointY = Double.parseDouble(pointCoords[1]);

                // Вычисление расстояния от точки до центра окружности
                double distanceSquared = Math.pow(pointX - circleX, 2) + Math.pow(pointY - circleY, 2);
                double radiusSquared = Math.pow(radius, 2);

                // Определение положения точки
                if (distanceSquared == radiusSquared) {
                    System.out.println(0); // Точка лежит на окружности
                } else if (distanceSquared < radiusSquared) {
                    System.out.println(1); // Точка внутри окружности
                } else {
                    System.out.println(2); // Точка снаружи окружности
                }
            }
            pointsReader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в формате данных: " + e.getMessage());
        }
    }
}
