package com.project2;

import java.util.ArrayList;
import java.util.List;

import burlap.behavior.stochasticgames.GameEpisode;
import burlap.behavior.stochasticgames.agents.RandomSGAgent;
import burlap.behavior.stochasticgames.agents.maql.MultiAgentQLearning;
import burlap.behavior.stochasticgames.madynamicprogramming.backupOperators.MinMaxQ;
import burlap.behavior.valuefunction.ConstantValueFunction;
import burlap.debugtools.DPrint;
import burlap.domain.singleagent.gridworld.GridWorldDomain;
import burlap.domain.singleagent.gridworld.GridWorldTerminalFunction;
import burlap.domain.stochasticgames.gridgame.GridGame;
import burlap.domain.stochasticgames.gridgame.GridGame.GGTerminalFunction;
import burlap.mdp.auxiliary.common.ConstantStateGenerator;
import burlap.mdp.core.TerminalFunction;
import burlap.mdp.core.action.ActionType;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.SADomain;
import burlap.mdp.singleagent.oo.OOSADomain;
import burlap.mdp.stochasticgames.SGDomain;
import burlap.mdp.stochasticgames.agent.SGAgent;
import burlap.mdp.stochasticgames.agent.SGAgentType;
import burlap.mdp.stochasticgames.model.JointRewardFunction;
import burlap.mdp.stochasticgames.oo.OOSGDomain;
import burlap.mdp.stochasticgames.world.World;
import burlap.statehashing.simple.SimpleHashableStateFactory;

public class MultiAgent {
	public class QL {
		
	}
	
	
	public static void main(String[] args) {
		GridGame gg = new GridGame();
	    OOSGDomain domain = gg.generateDomain();
	    State s = GridGame.getTurkeyInitialState();
	    JointRewardFunction jr = new GridGame.GGJointRewardFunction(domain);
	    TerminalFunction tf = new GridGame.GGTerminalFunction(domain);
	    World world = new World(domain, jr, tf, new ConstantStateGenerator(s));
	    DPrint.toggleCode(world.getDebugId(), false);
	    
		double discount = 0.9;
		double learningRate = 0.1;
		double qInit =0.;
		SGAgentType type = new SGAgentType("agent", domain.getActionTypes());
		SGAgent qlearner = new MultiAgentQLearning(world.getDomain(),
				discount, learningRate, new SimpleHashableStateFactory(),
				qInit, new MinMaxQ(), false, "soccer1",
				type);
		SGAgent qlearner2 = new MultiAgentQLearning(world.getDomain(),
				discount, learningRate, new SimpleHashableStateFactory(),
				qInit, new MinMaxQ(), false, "soccer2",
				type);
	    
	    
	   
	    world.join(qlearner);
	    world.join(qlearner2);
	    GameEpisode ga = world.runGame(20);
	    System.out.println(ga.maxTimeStep());
	    String serialized = ga.serialize();
	    System.out.println(serialized);
	    GameEpisode read = GameEpisode.parse(serialized);
	    System.out.println(read.maxTimeStep());
	    System.out.println(read.state(0).toString());

//		
//		MultiAgentQLearning qlearner = new MultiAgentQLearning(domain, discount, learningRate, new SimpleHashableStateFactory(), qInit, backupOperator, false, "soccer", agentType)
	}

}
