package com.sermister1.sblevelchanger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.Ordering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class GuiTabPlayerName extends Gui {
	public static Boolean isOnServer;
	private static int ticks;
	//static boolean gotLevel = false;
	static boolean gotRightBracket = false;
	static boolean gotLeftBracket = false;
	//failsafe for 2 methods
	static boolean gotRightBracket1 = false;
	static boolean gotLeftBracket1 = false;
	String potsblevel;
	
    @SubscribeEvent
    public void onClientConnectedToServer(ClientConnectedToServerEvent event) {
        // Check if the player is connected to a server
        if (event.isLocal) {
        	isOnServer = false;
            //System.out.println("Player is in a single-player world");
        } else {
        	isOnServer = true;
            //System.out.println("Player is in a server world");
        }
    }
    //actual code that will be run on Hypixel
    //Uses static sibling indexes, I'm lazy to do adjustable indexes, hope Hypixel doesn't make any shit with tab displaynames
    //Now should work with dynamic siblings
    //didn't want to use reflection but fuck it
    //omg it works without reflection?
    //handle command run
    public void TabListDisplayNameCommand() {
    if(isOnServer == true) {
    	Minecraft mc = Minecraft.getMinecraft();
    	//gotLevel = false;
    	gotRightBracket1 = false;
    	gotLeftBracket1 = false;
    	
    	Collection<NetworkPlayerInfo> playerCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
    	NetHandlerPlayClient netHandler = mc.thePlayer.sendQueue;
    	List<NetworkPlayerInfo> fullList = new ArrayList(playerCollection);
    	
        fullList.forEach((loadedPlayer) -> {
    		if(loadedPlayer.getDisplayName() != null) {
    			
    			if(loadedPlayer.getDisplayName().getFormattedText().contains(Minecraft.getMinecraft().thePlayer.getName())) {
    				
    				String tabDisplayName = loadedPlayer.getDisplayName().getFormattedText();
    				if (tabDisplayName.contains("[") && tabDisplayName.contains("]")) {
    					
    					potsblevel = tabDisplayName.substring(tabDisplayName.indexOf("[") + 1, tabDisplayName.indexOf("]"));
    				
    					if (potsblevel.contains("VIP") || potsblevel.contains("MVP") || potsblevel.contains("ADMIN")
    							|| potsblevel.contains("OWNER") || potsblevel.contains("GM") || potsblevel.contains("MOJANG")
    							|| potsblevel.contains("EVENTS") || potsblevel.contains("MCP") || potsblevel.contains("NPC")
    							|| potsblevel.contains("YOUTUBE") || potsblevel.contains("PIG")) { // RIP Technoblade
    					} else {
    						
    						potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
    						if(potsblevel.length() > 3) {
    						potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
    						
    						}
    						
    						if (NumberUtils.isNumber(potsblevel)) {
    							IChatComponent chatMessageComponent = new ChatComponentText("");
    							List<IChatComponent> Siblings = loadedPlayer.getDisplayName().getSiblings();
    							
    							for(int i = 0; i < Siblings.size(); i++) {
    								
    								if(Siblings.get(i).getUnformattedText().equals("[")) {
    									gotLeftBracket1 = true;
    									chatMessageComponent.appendSibling(Siblings.get(i));
    								} else if(Siblings.get(i).getUnformattedText().equals("]")) {
    									gotRightBracket1 = true;
    									chatMessageComponent.appendSibling(Siblings.get(i));
    								} else if(gotLeftBracket1 == true && gotRightBracket1 == false && Siblings.get(i).getUnformattedText().equals(potsblevel)) {
    									ChatStyle sbLevelChatStyle = Siblings.get(i).getChatStyle();
    									sbLevelChatStyle.setColor(null);
    									IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
    									sbLevelComp.setChatStyle(sbLevelChatStyle);
    									chatMessageComponent.appendSibling(sbLevelComp);
    								} else if(Siblings.get(i).getUnformattedText().length() > 2 && Siblings.get(i).getUnformattedText().substring(2).equals(potsblevel)) {
    									String psbl = Siblings.get(i).getUnformattedText().substring(2);
    									ChatStyle sbLevelChatStyle = Siblings.get(i).getChatStyle();
    									sbLevelChatStyle.setColor(null);
    									IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
    									sbLevelComp.setChatStyle(sbLevelChatStyle);
    									chatMessageComponent.appendSibling(sbLevelComp);
    								} else {
    									chatMessageComponent.appendSibling(Siblings.get(i));
    								}
    							}
    							loadedPlayer.setDisplayName(chatMessageComponent);
    						}
    					}
    				}
    			}
    		}
    	});
    	}
    }
    //fire name change event every 0.2s
    @SubscribeEvent
    public void TabListDisplayNameEvery4ticks(PlayerTickEvent event) {
    if(isOnServer == true) {
            ticks++;
            if (ticks >= 4) {
            	Minecraft mc = Minecraft.getMinecraft();
            	//gotLevel = false;
            	gotRightBracket = false;
            	gotLeftBracket = false;
            	
            	Collection<NetworkPlayerInfo> playerCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
            	NetHandlerPlayClient netHandler = mc.thePlayer.sendQueue;
            	List<NetworkPlayerInfo> fullList = new ArrayList(playerCollection);
            	
                fullList.forEach((loadedPlayer) -> {
            		if(loadedPlayer.getDisplayName() != null) {
            			
    	    			if(loadedPlayer.getDisplayName().getFormattedText().contains(Minecraft.getMinecraft().thePlayer.getName())) {
    	    				
    	    				String tabDisplayName = loadedPlayer.getDisplayName().getFormattedText();
    	    				if (tabDisplayName.contains("[") && tabDisplayName.contains("]")) {
    	    					
    	    					potsblevel = tabDisplayName.substring(tabDisplayName.indexOf("[") + 1, tabDisplayName.indexOf("]"));
    	    				
    	    					if (potsblevel.contains("VIP") || potsblevel.contains("MVP") || potsblevel.contains("ADMIN")
    	    							|| potsblevel.contains("OWNER") || potsblevel.contains("GM") || potsblevel.contains("MOJANG")
    	    							|| potsblevel.contains("EVENTS") || potsblevel.contains("MCP") || potsblevel.contains("NPC")
    	    							|| potsblevel.contains("YOUTUBE") || potsblevel.contains("PIG")) { // RIP Technoblade
    	    					} else {
    	    						
    	    						potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
    	    						if(potsblevel.length() > 3) {
    	    						potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
    	    						
    	    						}
    	    						if (NumberUtils.isNumber(potsblevel)) {
    	    							IChatComponent chatMessageComponent = new ChatComponentText("");
    	    							List<IChatComponent> Siblings = loadedPlayer.getDisplayName().getSiblings();
    	    							
    	    							for(int i = 0; i < Siblings.size(); i++) {
    	    								if(Siblings.get(i).getUnformattedText().equals("[")) {
    	    									gotLeftBracket = true;
    	    									chatMessageComponent.appendSibling(Siblings.get(i));
    	    								} else if(Siblings.get(i).getUnformattedText().equals("]")) {
    	    									gotRightBracket = true;
    	    									chatMessageComponent.appendSibling(Siblings.get(i));
    	    								} else if(gotLeftBracket == true && gotRightBracket == false && Siblings.get(i).getUnformattedText().equals(potsblevel)) {
    	    									ChatStyle sbLevelChatStyle = Siblings.get(i).getChatStyle();
    	    									sbLevelChatStyle.setColor(null);
    	    									IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
    	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
    	    									chatMessageComponent.appendSibling(sbLevelComp);
    	    								} else {
    	    									chatMessageComponent.appendSibling(Siblings.get(i));
    	    								}		
    	    							}
    	    							loadedPlayer.setDisplayName(chatMessageComponent);
    	    						}
    	    					}
    	    				}
    	    			}
            		}
    	    	});
                ticks = 0;
            	}
                // Execute code every 4 ticks  
        }
    }
}
