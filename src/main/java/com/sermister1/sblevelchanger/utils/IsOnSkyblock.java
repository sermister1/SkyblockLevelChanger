package com.sermister1.sblevelchanger.utils;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;

public class IsOnSkyblock {
	private static final Set<String> SKYBLOCK_IN_ALL_LANGUAGES = Sets.newHashSet("SKYBLOCK", "\u7A7A\u5C9B\u751F\u5B58", "\u7A7A\u5CF6\u751F\u5B58");
	private static String scoreboardTitle;
	private static List<String> scoreboardLines;

    Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
    ScoreObjective sidebarObjective = scoreboard.getObjectiveInDisplaySlot(1);
    private static void clear() {
        scoreboardTitle = null;
        scoreboardLines = null;
    }
    public boolean isOnSkyblock() {
    	for (String skyblock : SKYBLOCK_IN_ALL_LANGUAGES) {
    		if(sidebarObjective != null) {
	            if (sidebarObjective.getDisplayName().contains(skyblock)) {
	            	return true;
	                //foundSkyblockTitle = true;
	                //break;
	            }
    		}
            
        }
    	return false;
    }
    
    

}