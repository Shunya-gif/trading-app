import java.util.List;
import java.util.ArrayList;

public class StockManager {
    private List<Stock> stockList = new ArrayList<>();
    private final int widthOfTable = 3;

    public void addStock(Stock stock) {
        stockList.add(stock);
    }
}
