package jp.co.fullness.bdd.presentations.e2e.steps;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.presentations.e2e.commons.GoMenuCommonSteps;
import jp.co.fullness.bdd.util.ScenarioContext;
import jp.co.fullness.bdd.util.factories.ProductTableModelDataTableFactory;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * 演習-15:商品キーワード検索のシナリオとドライバを作成してテストする
 */
public class ProductSearchE2EStepdefs {
    // [終了]ボタンクリック共通ステップ
    private final GoMenuCommonSteps goMenuCommonSteps;
    // Product DataTable変換ファクトリ
    private final ProductTableModelDataTableFactory productTableModelDataTableFactory;
    // Playwright Pageオブジェクト
    private final Page page;

    /**
     * コンストラクタ
     * @param goMenuCommonSteps [終了]ボタンクリック共通ステップ
     * @param productTableModelDataTableFactory Product DataTable変換ファクトリ
     * @param scenarioContext シナリオコンテキスト
     */
    public ProductSearchE2EStepdefs(
            GoMenuCommonSteps goMenuCommonSteps,
            ProductTableModelDataTableFactory productTableModelDataTableFactory,
            ScenarioContext scenarioContext) {
        this.goMenuCommonSteps = goMenuCommonSteps;
        this.productTableModelDataTableFactory = productTableModelDataTableFactory;
        this.page = (Page) scenarioContext.get("page");
    }

    /**
     * 検索画面が正しく表示される
     */
    @Given("ユーザは検索画面を表示するリクエストを送信する {string}")
    public void ユーザは検索画面を表示するリクエストを送信する(String url) {
        page.navigate(url);
    }

    @When("検索画面が表示される {string}")
    public void 検索画面が表示される(String title) {
        // ページが完全に読み込まれるまで待機する
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        // ページが読み込まれ、タイトルが正しいか確認
        String pageTitle = page.title();
        Assertions.assertTrue(pageTitle.contains(title));
    }

    @Then("キーワード入力項目と[検索]ボタンが表示されたことを評価する")
    public void キーワード入力項目と検索ボタンが表示されたことを評価する() {
        // キーワード入力項目が表示されていることを確認
        Assertions.assertTrue(page.isVisible("#keyword"));
        // [検索]ボタンが表示されていることを確認
        Assertions.assertTrue(page.isVisible("button[name='search']"));
    }

    /**
     * 検索結果が正しく表示される
     */
    @When("ユーザーがキーワードを入力し [検索] ボタンをクリックする {string}")
    public void ユーザーがキーワードを入力し検索ボタンをクリックする(String keyword) {
        // キーワードを入力
        page.fill("#keyword", keyword);
        // 検索ボタンをクリック
        page.click("button[name='search']");
        // ページが完全に読み込まれるまで待機する
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Then("検索結果として以下の内容が表示されることを評価する")
    public void 検索結果として以下の内容が表示されることを評価する(DataTable dataTable)
    throws Exception {
        var results = productTableModelDataTableFactory.convertList(dataTable);
        // 検索結果が表示されていることを確認する
        Assertions.assertTrue(page.isVisible("table.table"));
        // 実際の検索結果を評価する
        List<ElementHandle> rows = page.querySelectorAll("table.table tbody tr");
        for (int i = 0; i < results.size() ; i++){
            ElementHandle row = rows.get(i); // 1行分の値を取得
            // 商品Idを取得する
            String actualId = row.querySelector("td:nth-child(1)").innerText();
            // 商品名を取得する
            String actualName = row.querySelector("td:nth-child(2) a").innerText();
            // 商品単価を取得する
            String actualPrice = row.querySelector("td:nth-child(3)").innerText();
            // 商品Idが一致することを確認
            Assertions. assertEquals(actualId, results.get(i).getObjId());
            // 商品名が一致することを確認
            Assertions.assertEquals(actualName, results.get(i).getName());
            // 単価が一致することを確認
            Assertions.assertEquals(Integer.parseInt(actualPrice),
                    results.get(i).getPrice());
        }
    }

    /**
     * キーワードを入力ぜずに[検索]ボタンをクリックするとエラーメッセージが表示される
     */
    @When("ユーザーはキーワードを入力せずに [検索] ボタンをクリックする")
    public void ユーザーはキーワードを入力せずに検索ボタンをクリックする() {
        // キーワードを入力せずに検索ボタンをクリック
        page.click("button[name='search']");
    }

    @When("ユーザーはキーワードを入力し [検索] ボタンをクリックする {string}")
    public void ユーザーはキーワードを入力し検索ボタンをクリックする(String keyword) {
        // キーワードを入力
        page.fill("#keyword", keyword);
        // 検索ボタンをクリック
        page.click("button[name='search']");
        // ページが完全に読み込まれるまで待機する
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Then("エラーメッセージが表示されたことを評価する {string}")
    public void エラーメッセージが表示されたことを評価する(String expectedErrorMessage) {
        // エラーメッセージが表示されていることを確認
        String actualErrorMessage = page.textContent(
                "div[style='color:red;'] span");
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
