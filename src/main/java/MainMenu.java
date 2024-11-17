
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
                    "9.アプリケーションを終了する%n" +
                    ">");
            String userSelect = scanUserInput.nextLine();
            switch (userSelect){
                case "1" ->
                    {System.out.println("銘柄マスタ一覧表示が選択されました。");
                    StockFileManager stockFileManager = new StockFileManager();
                    stockFileManager.readCsv();
                    }
                case "2" -> System.out.println("銘柄マスタ新規登録が選択されました。");
                case "3" -> System.out.println("銘柄マスタ詳細表示が選択されました。");
                case "4" -> System.out.println("取引履歴一覧表示が選択されました。");
                case "5" -> System.out.println("取引データ新規登録が選択されました。");
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
