package jp.co.fullness.bdd.presentations.e2e.commons;

import com.microsoft.playwright.Page;
import jp.co.fullness.bdd.util.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

/**
 * メニュー画面遷移テスト共通ステップ
 * @author Fullness,Inc.
 * @since 2025-02-24
 * @version 1.0.1
 */
public class GoMenuCommonSteps {
    // Pageインターフェイス
    private final Page page;

    /**
     * コンストラクタ
     * @param scenarioContext シナリオコンテキスト
     */
    public GoMenuCommonSteps(ScenarioContext scenarioContext) {
        // シナリオコンテキストからPageを取得する
        page = (Page) scenarioContext.get("page");
    }

    @Given("テスト対象のページリクエストを送信する {string}")
    public void テスト対象のページリクエストを送信する(String url) {
        // アクセスURLを設定する
        page.navigate(url);
    }
    @When("[終了]ボタンをクリックする")
    public void 終了ボタンをクリックする() {
        // [終了]ボタンをクリックする
        page.click("button[name='end']");
    }
    @Then("メニュー画面が表示されたことを評価する")
    public void メニュー画面が表示されたことを評価する() {
        // メニュー画面が表示されたことを確認
        String currentUrl = page.url();
        Assertions.assertEquals("http://localhost:8080/exercise/menu",
                currentUrl, "メニュー画面のURLが正しいことを確認");
        // タイトルを確認（メニュー画面のタイトルが"メニュー"であることを確認）
        String title = page.title();
        Assertions.assertTrue(title.contains("トップ"),
                "メニュー画面のタイトルを確認");
    }
}
