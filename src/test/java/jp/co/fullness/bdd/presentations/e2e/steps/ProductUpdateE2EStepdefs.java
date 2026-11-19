package jp.co.fullness.bdd.presentations.e2e.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.presentations.e2e.commons.GoMenuCommonSteps;
import jp.co.fullness.bdd.util.ScenarioContext;
import jp.co.fullness.bdd.util.factories.ProductDataTableFactory;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * 演習-17:商品変更のシナリオとテストドライバを作成してテストする
 */
public class ProductUpdateE2EStepdefs {
    // [終了]ボタンクリック共通ステップ
    private final GoMenuCommonSteps goMenuCommonSteps;
    // DataTableからProductに変換するファクトリ
    ProductDataTableFactory productDataTableFactory;
    // Playwright Pageオブジェクト
    private final Page page;

    /**
     * コンストラクタ
     * @param goMenuCommonSteps [終了]ボタンクリック共通ステップ
     * @param productDataTableFactory DataTableからProductに変換するファクトリ
     * @param scenarioContext シナリオコンテキスト
     */
    public ProductUpdateE2EStepdefs(
            GoMenuCommonSteps goMenuCommonSteps,
            ProductDataTableFactory productDataTableFactory,
            ScenarioContext scenarioContext) {
        this.goMenuCommonSteps = goMenuCommonSteps;
        this.productDataTableFactory = productDataTableFactory;
        this.page = (Page) scenarioContext.get("page");
    }

    /**
     * -変更商品を選択する
     */
    @Given("ユーザーは商品検索画面を開く {string}")
    public void ユーザーは商品検索画面を開く(String url) {
        page.navigate(url);
    }

    @And("検索画面が表示される")
    public void 検索画面が表示される() {
        // 画面表示が完了するまで待機する
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @And("ユーザーがキーワードを入力して検索する {string}")
    public void ユーザーがキーワードを入力して検索する(String keyword) {
        // キーワードを入力し、[検索]ボタンをクリックする
        page.fill("#keyword", keyword);
        page.click("button[type='submit'][name='search']");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @And("ユーザーが変更対象の商品を選択する {string}")
    public void ユーザーが変更対象の商品を選択する(String productName) {
        // 表示された商品名のリンクを取得してクリックする
        var productLink = page.waitForSelector(
                "a:has-text('" + productName + "')");
        productLink.click();
    }

    /**
     * - 変更画面が正しく表示される
     */
    @When("変更画面が表示される")
    public void 変更画面が表示される() {
        // 画面表示が完了するまで待機する
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Then("変更画面の表示内容を評価する")
    public void 変更画面の表示内容を評価する(DataTable dataTable)
    throws Exception{
        // タイトルを評価する
        var pageTitle = page.innerText("h2");
        Assertions.assertEquals("商品変更", pageTitle);
        // 期待値をProductに変換する
        var expectedProduct = productDataTableFactory.convert(dataTable);
        // 表示された値を取得する
        var productId = page.getAttribute("#id", "value");
        var productName = page.getAttribute("#name", "value");
        var productPrice = page.getAttribute("#price", "value");
        // 表示された値を評価する
        Assertions.assertEquals(expectedProduct.getProductId().getValue(),
                productId);
        Assertions.assertEquals(expectedProduct.getProductName().getValue(),
                productName);
        Assertions.assertEquals(expectedProduct.getProductPrice().getValue(),
                Integer.parseInt(productPrice));
    }

    /**
     * - [変更]ボタンをクリックすると変更完了画面に遷移する
     */
    @When("ユーザは単価を変更して、[変更]ボタンをクリックする {string}")
    public void ユーザは単価を変更して変更ボタンをクリックする(String newPrice) {
        // 単価を変更する
        page.fill("#price", newPrice);
        // 変更ボタンをクリックする
        page.click("button[type='submit'][name='update']");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Then("変更完了画面が表示されることを評価する {string}")
    public void 変更完了画面が表示されることを評価する(String expectedMessage) {
        // 変更完了画面の表示を評価する
        var pageTitle = page.innerText("h2");
        Assertions.assertEquals("商品変更完了", pageTitle);

        // 商品情報が表示されているか確認
        String actualMessage = page.textContent("h4:nth-of-type(2)");
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    /**
     * - 商品名と単価が未入力で[変更]ボタンをクリックするとエラーメッセージが表示される
     * - 商品名が未入力で[変更]ボタンをクリックするとエラーメッセージが表示される
     * - 単価が未入力で[変更]ボタンをクリックするとエラーメッセージが表示される
     */
    @When("ユーザーは商品名と単価をクリアする")
    public void ユーザーは商品名と単価をクリアする() {
        // 商品名と単価をクリアする
        page.fill("#name", "");
        page.fill("#price", "");
    }

    @When("ユーザーは商品名をクリアする")
    public void ユーザーは商品名をクリアする() {
        // 商品名をクリアする
        page.fill("#name", "");
    }

    @When("ユーザーは単価をクリアする")
    public void ユーザーは単価をクリアする() {
        // 単価をクリアする
        page.fill("#price", "");
    }

    @And("ユーザーは[変更]ボタンをクリックする")
    public void ユーザーは変更ボタンをクリックする() {
        // 変更ボタンをクリックする
        page.click("button[type='submit'][name='update']");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Then("エラーメッセージが表示されることを評価する")
    public void エラーメッセージが表示されることを評価する(DataTable dataTable)
    throws Exception{
        // DataTableから期待するエラーメッセージを取得する
        List<String> expectedMessages = dataTable.asList();
        // 画面に表示されているエラーメッセージをリストとして取得する
        List<String> actualMessages = page.locator("ul li").allInnerTexts();
        // 各期待メッセージが表示されているか確認
        for (String expectedMessage : expectedMessages) {
            Assertions.assertTrue(
                    actualMessages.contains(expectedMessage), expectedMessage);
        }
    }
}
