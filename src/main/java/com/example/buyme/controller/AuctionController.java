package com.example.buyme.controller;

import com.example.buyme.model.*;
import com.example.buyme.service.AuctionService;
import com.example.buyme.service.BidService;
import com.example.buyme.service.UserService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class AuctionController {

    private final AuctionService auctionService;
    private final BidService bidService;
    private final UserService userService;

    @Autowired
    private AuctionController(AuctionService auctionService, BidService bidService, UserService userService) {
        this.auctionService = auctionService;
        this.bidService = bidService;
        this.userService = userService;
    }

    @GetMapping("/auction/{auction}")
    public ModelAndView auction(HttpSession session, @PathVariable(value="auction") String auctionId) {
        User user = (User) session.getAttribute("user");
        Auction auction = auctionService.findAuctionById(new ObjectId(auctionId)).get();
        List<BidTableEntry> bids = BidTableEntry.fromBidsByAuction(bidService.findAllBidsByAuction(new ObjectId(auctionId)));
        boolean disabled = user == null || user.getUsername().equals(auction.getSellerId()) || auction.getExpiration().getTime() < System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("/auction");
        modelAndView.addObject("user", user);
        modelAndView.addObject("auction", AuctionPageEntry.fromAuction(auction, bids.isEmpty()));
        modelAndView.addObject("bids", bids);
        modelAndView.addObject("disabled", disabled);
        return modelAndView;
    }

    @PostMapping("/auction/{auction}/bid")
    public String bidAttempt(HttpSession session, double bid, boolean anonymous, @PathVariable("auction") String auctionId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Bid auctionBid = new Bid(
                new ObjectId(),
                bid,
                new Date(System.currentTimeMillis()),
                anonymous,
                user.getUsername(),
                new ObjectId(auctionId)
        );
        bidService.saveBid(auctionBid);
        Auction auction = auctionService.findAuctionById(new ObjectId(auctionId)).get();
        auction.setCurrent(bid);
        auctionService.saveAuction(auction);
        return "redirect:/auction/" + auctionId;
    }

    @GetMapping("/auction/create")
    public ModelAndView createAuction(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("/login");
        }
        ModelAndView modelAndView = new ModelAndView("/create-auction");
        return modelAndView;
    }

    @PostMapping("/auction/create/attempt")
    public String attemptCreate(HttpSession session, String data) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Document input = Document.parse(data);
        auctionService.saveAuction(new Auction(
                new ObjectId(),
                (String) input.get("title"),
                (String) input.get("description"),
                (String) input.get("condition"),
                (String) input.get("type"),
                (List<Document>) input.get("details"),
                (List<String>) input.get("tags"),
                Double.parseDouble((String) input.get("price")),
                Double.parseDouble((String) input.get("price")),
                Double.parseDouble((String) input.get("increment")),
                new Date(),
                new Date((Long) input.get("expiration")),
                user.getUsername()
        ));
        return "redirect:/home";
    }

}
