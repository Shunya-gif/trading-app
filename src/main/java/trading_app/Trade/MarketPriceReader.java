package trading_app.Trade;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.*;


public class MarketPriceReader {
    String filePath = "src/main/data-files/market_prices.csv";

    public Map<String, BigDecimal> loadMarketData() {
        Map<String, BigDecimal> marketPrices = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String ticker = parts[0];
                BigDecimal price = new BigDecimal(parts[1]);
                marketPrices.put(ticker, price);
                }
            return marketPrices;
        }catch (IOException e){
            System.out.println("時価ファイルの読み込みに失敗");
            return null;
        }
    }
}



