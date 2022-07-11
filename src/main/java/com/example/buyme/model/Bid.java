package com.example.buyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("bids")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    @Id
    private ObjectId id;

    private double price;
    private Date timestamp;
    private boolean anonymous;
    @Field("bidder_id")
    private String bidderId;
    @Field("auction_id")
    private ObjectId auctionId;

}
