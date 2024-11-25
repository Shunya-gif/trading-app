package trading_app.Trade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
    public BigDecimal validateUnitPrice(BigDecimal userInput){
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(userInput);
    }
}
