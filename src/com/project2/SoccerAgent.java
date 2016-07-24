package com.project2;

import java.util.Arrays;
import java.util.List;

import burlap.mdp.core.oo.state.OOStateUtilities;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.MutableState;
import burlap.mdp.core.state.State;
import burlap.mdp.core.state.StateUtilities;
import burlap.mdp.core.state.UnknownKeyException;
import burlap.mdp.core.state.annotations.DeepCopyState;
import static com.project2.SoccerGame.*;
/**
 * @author James MacGlashan.
 */
@DeepCopyState
public class SoccerAgent implements ObjectInstance, MutableState {

	public int x;
	public int y;
	public int player;
	public boolean hasBall;

	protected String name;

	private static final List<Object> keys = Arrays.<Object>asList(VAR_X, VAR_Y, VAR_PN);


	public SoccerAgent() {
	}

	public SoccerAgent(int x, int y, int player, String name) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.name = name;
		this.hasBall=true;
	}
	public SoccerAgent(boolean hasBall, int x, int y, int player, String name) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.name = name;
		this.hasBall=hasBall;
	}

	@Override
	public String className() {
		return CLASS_AGENT;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public ObjectInstance copyWithName(String objectName) {
		return new SoccerAgent(hasBall, x, y, player, objectName);
	}

	@Override
	public MutableState set(Object variableKey, Object value) {

		int i = StateUtilities.stringOrNumber(value).intValue();

		if(variableKey.equals(VAR_X)){
			this.x = i;
		}
		else if(variableKey.equals(VAR_Y)){
			this.y = i;
		}
		else if(variableKey.equals(VAR_PN)){
			this.player = i;
		}
		else{
			throw new UnknownKeyException(variableKey);
		}

		return this;
	}

	@Override
	public List<Object> variableKeys() {
		return keys;
	}

	@Override
	public Object get(Object variableKey) {
		if(variableKey.equals(VAR_X)){
			return x;
		}
		else if(variableKey.equals(VAR_Y)){
			return y;
		}
		else if(variableKey.equals(VAR_PN)){
			return player;
		}
		else{
			throw new UnknownKeyException(variableKey);
		}
	}

	@Override
	public State copy() {
		return new SoccerAgent(hasBall, x, y, player, name);
	}

	@Override
	public String toString() {
		return OOStateUtilities.objectInstanceToString(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasBall() {
		return hasBall;
	}

	public void setHasBall(boolean hasBall) {
		this.hasBall = hasBall;
	}
}