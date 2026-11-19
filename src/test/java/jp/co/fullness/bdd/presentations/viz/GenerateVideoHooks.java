package jp.co.fullness.bdd.presentations.viz;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import jp.co.fullness.bdd.util.ScenarioContext;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 演習-19:動画生成フックを実装し、商品変更をテストする
 */
public class GenerateVideoHooks {

    private final ScenarioContext scenarioContext;

    /**
     * シナリオコンテキスト
     * @param scenarioContext
     */
    public GenerateVideoHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    private Scenario scenario;
    private Browser browser;        // ブラウザ
    private BrowserContext context; // ブラウザコンテキスト
    private Page page;              // ページ

    @Before("@Video")
    public void setupVideo(Scenario scenario) {
        this.scenario = scenario;
        // Playwrightのセットアップとブラウザ起動
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true) // ヘッドレスモード
                        .setSlowMo(1000));  // 1秒のスローモーション
        // 動画オプション付きでブラウザコンテキストを作成
        Path videoDir = Paths.get("src/test/resources/"+scenario.getName());
        Browser.NewContextOptions options = new Browser.NewContextOptions()
                .setRecordVideoDir(videoDir)
                .setRecordVideoSize(1280, 720);  // 動画の解像度を指定
        context = browser.newContext(options);
        page = context.newPage();
        scenarioContext.set("page", page);
    }

    @After("@Video")
    public void teardownVideo() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
    }
}
