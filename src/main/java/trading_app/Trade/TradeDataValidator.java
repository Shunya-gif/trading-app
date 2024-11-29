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

    public boolean validateQuantity(long userInput) {
        return (1 <= userInput && userInput <= 999_999_999_999L);
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
}

