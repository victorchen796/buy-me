package com.example.buyme.controller;

import com.example.buyme.model.AuctionTableEntry;
import com.example.buyme.model.Bid;
import com.example.buyme.model.BidTableEntry;
import com.example.buyme.model.User;
import com.example.buyme.service.AuctionService;
import com.example.buyme.service.BidService;
import com.example.buyme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final AuctionService auctionService;
    private final BidService bidService;
    private final UserService userService;

    @Autowired
    public UserController(AuctionService auctionService, BidService bidService, UserService userService) {
        this.auctionService = auctionService;
        this.bidService = bidService;
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public ModelAndView user(HttpSession session, @PathVariable(value="username") String username) {
        ModelAndView modelAndView = new ModelAndView("/user");
        modelAndView.addObject("username", username);
        modelAndView.addObject("auctions",
                AuctionTableEntry.fromAuctionList(auctionService.findAuctionsByUser(username)));
        User user = (User) session.getAttribute("user");
        List<Bid> bids;
        if (user != null && user.getUsername().equals(username)) {
            modelAndView.addObject("bids",
                    BidTableEntry.fromBidsByUser(bidService.findBidsByCurrentUser(username), auctionService));
        } else {
            modelAndView.addObject("bids",
                    BidTableEntry.fromBidsByUser(bidService.findBidsByOtherUser(username), auctionService));
        }
        return modelAndView;
    }

}
