package jp.co.fullness.bdd.presentations.menu;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * メニューコントローラ
 */
@Controller
@RequestMapping("exercise/menu")
public class MenuController {
    private final MessageSource messageSource;
    public MenuController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String menuPage(Model model) {
        model.addAttribute("title",
                messageSource.getMessage("title.menu",
                        null, Locale.JAPAN));
        return "menu/menu";
    }
}
