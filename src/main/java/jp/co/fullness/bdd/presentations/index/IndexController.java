package jp.co.fullness.bdd.presentations.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * デフォルトリクエストコントローラ
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String menuPage(Model model) {
        // MenuControllerのリクエストマッピングにリダイレクト
        return "redirect:/exercise/menu";
    }
}
