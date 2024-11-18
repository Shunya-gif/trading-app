import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class StockManager {
    private final List<Stock> stockList = new ArrayList<>();
    private static final int widthOfTable = 84;



    public void addStock(Stock stock) {
        stockList.add(stock);
    }
    public boolean isAlreadyRegistered(String codeUserInput){
        StockFileManager stockFileManager = new StockFileManager();
        StockManager stocksFromCsv = stockFileManager.readCsv();
        if(stocksFromCsv == null){
            return false;
        }else{
            for (Stock stock :stocksFromCsv.stockList){
                if(Objects.equals(codeUserInput, stock.getTickerCode())){
                    return true;
                }
            }
        }
        return false;
    }
    public static void displayStocks(){
        StockFileManager stockFileManager = new StockFileManager();
        StockManager stocksFromCsv = stockFileManager.readCsv();
        if(stocksFromCsv == null){
            System.out.println("CSVファイルからの受取りに失敗。");
        }else{
            TableCreator tableCreator = new TableCreator();
            tableCreator.createHorizontalLine(widthOfTable);
            tableCreator.createStockColumns();
            tableCreator.createHorizontalLine(widthOfTable);
            for(Stock stock : stocksFromCsv.stockList){
                tableCreator.buildStockDisplayLine(stock);
            }
            tableCreator.createHorizontalLine(widthOfTable);
        }
    }
    public String buildCSVLine(){
        String separator = ",";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        StockRegistrant stockRegistrant = new StockRegistrant();
        Stock stockToCSV = stockRegistrant.registerStockData();
        return stockToCSV.getTickerCode()+separator+stockToCSV.getProductName()+separator+stockToCSV.getExchangedMarket().getShortName()+separator+stockToCSV.getSharesIssued()+separator+stockToCSV.getListedDate().format(dateTimeFormatter);
    }
}
