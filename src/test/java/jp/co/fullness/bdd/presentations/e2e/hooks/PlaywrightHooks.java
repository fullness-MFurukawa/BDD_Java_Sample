package jp.co.fullness.bdd.presentations.e2e.hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import jp.co.fullness.bdd.util.ScenarioContext;

/**
 * Playwrightを利用したE2Eテストのフック
 * @author Fullness,Inc.
 * @since 2025-02-24
 * @version 1.0.1
 */
public class PlaywrightHooks {
    // シナリオコンテキスト
    private ScenarioContext scenarioContext;

    /**
     * コンストラクタ
     * @param scenarioContext シナリオコンテキスト
     */
    public PlaywrightHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    private Browser browser;        // ブラウザ
    private BrowserContext context; // ブラウザコンテキスト
    private Page page;              // ページ

    /**
     * E2Eの前処理
     * Browser、Pageの初期化
     * @throws Exception
     */
    @Before("@E2E")
    public void setupPlaywright() throws Exception{

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

    /**
     * E2Eの後処理
     * Browser、Pageのクローズ
     * @throws Exception
     */
    @After("@E2E")
    public void teardownPlaywright() throws Exception {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
    }
}
