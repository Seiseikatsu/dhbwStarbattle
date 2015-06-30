package com.starbattle.ingame.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.states.BattleState;
import com.starbattle.ingame.game.states.LoadingState;
import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.render.RenderResource;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameStateContainer extends StateBasedGame {

	private GameManager manager;
	private NP_PrepareGame prepareGame;

	public GameStateContainer(GameManager manager, NP_PrepareGame prepareGame) {
		super("Starbattle Client");
		this.manager=manager;
		this.prepareGame=prepareGame;
		
	}
	

	public void setRenderSettings(RenderSettings settings) {

	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new LoadingState(manager,prepareGame));
		addState(new BattleState(manager));
		
		Input input = container.getInput();
		manager.prepareInput(input);
		RenderResource.readScreenSize();
	}

	@Override
	public boolean closeRequested()
	{
		try
        {
            manager.closeGame();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
	    return true;
	}
}
