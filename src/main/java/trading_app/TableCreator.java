package trading_app;

import trading_app.Stock.Stock;
import trading_app.Trade.TradeData;

import java.text.DecimalFormat;

public class TableCreator {
    public void createHorizontalLine(int widthOfTable){
        System.out.print("|");
        for(int i =0;i<widthOfTable;i++){
            System.out.print("=");
        }
        System.out.println("|");
    }
    public String ellipseProductName(String productName){
        if(productName.length()<=28){
            return productName;
        }else{
            return productName.substring(0,27)+"...";
        }
    }
    public void createStockColumns(){
        System.out.printf("| %4s | %-31s | %-8s | %16s | %10s |%n","Code","Product Name","Market","Shares Issued","Listed Date");
    }
    public  void buildStockDisplayLine(Stock stock){
        System.out.printf("| %4s | %-31s | %-8s | %,16d | %tY/%<tm/%<td  |%n"
                ,stock.getTickerCode()
                ,ellipseProductName(stock.getProductName())
                ,stock.getExchangedMarket()
                ,stock.getSharesIssued()
                ,stock.getListedDate());
    }
    public void createTradeDataColumns(){
        System.out.printf("| %4s | %-31s | %-4s | %16s | %10s | %16s | %16s |%n","Code","Product Name","Side","Quantity","Unit Price","Traded Date","InputDate");
    }
    public void buildTradeDataDisplayLine(TradeData tradeData){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        System.out.printf("| %4s | %-31s | %-4s | %,16d | %10s | %tY/%<tm/%<td %<tH:%<tM | %tY/%<tm/%<td %<tH:%<tM |%n"
                ,tradeData.getTickerCode()
                ,tradeData.getProductName()
                ,tradeData.getSide()
                ,tradeData.getQuantity()
                ,decimalFormat.format(tradeData.getUnitPrice())
                ,tradeData.getTradedDateTime()
                ,tradeData.getInputDateTime());
    }
}

