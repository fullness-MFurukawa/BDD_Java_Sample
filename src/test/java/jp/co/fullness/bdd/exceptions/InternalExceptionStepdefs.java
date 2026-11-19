package jp.co.fullness.bdd.exceptions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.categories.CategoryId;
import jp.co.fullness.bdd.domains.models.categories.CategoryRepository;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductId;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.domains.models.products.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import static org.mockito.Mockito.doThrow;

/**
 * Repositoryのメソッドに対するInternalExceptionをシミュレートし、
 * 例外処理の挙動を検証するための単体テストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class InternalExceptionStepdefs {
    // CategoryRepository をモック化して、例外をスローするように設定する
    private final CategoryRepository categoryRepository
            = Mockito.mock(CategoryRepository.class);

    // テスト対象メソッドからの例外を保持するフィールド
    private Exception exception;

    /**
     * CategoryRepository.findAll()を実行し、例外が発生することを確認するステップ
     */
    @When("CategoryRepositoryのfindAllメソッドを実行する")
    public void categoryrepositoryのfindallメソッドを実行する() {
        // findAll()メソッドがInternalExceptionをスローするように設定する
        doThrow(new InternalException("Database access error"))
                .when(categoryRepository).findAll();
        try{
            categoryRepository.findAll();
        }catch(Exception e){
            exception = e;
        }
    }

    /**
     * CategoryRepository.findById()を実行し、例外が発生することを確認するステップ
     */
    @When("CategoryRepositoryのfindIdメソッドを実行する")
    public void categoryrepositoryのfindidメソッドを実行する() {
        // findById()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(categoryRepository)
                .findById(Mockito.any());
        try{
            var categoryId = new CategoryId(
                    "40bf7198-6795-4a3d-bc88-e59e1fa52438");
            categoryRepository.findById(categoryId);
        }catch(Exception e){
            exception = e;
        }
    }

    @Then("InternalExceptionがスローされたことを評価する")
    public void internalexceptionがスローされたことを評価する() {
        Assertions.assertNotNull(exception);
        Assertions.assertInstanceOf(InternalException.class, exception);
    }

    // ProductRepository をモック化して、例外をスローするように設定する
    private final ProductRepository productRepository
            = Mockito.mock(ProductRepository.class);
    /**
     * 演習-09 シナリオとテストドライバを作成しテストする
     * ProductRepositoryのInternalException例外テスト
     */
    @When("ProductRepositoryのfindByIdメソッドを実行する")
    public void productrepositoryのfindbyidメソッドを実行する() {
        // findById()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(productRepository).findById(Mockito.any());
        try{
            var productId = new ProductId(
                    "40bf7198-6795-4a3d-bc88-e59e1fa52438");
            productRepository.findById(productId);
        }catch(Exception e){
            exception = e;
        }
    }

    @When("ProductRepositoryのfindByNameContainsメソッドを実行する")
    public void productrepositoryのfindbynamecontainsメソッドを実行する() {
        // findByNameContains()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(productRepository).findByNameContains(Mockito.any());
        try{
            var productName = new ProductName("ペン");
            productRepository.findByNameContains(productName);
        }catch(Exception e){
            exception = e;
        }
    }

    @When("ProductRepositoryのexistsメソッドを実行する")
    public void productrepositoryのexistsメソッドを実行する() {
        // exists()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(productRepository).exists(Mockito.any());
        try{
            var productName = new ProductName("ペン");
            productRepository.exists(productName);
        }catch(Exception e){
            exception = e;
        }
    }

    @When("ProductRepositoryのcreateメソッドを実行する")
    public void productrepositoryのcreateメソッドを実行する() {
        // create()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(productRepository).create(Mockito.any());
        try{
            var product = new Product(
                    "e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd3",
                    "消しゴム", 120);
            productRepository.create(product);
        }catch(Exception e){
            exception = e;
        }
    }

    @When("ProductRepositoryのupdateByIdメソッドを実行する")
    public void productrepositoryのupdatebyidメソッドを実行する() {
        // updateById()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(productRepository).updateById(Mockito.any());
        try{
            var product = new Product(
                    "e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd3",
                    "消しゴム", 120);
            productRepository.updateById(product);
        }catch(Exception e){
            exception = e;
        }
    }

    @When("ProductRepositoryのdeleteByIdメソッドを実行する")
    public void productrepositoryのdeletebyidメソッドを実行する() {
        // updateById()メソッドがInternalExceptionをスローするように設定
        doThrow(new InternalException("Database access error"))
                .when(productRepository).deleteById(Mockito.any());
        try{
            var productId = new ProductId(
                    "40bf7198-6795-4a3d-bc88-e59e1fa52438");
            productRepository.deleteById(productId);
        }catch(Exception e){
            exception = e;
        }
    }
}
