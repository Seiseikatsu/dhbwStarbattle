package com.starbattle.network.connection.objects.constant;

/**
 * 
 * Constants for special communication values between server and client.
 * 
 * @author Roland
 *
 */

public class NP_Constants {
	
	public final static int FRIEND_UPDATE_TYPE_ONLINEUPDATE=0;
	public final static int FRIEND_UPDATE_TYPE_ADDFRIEND=1;
	public final static int FRIEND_UPDATE_TYPE_DELETEFRIEND=2;
	public final static int FRIEND_UPDATE_TYPE_ADDFRIENDREQUEST=3;
	public final static int FRIEND_UPDATE_TYPE_ADDFRIENDPENDING=4;
	
	public final static int FRIEND_STATE_FRIENDS=0;
	public final static int FRIEND_STATE_REQUEST=1;
	public final static int FRIEND_STATE_PENDING=2;
	
	public final static int NO_MOVEMENT=0;
	public final static int FORWARD_MOVEMENT=1;
	public final static int BACKWARD_MOVEMENT=2;
	
	public final static int NO_ACTION=0;
	public final static int FIRE_WEAPON=1;
	
	
}
