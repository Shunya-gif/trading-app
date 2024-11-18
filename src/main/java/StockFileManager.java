import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StockFileManager {
    private static final Path File_Path = Paths.get("src/main/data-files/japan_stock_data_copied.csv");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public StockManager readCsv(){
        // InputStreamを使ってファイルを読み込む
        try (BufferedReader reader = Files.newBufferedReader(File_Path, StandardCharsets.UTF_8)) {
            reader.readLine();//一行目（カラム）を読み捨てる。
            String line;
            StockManager stockManager = new StockManager();
            while ((line = reader.readLine()) != null) {
                List<String> splitLine = List.of(line.split(","));
                Stock stock = new Stock(splitLine.get(0), splitLine.get(1), Market.fromOtherName(splitLine.get(2)), Long.parseLong(splitLine.get(3)), LocalDate.parse(splitLine.get(4), dateTimeFormatter));
                stockManager.addStock(stock);
            }
            return stockManager;
        }catch (NoSuchFileException e){
            System.out.println("ファイルが見つかりません。");
            return null;
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
        StockManager stockManager = new StockManager();
        try(BufferedWriter writer = Files.newBufferedWriter(File_Path,StandardCharsets.UTF_8,StandardOpenOption.APPEND)){
            String registerToCSV =  stockManager.buildCSVLine();
            writer.write(registerToCSV);
            writer.newLine();
        }catch (NoSuchFileException e){
            System.out.println("ファイルが見つかりません。");
        } catch (IOException e) {
            System.out.println("ファイルの読み込みに失敗");
        }
    }
    }

