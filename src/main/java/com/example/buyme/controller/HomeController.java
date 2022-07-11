package com.example.buyme.controller;

import com.example.buyme.model.AuctionTableEntry;
import com.example.buyme.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final AuctionService auctionService;

    @Autowired
    public HomeController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {
        // implement filters
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("user", session.getAttribute("user"));
        modelAndView.addObject("auctions", AuctionTableEntry.fromAuctionList(auctionService.findAllAuctions()));
        return modelAndView;
    }

}
