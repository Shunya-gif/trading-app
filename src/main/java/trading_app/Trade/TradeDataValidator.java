package trading_app.Trade;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TradeDataValidator {
    /**
     * @param userInput 　の一,三文字目が0-9の数字かつ二,四文字目が0~9の数字またはBEIOQVZ
     *                  を除く19種類のアルファベットのとき
     * @return Trueを返す。
     */
    public boolean validateTickerCode(String userInput) {
        return userInput.matches("^[0-9][ACDFGHJ-NPR-UXY0-9][0-9][ACDFGHJ-NPR-UXY0-9]$");
    }
    public boolean canSellStock(TradeData trade ){
        HoldingManager holdingManager = new HoldingManager();
        TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
        Holding holding = holdingManager.calculateHolding(tradeDataFileManager.readCsv()).get(trade.getTickerCode());
        return  ( holding!=null&& holding.getQuantity() != 0 ) || trade.getSide() != Side.Sell;
    }

    public boolean validateQuantity(long userInput) {
        return (1 <= userInput && userInput <= 999_999_999_999L);
    }
    public boolean quantityWillBePlus(long userInput,TradeData tradeData){
        if(tradeData.getSide()==Side.Sell){
            HoldingManager holdingManager = new HoldingManager();
            TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
            Holding holding = holdingManager.calculateHolding(tradeDataFileManager.readCsv()).get(tradeData.getTickerCode());
            return holding != null && userInput <= holding.getQuantity();
        }else return true;
    }

    public boolean isHundredfold(long quantityUserInput) {
        return (quantityUserInput % 100 == 0);
    }

    public boolean validateUnitPrice(String userInput){
        BigDecimal registerToCSV = new BigDecimal(userInput);
        return registerToCSV.signum() >0;
    }
    public boolean isPastDate(LocalDateTime userInput){
        return userInput.isBefore(LocalDateTime.now());
    }
    public boolean isWeekDay(LocalDateTime userInput){
        return !(userInput.getDayOfWeek().equals(DayOfWeek.SATURDAY)||userInput.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }
    public boolean marketIsOpen(LocalDateTime userInput){
        LocalTime marketOpen = LocalTime.of(9,0).minusNanos(1);
        LocalTime marketClose = LocalTime.of(15,30).plusNanos(1);
        return userInput.toLocalTime().isAfter(marketOpen)&&userInput.toLocalTime().isBefore(marketClose);
    }
    public boolean tradeIsOver(LocalDateTime userInput ,TradeData tradeData){
        TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
        HoldingManager holdingManager = new HoldingManager();
        Holding holding = holdingManager.calculateHolding(tradeDataFileManager.readCsv()).get(tradeData.getTickerCode());
        return holding == null || !userInput.isBefore(holding.getLatestTradeDateTime());
    }
}

