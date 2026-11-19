package jp.co.fullness.bdd.presentations.search;

import jp.co.fullness.bdd.applications.services.ProductKeywordSearchService;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.exceptions.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * 演習-14:商品キーワード検索コントローラとFormオブジェクトを作成する
 * 商品キーワード検索コントローラ
 */
@Controller
@RequestMapping("/exercise/product/search")
public class SearchController {
    private final MessageSource messageSource;
    private final ProductKeywordSearchService productKeywordSearchService;
    /**
     * コンストラクタ
     * @param messageSource SpringFrameworkのメッセージソース
     * @param productKeywordSearchService 商品キーワード検索サービス
     */
    public SearchController(
            MessageSource messageSource,
            ProductKeywordSearchService productKeywordSearchService) {
        this.messageSource = messageSource;
        this.productKeywordSearchService = productKeywordSearchService;
    }

    /**
     * KeywordFormの初期化とModelへの格納
     * @return
     */
    @ModelAttribute()
    public KeywordForm setUpForm(){
        return new KeywordForm();
    }

    /**
     * 検索ページの表示
     * @param model
     * @return
     */
    @GetMapping
    public String enter(Model model){
        model.addAttribute("title",
                messageSource.getMessage("title.product.search", null, Locale.JAPAN));
        return "search/search";
    }

    /**
     * [検索]ボタンクリックイベントハンドラ
     * @param keywordForm 入力フォーム
     * @param bindingResult バリデーション結果
     * @param model モデル
     * @return 検索結果View名
     */
    @PostMapping(params = "search")
    public String search(@Validated KeywordForm keywordForm,
                         BindingResult bindingResult, Model model){
        model.addAttribute("title",
                messageSource.getMessage("title.product.search", null, Locale.JAPAN));
        // バリデーションエラーがある場合、元のページに戻す
        if (bindingResult.hasErrors()) {
            return "search/search";
        }
        try{
            // 商品キーワード検索する
            var products = productKeywordSearchService.execute(
                    new ProductName(keywordForm.getKeyword()));
            model.addAttribute("products",products);
        }catch(NotFoundException e){
            bindingResult.addError(new ObjectError("",e.getMessage()));
        }
        return "search/search";
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
