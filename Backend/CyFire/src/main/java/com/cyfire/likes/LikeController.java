package com.cyfire.likes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {
    /*
     * Adding comment to test CICD
     * Another comment
     */

    static {
        System.out.println("\nCyFire: Ready to save new like...");
    }

    private final Logger logger = LoggerFactory.getLogger(LikeController.class);
    @Autowired
    LikeRepository likeRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/likes/new", produces = "appication/json")
    public String addNewLike(@RequestBody Likes like) {
        likeRepo.save(like);
        System.out.println("\n" + like.toString() + "\n");
        return "Like saved for: " + like.getNet_id() + ": " + like.getLikes();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/likes")
    public List<Likes> getAllLikes() {
        logger.info("Entered into Controller Layer");
        List<Likes> results = likeRepo.findAll();
        logger.info("Number of Records Fetched: " + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/likes/{net_id}")
    public Likes getLikeByNetId(@PathVariable("net_id") String net_id) {
        logger.info("Entered into Controller Layer");
        Likes result = getByNetId(net_id);

        System.out.println("\n Likes for " + result.getNet_id() + ": [" + result.getLikes() + "]");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, path = "likes/{net_id}")
    public String appendNewLike(@RequestBody Likes like) {
        Likes likeToUpdate = getByNetId(like.getNet_id());

        likeToUpdate.addLike(like.getLikes());
        likeRepo.save(likeToUpdate);

        return "New like for " + like.getNet_id() + ": " + like.getLikes();
    }

    public Likes getByNetId(String net_id) {
        List<Likes> allLikes = likeRepo.findAll();
        Likes userToReturn = new Likes();

        for (int i = 0; i < allLikes.size(); i++) {
            if (allLikes.get(i).getNet_id().equals(net_id)) {
                userToReturn = allLikes.get(i);
                break;
            }
        }

        return userToReturn;
    }

}
