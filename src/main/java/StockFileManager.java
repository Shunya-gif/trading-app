import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StockFileManager {
    private static final String FILE_NAME = "japan_stock_data.csv";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public StockManager readCsv(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        if (inputStream == null) {
            System.out.println("リソースファイルが見つかりませんでした。");
            return null;
        }
        // InputStreamを使ってファイルを読み込む
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.readLine();//一行目（カラム）を読み捨てる。
            String line;
            StockManager stockManager = new StockManager();
            while ((line = reader.readLine()) != null) {
                List<String> splitLine = List.of(line.split(","));
                Stock stock = new Stock(splitLine.get(0),splitLine.get(1),Market.fromOtherName(splitLine.get(2)),Long.parseLong(splitLine.get(3)), LocalDate.parse(splitLine.get(4),dateTimeFormatter));
                stockManager.addStock(stock);
            }
            return stockManager;
        } catch (IOException e) {
            System.out.println("ファイルの読み込みに失敗");
            return null;
        }catch (IllegalArgumentException e){
            System.out.println("ファイル内に無効な値が入力されています"+e.getLocalizedMessage());
            return null;
        }catch (DateTimeParseException e) {
            System.out.println("ファイル内の日付に無効な値が入力されています。");
            return null;
        }
    }
    public void writeCSV(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        if (inputStream == null) {
            System.out.println("リソースファイルが見つかりませんでした。");
        }
        try(BufferedWriter writer = new BufferedWriter())
    }
    }

