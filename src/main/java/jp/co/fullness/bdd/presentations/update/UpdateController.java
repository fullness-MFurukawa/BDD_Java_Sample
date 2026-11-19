package jp.co.fullness.bdd.presentations.update;

import jp.co.fullness.bdd.applications.services.ProductUpdateService;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductId;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.support.SessionStatus;
import java.util.Locale;

/**
 * 演習-16:商品変更コントローラとFormオブジェクトを作成する
 * 商品更新コントローラ
 */
@Controller
@RequestMapping("/exercise/product/update")
@SessionAttributes(names= {"updateForm"})
public class UpdateController {
    // メッセージソース
    private final MessageSource messageSource;
    // 商品変更サービス
    private final ProductUpdateService productUpdateService;
    /**
     * コンストラクタ
     * @param messageSource メッセージソース
     * @param productUpdateService 商品変更サービス
     */
    public UpdateController(
            MessageSource messageSource,
            ProductUpdateService productUpdateService) {
        this.messageSource = messageSource;
        this.productUpdateService = productUpdateService;
    }
    /**
     * UpdateFormの初期化とModelへ格納
     * @return
     */
    @ModelAttribute
    public UpdateForm setupForm(){
        return new UpdateForm();
    }

    /**
     * 商品更新ページへのリクエストを処理
     * @param id 商品Id
     * @param model モデル
     * @return 更新ページのView名
     */
    @GetMapping("/{id}")
    public String enter(@PathVariable("id") String id, UpdateForm form, Model model) {
        model.addAttribute("title",
                messageSource.getMessage("title.product.update",
                        null, Locale.JAPAN));
        var product = productUpdateService.getProduct(new ProductId(id));
        form.setId(product.getProductId().getValue());
        form.setName(product.getProductName().getValue());
        form.setPrice(product.getProductPrice().getValue());
        return "update/enter";  // 更新ページ
    }

    /**
     * [変更]ボタンクリックイベントハンドラ
     * @return リダイレクト:デフォルトリクエスト
     */
    @PostMapping(params = "update")
    public String confirm(@Validated UpdateForm updateForm,
                          BindingResult bindingResult, Model model) {
        model.addAttribute("title",
                messageSource.getMessage("title.product.update",
                        null, Locale.JAPAN));
        if (bindingResult.hasErrors()){
            return "update/enter";
        }
        // register()メソッドへリダイレクト
        return "redirect:/exercise/product/update?complete";
    }

    /**
     * 商品変更リクエストハンドラ
     * 入力内容から、Productエンティティを生成して登録する
     * @param updateForm 商品変更Form
     * @param sessionStatus セッションステータス
     * @param model モデル
     * @return 変更完了画面名
     */
    @GetMapping(params="complete")
    public String register(UpdateForm updateForm ,
                           SessionStatus sessionStatus , Model model){
        // 変更商品のエンティティ生成
        var product = new Product(
                updateForm.getId(),
                updateForm.getName(),
                updateForm.getPrice());
        // 商品の更新
        productUpdateService.update(product);
        sessionStatus.setComplete();
        model.addAttribute(product);
        model.addAttribute("title",
                messageSource.getMessage("title.product.update.end",
                        null, Locale.JAPAN));
        return "update/complete";
    }

    /**
     * [終了]ボタンクリックイベントハンドラ
     * @return リダイレクト:デフォルトリクエスト
     */
    @PostMapping(params = "end")
    public String end(){
        return "redirect:/";
    }
}
