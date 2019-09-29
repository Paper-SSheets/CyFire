package com.cyfire.matches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {

    static {
        System.out.println("\nCyFire: Ready to save new match...");
    }

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);
    @Autowired
    MatchRepository matchRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/matches/new", produces = "application/json")
    public String addNewMatch(@RequestBody Matches match) {
        matchRepo.save(match);
        System.out.println("\n" + match.toString() + "\n");
        return "Match saved for " + match.getNetId() + ": " + match.getMatches();
    }

    @RequestMapping(method = RequestMethod.POST, path = "matches/{net_id}")
    public String appendNewMatch(@RequestBody Matches match) {
        Matches matchToUpdate = getByNetId(match.getNetId());

        matchToUpdate.addMatch(match.getMatches());
        matchRepo.save(matchToUpdate);

        return "New match for " + match.getNetId() + ": " + match.getMatches();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/matches")
    public List<Matches> getAllMatches() {
        logger.info("Entered into Controller Layer");
        List<Matches> results = matchRepo.findAll();
        logger.info("Number of Records Fetched: " + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/matches/{net_id}")
    public Matches getMatchesByNetId(@PathVariable("net_id") String net_id) {
        logger.info("Entered into Controller Layer");
        Matches result = getByNetId(net_id);

        System.out.println("\n Matches for " + result.getNetId() + ": [" + result.getMatches() + "]");
        return result;
    }

    public Matches getByNetId(String net_id) {
        List<Matches> allMatches = matchRepo.findAll();
        Matches userToReturn = new Matches();

        for (int i = 0; i < allMatches.size(); i++) {
            if (allMatches.get(i).getNetId().equals(net_id)) {
                userToReturn = allMatches.get(i);
                break;
            }
        }

        return userToReturn;
    }
}
