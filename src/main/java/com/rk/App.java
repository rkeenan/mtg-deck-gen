package com.rk;

import com.rk.api.MtgJsonApi;
import com.rk.generator.DeckGenerator;
import com.rk.model.Card;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        try {
            List<Card> cardList = MtgJsonApi.getCardList();
            DeckGenerator deckGenerator = new DeckGenerator(60, "Green", 15, cardList, 4);
            System.out.println(deckGenerator.getDeck());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
