package com.example.buyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
public class AuctionTableEntry {

    private String name;
    private String condition;
    private String type;
    private List<String> tags;
    private String current;
    private String timeLeft;
    private Date expiration;
    private String sellerId;
    private String auctionId;

    public static List<AuctionTableEntry> fromAuctionList(List<Auction> auctions) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        List<AuctionTableEntry> entries = new ArrayList<>();
        for (Auction auction : auctions) {
            long diff = auction.getExpiration().getTime() - System.currentTimeMillis();
            String timeLeft = "";
            if (diff > 0) {
                if (diff / (1000 * 60 * 60 * 24) > 0) timeLeft += (diff / (1000 * 60 * 60 * 24)) + "d ";
                if (diff / (1000 * 60 * 60) > 0) timeLeft += (diff / (1000 * 60 * 60)) + "h ";
                timeLeft += (diff / (1000 * 60)) + "m";
            } else {
                timeLeft = "Expired";
            }
            entries.add(new AuctionTableEntry(
                    auction.getName(),
                    auction.getCondition(),
                    auction.getType(),
                    auction.getTags(),
                    currency.format(auction.getCurrent()),
                    timeLeft,
                    auction.getExpiration(),
                    auction.getSellerId(),
                    auction.getId().toString()
            ));
        }
        return entries;
    }

}
