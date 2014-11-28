package com.starbattle.client.views.shop;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.window.ContentView;

public class ShopView extends ContentView{

	public final static int VIEW_ID=6;
	
	public  ShopView(NetworkConnection networkConnection) {
		windowSize = StarBattleClient.windowSize;
		
	}
	
	
	@Override
	protected void initView() {
		
	}

	@Override
	protected void onClosing() {
	
	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
