package jp.co.fullness.bdd.presentations.viz;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import jp.co.fullness.bdd.util.ScenarioContext;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 演習-18:PDF生成フックを実装し、商品キーワード検索をテストする
 */
public class GeneratePDFHooks {
    private final ScenarioContext scenarioContext;
    /**
     * コンストラクタ
     * @param scenarioContext シナリオコンテキスト
     */
    public GeneratePDFHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    private Scenario scenario;
    private Browser browser;        // ブラウザ
    private BrowserContext context; // ブラウザコンテキスト
    private Page page;              // ページ
    @Before(value = "@PDF")
    public void pdfSetup(Scenario scenario){
        this.scenario = scenario;
        // Playwrightの初期化
        Playwright playwright = Playwright.create();
        // ヘッドレスモードでChromiumブラウザを起動
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true));
        // ブラウザコンテキストの生成
        context = browser.newContext();
        // 新しいページを作成
        page = context.newPage();
        // ScenarioContextにPageを格納する
        scenarioContext.set("page" , page);
    }

    @After(value = "@PDF")
    public void pdfTearDown(){
        // シナリオ名をファイル名に利用してPDFを出力
        savePdf(page , scenario.getName());
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
    }

    // PDFを保存するメソッド
    public void savePdf(Page page, String filename) {
        try {
            // 確実にページがロードされているか確認
            if (page.url().isEmpty()) {
                System.out.println("Page URL is empty. Navigating to a valid page...");
            }
            // src/test/resourcesディレクトリにファイルを保存するパスを指定
            Path pdfPath = Paths.get("src/test/resources/" + filename + "_result.pdf");

            // PDFオプションの設定
            Page.PdfOptions pdfOptions = new Page.PdfOptions().setPath(pdfPath);

            // PDFファイルを生成
            page.pdf(pdfOptions);
            System.out.println("PDF generated: " + pdfPath.toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
