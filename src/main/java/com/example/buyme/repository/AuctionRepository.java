package com.example.buyme.repository;

import com.example.buyme.model.Auction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuctionRepository extends MongoRepository<Auction, ObjectId> {

    List<Auction> findBySellerIdOrderByTimestampDesc(String username);

}
