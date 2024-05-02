package info;

import java.util.HashMap;

public class InfoFromApi {
    public static HashMap<String, String> getValueFromCard1() {
        HashMap<String, String> card1 = new HashMap<>();
        card1.put("title", "\uD83D\uDD25 Топ лучших скидок");
        card1.put("productName", "Фен Dyson HD07 Supersonic (никель/медный)");
        card1.put("price", "19");
        card1.put("priceColor", "#7d2020");
        card1.put("word 'От' in price", "displayed");
        card1.put("discount", "-10%");
        card1.put("discountColor", "#7d2020");
        card1.put("iconInDiscount", "displayed");
        card1.put("sticker: Выбор покупателей", "displayed");
        card1.put("sticker: PRIME", "not displayed");
        card1.put("sticker: Onliner рекомендует", "displayed");
        return card1;
    }
}
