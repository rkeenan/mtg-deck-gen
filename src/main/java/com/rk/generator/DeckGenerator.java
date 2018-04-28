package com.rk.generator;

import com.rk.model.Card;
import com.rk.model.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class DeckGenerator {

    private Deck deck;

    public DeckGenerator(Integer cardCount, String color, Integer manaCount, List<Card> cards, Integer manaCurve) {
        List<Card> coloredCards = sortCardsByColor(color, cards);
        Integer actualCardCount = cardCount - manaCount;
        coloredCards = randomByManaCurve(coloredCards, manaCurve, actualCardCount);
        this.deck = new Deck(coloredCards);
    }

    private List<Card> sortCardsByColor(String color, List<Card> cards) {
        final String[] colorArr = new String[] {color};
        return cards.stream().filter(card -> arrayEquals(card.getColors(), colorArr)).collect(Collectors.toList());
    }

    private List<Card> randomByManaCurve(List<Card> cards, Integer manaCurve, Integer cardCount) {
        List<Card> randomLimited = new ArrayList<>();
        Integer max = cards.size();
        Long currentManaCurve = 0L;
        Long totalMana = 0L;
        for (int i = 0; i < cardCount; i ++) {
            Card currentCard = cards.get(ThreadLocalRandom.current().nextInt(0, max));
            Integer currentCardMana = currentCard.getCmc();
            System.out.println("current card mana = " + currentCardMana);
            totalMana += currentCardMana;
            System.out.println("total mana = " + totalMana);
            currentManaCurve = totalMana / (i + 1);
            System.out.println("mana curve = " + currentManaCurve);
            randomLimited.add(currentCard);
        }
        return randomLimited;
    }

    private boolean arrayEquals(String[] colors, String[] colors2) {
        return Arrays.equals(colors, colors2);
    }

    public Deck getDeck() {
        return deck;
    }
}
