package com.example.buyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
public class AuctionPageEntry {

    private ObjectId id;
    private String name;
    private String description;
    private String condition;
    private String type;
    private List<Document> details;
    private List<String> tags;
    private double price;
    private double current;
    private double increment;
    private String currentString;
    private String incrementString;
    private String timestamp;
    private String timeLeft;
    private String sellerId;

    public static AuctionPageEntry fromAuction(Auction auction, boolean bidless) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss zzz");
        long diff = auction.getExpiration().getTime() - System.currentTimeMillis();
        String timeLeft = "";
        if (diff > 0) {
            if (diff / (1000 * 60 * 60 * 24) > 0) timeLeft += (diff / (1000 * 60 * 60 * 24)) + "d ";
            if (diff / (1000 * 60 * 60) > 0) timeLeft += (diff / (1000 * 60 * 60)) + "h ";
            timeLeft += (diff / (1000 * 60)) + "m";
        } else {
            timeLeft = "Expired";
        }
        double current = auction.getCurrent();
        if (bidless) {
            current -= auction.getIncrement();
        }
        return new AuctionPageEntry(
                auction.getId(),
                auction.getName(),
                auction.getDescription(),
                auction.getCondition(),
                auction.getType(),
                auction.getDetails(),
                auction.getTags(),
                auction.getPrice(),
                current,
                auction.getIncrement(),
                currency.format(auction.getCurrent()),
                currency.format(auction.getIncrement() + current),
                date.format(auction.getTimestamp()),
                timeLeft,
                auction.getSellerId()
        );
    }

}
