package trading_app.Trade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TradeDataFileManager {
        private static final Path File_Path = Paths.get("src/main/data-files/trade_data_copied.csv");

        public TradeManager readCsv(){
            try (BufferedReader reader = Files.newBufferedReader(File_Path, StandardCharsets.UTF_8)) {
                reader.readLine();//一行目（カラム）を読み捨てる。
                String line;
                TradeManager dataList = new TradeManager();
                while ((line = reader.readLine()) != null) {
                    List<String> splitLine = List.of(line.split(","));
                    TradeData tradeData = new TradeData(splitLine.get(0),splitLine.get(1),Side.fromOtherName(splitLine.get(2).toUpperCase()),Long.parseLong(splitLine.get(3)),new BigDecimal(splitLine.get(4)), LocalDateTime.parse(splitLine.get(5)),LocalDateTime.parse(splitLine.get(6)));
                    dataList.addData(tradeData);
                }
                return dataList;
            }catch (NoSuchFileException e){
                System.out.println("ファイルが見つかりません。");
                return null;
            } catch (IOException e) {
                System.out.println("ファイルの読み込みに失敗");
                return null;
            } catch (IllegalArgumentException e){
                System.out.println("ファイル内に無効な値が入力されています"+e.getLocalizedMessage());
                return null;
            }catch (DateTimeParseException e) {
                System.out.println("ファイル内の日付に無効な値が入力されています。");
                return null;
            }
        }
        public void writeCSV(){
            TradeManager tradeManager = new TradeManager();
            try(BufferedWriter writer = Files.newBufferedWriter(File_Path,StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
                String registerToCSV =  tradeManager.buildCSVLine();
                writer.write(registerToCSV);
                writer.newLine();
            }catch (NoSuchFileException e){
                System.out.println("ファイルが見つかりません。");
            } catch (IOException e) {
                System.out.println("ファイルの読み込みに失敗");
            }
        }
    }


