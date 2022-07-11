package com.example.buyme.model;

import com.example.buyme.service.AuctionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
public class BidTableEntry {

    private String timestamp;
    private String title;
    private ObjectId auctionId;
    private String price;
    private Date timestampDate;
    private String bidderId;

    public static List<BidTableEntry> fromBidsByAuction(List<Bid> bids) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        List<BidTableEntry> entries = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss zzz");
        for (Bid bid : bids) {
            entries.add(new BidTableEntry(
                    date.format(bid.getTimestamp()),
                    null,
                    null,
                    currency.format(bid.getPrice()),
                    bid.getTimestamp(),
                    bid.isAnonymous() ? "anonymous" : bid.getBidderId()
            ));
        }
        return entries;
    }

    public static List<BidTableEntry> fromBidsByUser(List<Bid> bids, AuctionService auctionService) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        List<BidTableEntry> entries = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss zzz");
        for (Bid bid : bids) {
            entries.add(new BidTableEntry(
                    dateFormat.format(bid.getTimestamp()),
                    auctionService.findAuctionById(bid.getAuctionId()).get().getName(),
                    bid.getAuctionId(),
                    currency.format(bid.getPrice()),
                    bid.getTimestamp(),
                    null
            ));
        }
        return entries;
    }

}
