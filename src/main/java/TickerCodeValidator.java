public class TickerCodeValidator {
    /**
     *
     * @param userInput　の一,三文字目が0-9の数字かつ二,四文字目が0~9の数字またはBEIOQVZ
     *                 を除く19種類のアルファベットのとき
     * @return  Trueを返す。
     * */
    public boolean validateTickerCode(String userInput){
        return userInput.matches("^[0-9][ACDFGHJ-NPR-UXY0-9][0-9][ACDFGHJ-NPR-UXY0-9]$");
    }

}
