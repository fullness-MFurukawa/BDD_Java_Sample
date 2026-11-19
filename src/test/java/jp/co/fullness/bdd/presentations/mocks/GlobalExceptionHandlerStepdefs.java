package jp.co.fullness.bdd.presentations.mocks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import jp.co.fullness.bdd.exceptions.InternalException;
import jp.co.fullness.bdd.presentations.advice.GlobalExceptionHandler;
import jp.co.fullness.bdd.presentations.register.RegisterController;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.doThrow;

/**
 * 演習-20:例外ハンドリングアドバイスをMockMvcテストする
 */
public class GlobalExceptionHandlerStepdefs {
    // テスト用にHTTPリクエストを送信し、レスポンスを検証するためのモック
    private MockMvc mockMvc;
    // テスト対象であるRegisterControllerをモック化したインスタンス
    private RegisterController registerController;

    /**
     * テストのセットアップメソッド
     * 各テストの実行前に呼ばれ、必要なMockMvcやモックの設定を行う
     */
    @Before
    public void setup() {
        // RegisterControllerのモックを作成してセットアップ
        this.registerController = Mockito.mock(RegisterController.class);
        // MockMvcをStandaloneでセットアップし、GlobalExceptionHandlerも追加して例外処理を含める
        this.mockMvc = MockMvcBuilders.standaloneSetup(registerController)
                // GlobalExceptionHandlerも追加する
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    // MockMvcでのリクエスト結果が格納される
    private MvcResult mvcResult;
    @When("内部エラーが発生するリクエストを送信する")
    public void 内部エラーが発生するリクエストを送信する() throws Exception{
        // confirmメソッドが呼ばれた際にInternalExceptionをスローするようにモック設定
        doThrow(new InternalException("内部例外発生"))
                .when(registerController).confirm(
                        Mockito.any(), Mockito.any(), Mockito.any());
        // MockMvcを使ってPOSTリクエストを送信し、エラービューが返されるか検証
        mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/exercise/product/register")
                                .param("register", ""))
                .andExpect(MockMvcResultMatchers.view().name("error/error"))
                .andReturn();
    }
    @Then("エラーページ {string} が表示される")
    public void エラーページが表示される(String viewName) {
        // 期待するエラービューが表示されたかを確認する
        Assertions.assertEquals(viewName,
                mvcResult.getModelAndView().getViewName(),
                "期待するエラービュー名と一致しません");
    }
}
