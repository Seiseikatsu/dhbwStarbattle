package com.starbattle.accounts.player;

public enum FriendRelationState {

    Friends(0), Pending(1), Request(2);

    /**
     * This is the relation from the view of the player.
     * 
     * Friends = your friends Pending = this relation is a friend request sent by you Request = this relation is a friend request from another player you have to
     * decline or accept
     * 
     */

    private int id;

    FriendRelationState(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
