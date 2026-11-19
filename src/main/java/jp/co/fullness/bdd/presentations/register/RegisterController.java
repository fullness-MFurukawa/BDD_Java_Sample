package jp.co.fullness.bdd.presentations.register;

import jp.co.fullness.bdd.applications.services.ProductRegisterService;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.exceptions.ExistsException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Locale;

/**
 * 商品登録コントローラ
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Controller
@RequestMapping("/exercise/product/register")
@SessionAttributes(names= {"categories" , "registerForm"})
public class RegisterController {
    private final MessageSource messageSource;
    private final ProductRegisterService productRegisterService;
    /**
     * コンストラクタ
     * @param messageSource
     * @param productRegisterService
     */
    public RegisterController(
            MessageSource messageSource,
            ProductRegisterService productRegisterService) {
        this.messageSource = messageSource;
        this.productRegisterService = productRegisterService;
    }
    /**
     * RegisterFormの初期化とModelへの格納
     * @return
     */
    @ModelAttribute
    public RegisterForm setupForm(){
        return new RegisterForm();
    }

    /**
     * 入力画面表示
     * @param categories
     * @param model
     * @return
     */
    @GetMapping
    public String enter(
            @SessionAttribute(value ="categories",required=false) List<Category> categories, Model model){
        model.addAttribute("title",
                messageSource.getMessage("title.product.register", null, Locale.JAPAN));
        if (categories == null) {
            var result = productRegisterService.getCategories();
            model.addAttribute("categories", result);
        }
        return "register/enter";
    }
    /**
     * 入力値の検証、商品の有無を確認
     * 問題がなければ、PRGパターンで登録処理に遷移する
     * @param registerForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(params = "register")
    public String confirm(
            @Validated RegisterForm registerForm,
            BindingResult bindingResult, Model model) {
        // 入力値チェックエラー?
        if (bindingResult.hasErrors()){
            model.addAttribute("title",
                    messageSource.getMessage("title.product.register", null, Locale.JAPAN));
            return "register/enter"; // 入力画面に遷移
        }
        try{
            // 商品の有無を確認
            productRegisterService.exists(new ProductName(registerForm.getName()));
        }catch(ExistsException e){
            model.addAttribute("title",
                    messageSource.getMessage("title.product.register", null, Locale.JAPAN));
            // 既に同じ商品が登録されている場合は入力画面に遷移
            bindingResult.addError(new ObjectError("",e.getMessage()));
            return "register/enter";
        }
        // registerメソッドへリダイレクト
        return "redirect:/exercise/product/register?complete";
    }

    /**
     * 商品登録
     * @param registerForm
     * @param sessionStatus
     * @param model
     * @return
     */
    @GetMapping(params="complete")
    public String register(
            RegisterForm registerForm ,
            SessionStatus sessionStatus , Model model){
        // 商品エンティティの生成
        var product = new Product(registerForm.getName(), registerForm.getPrice());
        var category = new Category(registerForm.getCategoryId(),"dummy");
        product.changeCategory(category);
        // 商品登録
        productRegisterService.register(product);
        // セッション内の登録データをクリア
        sessionStatus.setComplete();
        // 次の画面に必要なデータをModelに格納
        model.addAttribute(product);
        model.addAttribute("title",
                messageSource.getMessage("title.product.register.end", null, Locale.JAPAN));
        // 登録完了画面に遷移
        return "register/complete";
    }

    /**
     * [終了]ボタンクリックイベントハンドラ
     * @return リダイレクト:デフォルトリクエスト
     */
    @PostMapping(params = "end")
    public String end(){
        return "redirect:/";
    }

    /**
     * [入力へ]ボタンクリックイベントハンドラ
     * @return リダイレクト:入力画面へのリダイレクト
     */
    @PostMapping(params = "enter")
    public String reenter(){
        return "redirect:/exercise/product/register";
    }
}
