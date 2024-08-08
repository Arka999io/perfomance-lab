import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Необходимо указать три пути к файлам: values.json, tests.json и report.json.");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        JSONParser parser = new JSONParser();

        try {
            // Считываем values.json
            JSONObject valuesJson = (JSONObject) parser.parse(new FileReader(valuesFilePath));
            JSONArray valuesArray = (JSONArray) valuesJson.get("values");

            // Создаем Map для хранения id и их значений
            Map<Long, String> valuesMap = new HashMap<>();
            for (Object obj : valuesArray) {
                JSONObject valueObj = (JSONObject) obj;
                Long id = (Long) valueObj.get("id");
                String value = (String) valueObj.get("value");
                valuesMap.put(id, value);
            }

            // Считываем tests.json
            JSONObject testsJson = (JSONObject) parser.parse(new FileReader(testsFilePath));

            // Получаем корневой массив тестов
            JSONArray testsArray = (JSONArray) testsJson.get("tests");

            // Заполняем значения value на основе values.json
            fillTestValues(testsArray, valuesMap);

            // Записываем результат в report.json
            try (FileWriter file = new FileWriter(reportFilePath)) {
                file.write(testsJson.toJSONString());
                file.flush();
            }

            System.out.println("Файл " + reportFilePath + " успешно создан!");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Рекурсивный метод для заполнения значений value
    private static void fillTestValues(JSONArray testNodes, Map<Long, String> valuesMap) {
        for (Object obj : testNodes) {
            JSONObject testNode = (JSONObject) obj;
            Long id = (Long) testNode.get("id");
            if (valuesMap.containsKey(id)) {
                testNode.put("value", valuesMap.get(id));
            }

            JSONArray children = (JSONArray) testNode.get("values");
            if (children != null) {
                fillTestValues(children, valuesMap);
            }
        }
    }
}
