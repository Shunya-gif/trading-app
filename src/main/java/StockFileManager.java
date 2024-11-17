import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StockFileManager {
    private static final String FILE_NAME = "japan_stock_data.csv";
    public void readCsv(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        if (inputStream == null) {
            System.out.println("リソースファイルが見つかりませんでした。");
            return;
        }
        // InputStreamを使ってファイルを読み込む
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("メニュー1の処理を完了");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

