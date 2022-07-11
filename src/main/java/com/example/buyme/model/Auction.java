package com.example.buyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document("auctions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auction {

    @Id
    private ObjectId id;

    private String name;
    private String description;
    private String condition;
    private String type;
    private List<org.bson.Document> details;
    private List<String> tags;
    private double price;
    private double current;
    private double increment;
    private Date timestamp;
    private Date expiration;
    @Field("seller_id")
    private String sellerId;

}
