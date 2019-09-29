package com.cyfire.matches;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Matches {

    @Id
    @Column(name = "net_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private String netId;

    @Column(name = "matches")
    @NotFound(action = NotFoundAction.IGNORE)
    private String matches;

    public Matches(String net_id, String matches) {
        this.netId = net_id;
        this.matches = matches;
    }


    public Matches() {
        this.netId = "";
        this.matches = "";
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("net_id: ", this.getNetId()).append("matches: ", this.getMatches())
                .toString();
    }

    public String getMatches() {
        return this.matches;
    }

    public String getNetId() {
        return this.netId;
    }

    public void setNetId(String net_id) {
        this.netId = net_id;
    }

    public void addMatch(String newMatch) {
        this.matches += "," + newMatch;
    }

}
