package trading_app.Trade;

import trading_app.Stock.StockManager;
import trading_app.TableCreator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class HoldingManager {
    int widthOfTable = 140;
    StockManager stockManager = new StockManager();
    TableCreator tableCreator = new TableCreator();
    public Map<String,Holding> calculateHolding (TradeManager trades){
        Map<String,Holding> holdings = new HashMap<>();
        for (TradeData tradeData : trades.getTradeDataList()){
            holdings.putIfAbsent(tradeData.getTickerCode(),new Holding());
            Holding holding = holdings.get(tradeData.getTickerCode());
            holding.updateHolding(tradeData);
        }
        return holdings;
    }
    public void displayHoldings(){
        TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
        MarketPriceReader marketPriceReader = new MarketPriceReader();
        TradeManager dataFromCsv = tradeDataFileManager.readCsv();
        Map <String,BigDecimal> marketPrices =  marketPriceReader.loadMarketData();
        if(dataFromCsv==null||marketPrices==null){
            System.out.println("CSVファイルからの受取に失敗");
        }else{
            Map<String,Holding> HoldingsToShow = calculateHolding(dataFromCsv);
            tableCreator.createHorizontalLine(widthOfTable);
            tableCreator.createHoldingDataColumns();
            tableCreator.createHorizontalLine(widthOfTable);
            HoldingsToShow.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach((entry) -> {
                        String tickerCode = entry.getKey();
                        Holding holdings = entry.getValue();
                        BigDecimal marketPrice = marketPrices.get(tickerCode);
                        holdings.setProductName(stockManager.searchStockFromCode(tickerCode).getProductName());
                        if(marketPrice!=null) {
                            BigDecimal valuation = holdings.calculateValuation(marketPrice);
                            BigDecimal unrealisedPNL = holdings.calculateUnrealizedPnL((marketPrice));
                            tableCreator.buildHoldingDisplayLine(tickerCode, holdings, valuation, unrealisedPNL);
                        }else{
                            tableCreator.buildNoMarketPricesHoldingDisplayLine(tickerCode,holdings);
                        }
                    });
            tableCreator.createHorizontalLine(widthOfTable);
        }
    }
}
