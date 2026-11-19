package jp.co.fullness.bdd.presentations.e2e.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.presentations.e2e.commons.GoMenuCommonSteps;
import jp.co.fullness.bdd.util.ScenarioContext;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * ユースケース:商品登録のE2E(外部品質)テストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-24
 * @version 1.0.1
 */
public class ProductRegisterE2EStepdefs {

    // [終了]ボタンクリック共通ステップ
    private final GoMenuCommonSteps goMenuCommonSteps;
    // Playwright Pageオブジェクト
    private final Page page;

    /**
     * コンストラクタ
     * @param goMenuCommonSteps [終了]ボタンクリック共通ステップ
     * @param scenarioContext シナリオコンテキスト
     */
    public ProductRegisterE2EStepdefs(
            GoMenuCommonSteps goMenuCommonSteps,
            ScenarioContext scenarioContext) {
        this.goMenuCommonSteps = goMenuCommonSteps;
        this.page = (Page) scenarioContext.get("page");
    }

    /**
     * テスト対象のページへアクセス
     */
    @Given("ユーザは商品登録画面を表示するリクエストを送信する {string}")
    public void ユーザは商品登録画面を表示するリクエストを送信する(String url) {
        page.navigate(url);
    }
    /**
     * - 登録画面が正しく表示される
     */
    @When("商品登録画面が表示される")
    public void 商品登録画面が表示される() {
        // 商品登録画面が表示されたことを確認する
        Assertions.assertNotNull(page);
    }

    @Then("入力に必要な項目が表示されていることを評価する")
    public void 入力に必要な項目が表示されていることを評価する(DataTable dataTable) {
        // 表示項目の評価
        var data = dataTable.asMap();
        // ページタイトルが正しいことを確認"
        Assertions.assertEquals(page.title(), data.get("pageTitle"));
        // 商品登録のタイトルが正しいことを確認"
        Assertions.assertEquals(page.locator("h2").innerText(),data.get("titleText"));
        // 商品名入力フィールドのプレースホルダが正しいことを確認
        Assertions.assertEquals(
                page.locator("input#name").getAttribute("placeholder"),
                data.get("productName_placeholder"));
        // 単価入力フィールドのプレースホルダが正しいことを確認
        Assertions.assertEquals(
                page.locator("input#price").getAttribute("placeholder"),
                data.get("productPrice_placeholder"));
        // [登録]ボタンのラベルが正しいことを確認
        Assertions.assertEquals(
                page.locator("button[name='register']").innerText(),
                data.get("register_btn"));
        // [終了]ボタンのラベルが正しいことを確認
        Assertions.assertEquals(
                page.locator("button[name='end']").innerText(),
                data.get("end_btn"));
        // 商品カテゴリ選択肢が正しいことを確認
        String[] expectedCategories = data.get("category_options").split(",");
        for (int i = 0; i < expectedCategories.length; i++) {
            Assertions.assertEquals(
                    page.locator("select#categoryId option")
                            .nth(i).innerText(), expectedCategories[i]);
        }
    }

    @When("ユーザは未登録の商品名と単価入力し、カテゴリを選択して[登録]ボタンをクリックする {string} {string} {string}")
    public void ユーザは未登録の商品名と単価入力しカテゴリを選択して登録ボタンをクリックする(
            String name, String price, String category) {
        // 商品名を入力する
        page.fill("input#name", name);
        // 単価を入力する
        page.fill("input#price", price);
        // カテゴリを選択する（カテゴリ名が「文房具」などの場合、そのvalueを使用）
        page.selectOption("select#categoryId",
                new SelectOption().setLabel(category));
        // [登録]ボタンをクリックする
        page.click("button[name='register']");
    }

    @Then("登録完了画面が表示されることを確認する")
    public void 登録完了画面が表示されることを確認する() {
        // ページタイトルが正しいか確認
        String title = page.title();
        Assertions.assertEquals("商品登録完了", title);
    }

    @And("登録完了画面にボールペンと単価が表示されることを評価する {string}")
    public void 登録完了画面にボールペンと単価が表示されることを評価する(String expectedMessage) {
        // 商品情報が正しく表示されていることを確認
        String actualMessage = page.textContent("h4:nth-of-type(2)");
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    /**
     * - 未入力(バリデーション)メッセージが正しく表示される
     */
    @When("ユーザは何も入力せずに[登録]ボタンをクリックする")
    public void ユーザは何も入力せずに登録ボタンをクリックする() {
        // [登録]ボタンをクリックする
        page.click("button[name='register']");
    }

    @When("ユーザは単価を入力して商品カテゴリを選択たら[登録]ボタンをクリックする {string} {string}")
    public void ユーザは単価を入力して商品カテゴリを選択たら登録ボタンをクリックする(
            String price, String category) {
        // 単価を入力する
        page.fill("input#price", price);
        // 商品カテゴリを選択する（カテゴリ名が「文房具」などの場合valueを使用）
        page.selectOption("select#categoryId",
                new SelectOption().setLabel(category));
        // [登録]ボタンをクリックする
        page.click("button[name='register']");
    }

    @When("商品名を入力して商品カテゴリを選択たら[登録]ボタンをクリックする {string} {string}")
    public void 商品名を入力して商品カテゴリを選択たら登録ボタンをクリックする(
            String name, String category) {
        // 商品名を入力する
        page.fill("input#name", name);
        // 商品カテゴリを選択する（カテゴリ名が「文房具」などの場合valueを使用）
        page.selectOption("select#categoryId",
                new SelectOption().setLabel(category));
        // [登録]ボタンをクリックする
        page.click("button[name='register']");
    }
    @Then("未入力項目があることを表すエラーメッセージが表示されることを評価する")
    public void 未入力項目があることを表すエラーメッセージが表示されることを評価する(
            DataTable dataTable) {
        // DataTableから期待するエラーメッセージを取得
        List<String> expectedMessages = dataTable.asList();
        // 画面に表示されているエラーメッセージをリストとして取得
        List<String> actualMessages = page.locator("ul li").allInnerTexts();
        // 各期待メッセージが表示されているか確認
        for (String expectedMessage : expectedMessages) {
            Assertions.assertTrue(
                    actualMessages.contains(expectedMessage), expectedMessage);
        }
    }
}
