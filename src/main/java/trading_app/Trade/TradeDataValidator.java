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
    public void isPastDate(LocalDateTime userInput){
        if(userInput.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("未来の日時が入力されています。");
        }
    }
    public void isWeekDay(LocalDateTime userInput){
        if(userInput.getDayOfWeek()==DayOfWeek.SATURDAY||userInput.getDayOfWeek()==DayOfWeek.SUNDAY){
            throw new IllegalArgumentException("土日が入力されています。");
        }
    }
    public void marketIsOpen(LocalDateTime userInput){
        LocalTime marketOpen = LocalTime.of(9,0).minusNanos(1);
        LocalTime marketClose = LocalTime.of(15,30).plusNanos(1);
        if(userInput.toLocalTime().isBefore(marketOpen)||userInput.toLocalTime().isAfter(marketClose)){
            throw new IllegalArgumentException("日本取引所の営業時間外です。");
        }
    }
    public void tradeIsOver(LocalDateTime userInput ,TradeData tradeData){
        TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
        HoldingManager holdingManager = new HoldingManager();
        Holding holding = holdingManager.calculateHolding(tradeDataFileManager.readCsv()).get(tradeData.getTickerCode());
        if(holding!=null&& userInput.isBefore(holding.getLatestTradeDateTime())){
            throw new IllegalArgumentException(holding.getLatestTradeDateTime()+"以前の取引は入力できません");
        }
    }
}

