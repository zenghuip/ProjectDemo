/**
  * Copyright 2017 bejson.com 
  */
package com.jgg.games.model.entity;


import java.util.List;

/**
 * Auto-generated: 2017-03-15 21:50:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MatchListEntity {
    private List<MatchEntity> matches;
    private MatchEntity match;


    public List<MatchEntity> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchEntity> matches) {
        this.matches = matches;
    }

    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }
}