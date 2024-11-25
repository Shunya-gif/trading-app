package trading_app.Stock;

public class StockValidator {
    /**
     *
     * @param userInput　の一,三文字目が0-9の数字かつ二,四文字目が0~9の数字またはBEIOQVZ
     *                 を除く19種類のアルファベットのとき
     * @return  Trueを返す。
     * */
    public boolean validateTickerCode(String userInput){
        return userInput.matches("^[0-9][ACDFGHJ-NPR-UXY0-9][0-9][ACDFGHJ-NPR-UXY0-9]$");
    }

    public boolean validateProductName(String userInput){
        return userInput.matches("^[A-Za-z0-9()][A-Za-z0-9 .()]*$");
    }

    public boolean validateSharesIssued(long userInput){
        return (1<=userInput&&userInput<=999_999_999_999L);
    }

}
