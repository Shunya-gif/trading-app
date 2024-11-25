package trading_app.Stock;

import java.util.Scanner;

public class StockDiscloser {
    StockManager stockManager = new StockManager();
    StockValidator stockValidator = new StockValidator();
    public void showStockProperty(){
        Scanner scanner = new Scanner(System.in);
        String codeUserInput;
        while (true) {
            try {
                System.out.printf("検索したい銘柄のコードを入力してください。%n" +
                        "1,3文字目：0~9の数字%n" +
                        "2,4文字目：0-9の数字またはB,E,I,O,V,Q,Zを除いた19種類の英大文字%n>");
                codeUserInput = scanner.nextLine().replace(",", "");
                if (stockValidator.validateTickerCode(codeUserInput)) {
                    if (stockManager.isAlreadyRegistered(codeUserInput)) {
                        Stock stock = stockManager.searchStockFromCode(codeUserInput);
                        System.out.printf("""
                                        銘柄コード: %s
                                        　　銘柄名: %s
                                        　　上場日: %tY/%<tm/%<td
                                        　上場市場: %s
                                　　発行済み株式総数: %,d株
                                """, stock.getTickerCode(), stock.getProductName(), stock.getListedDate(), stock.getExchangedMarket().getJapannedName(), stock.getSharesIssued());
                        return;
                    } else {
                        System.out.println("銘柄コード" + codeUserInput + "は未登録です。");
                    }
                } else {
                    System.out.println(codeUserInput + "は銘柄コードの要件を満たしていません。");
                }
            }catch (NullPointerException e){
                System.out.println(e.getLocalizedMessage());
            }
        }

    }
}

