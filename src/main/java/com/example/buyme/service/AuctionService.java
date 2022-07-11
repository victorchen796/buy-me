package com.example.buyme.service;

import com.example.buyme.model.Auction;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface AuctionService {

    public Auction saveAuction(Auction auction);

    public List<Auction> findAllAuctions();

    public List<Auction> findAuctionsByUser(String username);

    public Optional<Auction> findAuctionById(ObjectId id);

}
