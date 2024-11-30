package trading_app.Trade;


import trading_app.TableCreator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TradeManager {
    public List<TradeData> tradeDataList = new ArrayList<>();
    private static final int widthOfTable = 117;
    public List<TradeData> getTradeDataList() {
        return tradeDataList;
    }
    public void addData(TradeData tradeData) {
        tradeDataList.add(tradeData);
    }
    public void sortByTradedDate(){
        tradeDataList.sort(Comparator.comparing(TradeData::getTradedDateTime).reversed());
    }
    public  void displayTradeData(){
        TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
        TradeManager dataFromCSV = tradeDataFileManager.readCsv();
        if(dataFromCSV == null){
            System.out.println("CSVファイルからの受取りに失敗。");
        }else{
            dataFromCSV.sortByTradedDate();
            TableCreator tableCreator = new TableCreator();
            tableCreator.createHorizontalLine(widthOfTable);
            tableCreator.createTradeDataColumns();
            tableCreator.createHorizontalLine(widthOfTable);
            for(TradeData tradeData : dataFromCSV.getTradeDataList()){
                tableCreator.buildTradeDataDisplayLine(tradeData);
            }
            tableCreator.createHorizontalLine(widthOfTable);
        }
    }

    public String buildCSVLine(){
        String separator = ",";
        TradeDataRegistrant tradeDataRegistrant = new TradeDataRegistrant();
        TradeData dataToCSV =  tradeDataRegistrant.registerTradeData();
        return dataToCSV.getTickerCode()+separator+
                dataToCSV.getProductName()+separator+
                dataToCSV.getSide()+separator+
                dataToCSV.getQuantity()+separator+
                dataToCSV.getUnitPrice()+separator+
                dataToCSV.getTradedDateTime()+separator+
                dataToCSV.getInputDateTime();
    }
}
