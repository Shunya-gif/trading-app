import java.util.List;
import java.util.ArrayList;

public class StockManager {
    private final List<Stock> stockList = new ArrayList<>();
    private static final int widthOfTable = 84;



    public void addStock(Stock stock) {
        stockList.add(stock);
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
}
