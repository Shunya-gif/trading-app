package trading_app;

import trading_app.Stock.*;
import trading_app.Trade.HoldingManager;
import trading_app.Trade.TradeDataFileManager;
import trading_app.Trade.TradeManager;

import java.util.Scanner;

public class MainMenu {
    static Scanner scanUserInput = new Scanner(System.in);

    public static void startMenu(){
        System.out.println("株式銘柄取引システムを開始します。");
        while(true){
            System.out.printf("利用するメニュー番号を入力してください。%n" +
                    "1.銘柄マスタ一覧表示%n" +
                    "2.銘柄マスタ新規登録%n" +
                    "3.銘柄マスタ詳細表示%n" +
                    "4.取引履歴一覧表示%n"+
                    "5.取引データ新規登録%n" +
                    "6.保有ポジション一覧表示%n" +
                    "9.アプリケーションを終了する%n" +
                    ">");
            String userSelect = scanUserInput.nextLine();
            switch (userSelect){
                case "1" ->
                    {System.out.println("銘柄マスタ一覧表示が選択されました。");
                    StockManager.displayStocks();
                    }
                case "2" -> {
                    System.out.println("銘柄マスタ新規登録が選択されました。");
                    StockFileManager stockFileManager = new StockFileManager();
                    stockFileManager.writeCSV();
                }
                case "3" -> {
                    System.out.println("銘柄マスタ詳細表示が選択されました。");
                    StockDiscloser stockDiscloser = new StockDiscloser();
                    stockDiscloser.showStockProperty();
                }
                case "4" -> {
                    System.out.println("取引履歴一覧表示が選択されました。");
                    TradeManager tradeManager = new TradeManager();
                    tradeManager.displayTradeData();
                }
                case "5" -> {
                    System.out.println("取引データ新規登録が選択されました。");
                    TradeDataFileManager tradeDataFileManager = new TradeDataFileManager();
                    tradeDataFileManager.writeCSV();
                }
                case "6" -> {
                    System.out.println("保有ポジション一覧表示が選択されました。");
                    HoldingManager holdingManager = new HoldingManager();
                    holdingManager.displayHoldings();
                }
                case "9" -> {
                    System.out.println("システムを終了します。ご利用ありがとうございました。");
                    return;
                }
                case ""-> System.out.println("何も入力されていません。");
                default -> System.out.println("無効な入力です。該当するメニュー番号を入力してください。");
            }
            System.out.println("---");
            System.out.println();
        }
    }
}
