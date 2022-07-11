package com.example.buyme.repository;

import com.example.buyme.model.Bid;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BidRepository extends MongoRepository<Bid, ObjectId> {

    List<Bid> findByBidderIdOrderByTimestampDesc(String username);

    List<Bid> findByBidderIdAndAnonymousOrderByTimestampDesc(String username, boolean anonymous);

    List<Bid> findByAuctionIdOrderByTimestampDesc(ObjectId auctionId);

    Optional<Bid> findFirstByAuctionIdOrderByPriceDesc(ObjectId auctionId);

}
