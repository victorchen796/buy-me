package com.example.buyme.service.impl;

import com.example.buyme.model.Auction;
import com.example.buyme.repository.AuctionRepository;
import com.example.buyme.service.AuctionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public Auction saveAuction(Auction auction) {
        return auctionRepository.save(auction);
    }

    // implement filter
    @Override
    public List<Auction> findAllAuctions() {
        Sort sort = Sort.by(Sort.Direction.DESC, "timestamp");
        return auctionRepository.findAll(sort);
    }

    @Override
    public List<Auction> findAuctionsByUser(String username) {
        return auctionRepository.findBySellerIdOrderByTimestampDesc(username);
    }

    @Override
    public Optional<Auction> findAuctionById(ObjectId id) {
        return auctionRepository.findById(id);
    }

}
