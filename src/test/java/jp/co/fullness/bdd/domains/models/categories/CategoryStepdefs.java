package jp.co.fullness.bdd.domains.models.categories;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.commons.EqualsCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.HashCodeCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.ValidateExceptionCommonSteps;
import jp.co.fullness.bdd.exceptions.ValidateException;
import jp.co.fullness.bdd.util.ScenarioContext;
import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.stream.Stream;

/**
 * エンティティ:商品カテゴリの単体テストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-22
 * @version 1.0.1
 */
public class CategoryStepdefs {

    // ValidateException評価共通ステップ
    private final ValidateExceptionCommonSteps validateExceptionCommonSteps;
    // equals()メソッドを評価する共通ステップ
    private final EqualsCommonSteps equalsCommonSteps;
    // hashCode()メソッドを評価する共通ステップ
    private final HashCodeCommonSteps hashCodeCommonSteps;
    // シナリオコンテキスト
    private final ScenarioContext scenarioContext;

    /**
     * コンストラクタ
     * @param validateExceptionCommonSteps ValidateException評価共通ステップ
     * @param equalsCommonSteps equals()メソッドを評価する共通ステップ
     * @param hashCodeCommonSteps hashCode()メソッドを評価する共通ステップ
     * @param scenarioContext シナリオコンテキスト
     */
    public CategoryStepdefs(
            ValidateExceptionCommonSteps validateExceptionCommonSteps,
            EqualsCommonSteps equalsCommonSteps,
            HashCodeCommonSteps hashCodeCommonSteps,
            ScenarioContext scenarioContext) {
        this.validateExceptionCommonSteps = validateExceptionCommonSteps;
        this.equalsCommonSteps = equalsCommonSteps;
        this.hashCodeCommonSteps = hashCodeCommonSteps;
        this.scenarioContext = scenarioContext;
    }

    private String categoryId;
    private String categoryName;
    private Category category;
    /**
     * インスタンス生成の評価
     */
    @Given("新しい商品カテゴリを用意する {string}")
    public void 新しい商品カテゴリを用意する(String name) {
        categoryName = null;
        if (!name.equals("null")){
            categoryName = name;
        }
    }

    @When("新しい商品カテゴリを生成する")
    public void 新しい商品カテゴリを生成する() {
        try{
            category = new Category(categoryName);
        }catch(ValidateException e){
            // 共通ステップに例外を渡して評価する
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("新しい商品カテゴリIdを持った商品カテゴリが生成されていることを評価する {string}")
    public void 新しい商品カテゴリidを持った商品カテゴリが生成されていることを評価する(
            String expectedName) {
        Assertions.assertNotNull(category.getCategoryId());
        Assertions.assertEquals(category.getCategoryName().getValue() , expectedName);
    }

    @Given("既存商品カテゴリを用意する")
    public void 既存商品カテゴリを用意する(DataTable dataTable) {
        // DataTableをMap<String, String>のリストに変換し、Streamに変換する
        Stream<Map<String, String>> dataStream =
                dataTable.asMaps(String.class, String.class).stream();
        // Streamの最初の要素を取得し、その要素が存在する場合に値を取り出す
        dataStream.findFirst().ifPresent(dataMap -> {
            // idフィールドに値を取得する
            if (! dataMap.get("id").equals("null")){
                categoryId = dataMap.get("id");
            }
            // nameフィールドに値を取得する
            if (! dataMap.get("name").equals("null")){
                categoryName = dataMap.get("name");
            }
        });
    }

    @When("既存の商品カテゴリを生成する")
    public void 既存の商品カテゴリを生成する() {
        try{
            category = new Category(categoryId , categoryName);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品カテゴリIdと商品カテゴリ名は等価であることを評価する")
    public void 商品カテゴリidと商品カテゴリ名は等価であることを評価する(
            DataTable dataTable) {
        // DataTableをMap<String, String>のリストに変換し、Streamに変換する
        Stream<Map<String, String>> dataStream =
                dataTable.asMaps(String.class, String.class).stream();
        // Streamの最初の要素を取得し、その要素が存在する場合に値を取り出す
        dataStream.findFirst().ifPresent(dataMap -> {
            Assertions.assertEquals(dataMap.get("id") ,
                    category.getCategoryId().getValue());
            Assertions.assertEquals(dataMap.get("name") ,
                    category.getCategoryName().getValue());
        });
    }

    /**
     * ChangeName()メソッドの評価
     */
    @When("商品カテゴリ名を変更する {string}")
    public void 商品カテゴリ名を変更する(String name) {
        category = new Category(categoryName);
        try{
            if (! name.equals("null")){
                category.changeCategoryName(name);
            }else{
                category.changeCategoryName(null);
            }
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品カテゴリ名が変更されていることを検証する {string}")
    public void 商品カテゴリ名が変更されていることを検証する(String expectedName) {
        Assertions.assertEquals(expectedName ,
                category.getCategoryName().getValue());
    }

    /**
     * equals()メソッドの評価
     * hashCode()メソッドの評価
     */
    @Given("比較対象の商品カテゴリを用意する {string} {string} {string} {string}")
    public void 比較対象の商品カテゴリを用意する(
            String id1, String name1, String id2, String name2) {
        var objectA = new Category(id1,name1);
        var objectB = new Category(id2,name2);
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }

    /**
     * clone()メソッドの評価
     */
    private Category cloneCategory;
    @When("新しい商品カテゴリの複製を生成する")
    public void 新しい商品カテゴリの複製を生成する() {
        category = new Category(categoryName);
        try{
            cloneCategory = (Category)category.clone();
        }catch(CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("複製されたことを検証する")
    public void 複製されたことを検証する() {
        Assertions.assertEquals(
                category.getCategoryId().getValue(),
                cloneCategory.getCategoryId().getValue());
        Assertions.assertEquals(
                category.getCategoryName().getValue(),
                cloneCategory.getCategoryName().getValue());
    }

    @When("既存商品カテゴリの複製を生成する")
    public void 既存商品カテゴリの複製を生成する() {
        category = new Category(categoryId,categoryName);
        try{
            cloneCategory = (Category)category.clone();
        }catch(CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
