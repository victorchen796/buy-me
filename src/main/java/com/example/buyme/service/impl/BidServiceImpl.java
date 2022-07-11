package com.example.buyme.service.impl;

import com.example.buyme.model.Bid;
import com.example.buyme.repository.BidRepository;
import com.example.buyme.service.BidService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    private BidRepository bidRepository;

    @Override
    public Bid saveBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> findBidsByCurrentUser(String username) {
        return bidRepository.findByBidderIdOrderByTimestampDesc(username);
    }

    @Override
    public List<Bid> findBidsByOtherUser(String username) {
        return bidRepository.findByBidderIdAndAnonymousOrderByTimestampDesc(username, false);
    }

    @Override
    public List<Bid> findAllBidsByAuction(ObjectId auctionId) {
        return bidRepository.findByAuctionIdOrderByTimestampDesc(auctionId);
    }

    @Override
    public List<Bid> findAllBids() {
        return bidRepository.findAll();
    }
}
