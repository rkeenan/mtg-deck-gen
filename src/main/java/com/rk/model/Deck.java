package com.rk.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "{\"deck\":\"" + StringUtils.join(this.cards, ",") + "\",length:\"" + cards.size() + "\"";
    }
}
