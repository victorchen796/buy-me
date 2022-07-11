package com.example.buyme.service;

import com.example.buyme.model.Bid;
import org.bson.types.ObjectId;

import java.util.List;

public interface BidService {

    public Bid saveBid(Bid bid);

    public List<Bid> findBidsByCurrentUser(String username);

    public List<Bid> findBidsByOtherUser(String username);

    public List<Bid> findAllBidsByAuction(ObjectId auctionId);

    public List<Bid> findAllBids();

}
