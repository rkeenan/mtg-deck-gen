package com.rk.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rk.model.Card;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MtgJsonApi {

    private static final String ALL_CARDS_ENDPOINT = "http://mtgjson.com/json/AllCards.json";
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<Card> getCardList() throws ClientProtocolException, IOException {
        List<Card> cards = new ArrayList<>();
        String response = Request.Get(ALL_CARDS_ENDPOINT).execute().returnContent().asString();
        JsonNode jsonNode = mapper.readTree(response);
        Iterator<JsonNode> nodes = jsonNode.elements();
        while(nodes.hasNext()) {
            JsonNode node = nodes.next();
            Card card = mapper.readValue(node.toString(), Card.class);
            if (card != null) {
                cards.add(card);
            }
        }
        return cards;
    }
}
