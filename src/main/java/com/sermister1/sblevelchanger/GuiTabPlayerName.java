package com.sermister1.sblevelchanger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

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
	static boolean dosmth;
	static boolean dosmth1;
	//static boolean hasJoined = false;
	static IChatComponent oldDisplayName;
	
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
    	dosmth1 = true;
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
    						if(potsblevel.substring(potsblevel.length() - 2).equals("\u00A7r")) {
    							potsblevel = potsblevel.substring(0, potsblevel.length() - 2);
    						}
    						if(potsblevel.length() > 2 && potsblevel.substring(0, 2).contains("\u00A7")) {
    							potsblevel = potsblevel.substring(2);
    						}
    						//if(potsblevel.length() > 3 && potsblevel != Config.levelNumber ) {
    						//	potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
    						
    						//}
    						
    						if (NumberUtils.isNumber(potsblevel) || potsblevel.equals(Config.prevLevel)) {
    							
    							/*if(Main.hasJoined = false) {
    		    					oldDisplayName = loadedPlayer.getDisplayName();
    		    					Main.hasJoined = true;
    		    				}*/
    							
    							
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
    									/*IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
    									sbLevelComp.setChatStyle(sbLevelChatStyle);
    									chatMessageComponent.appendSibling(sbLevelComp);*/
    									if(!Config.levelColor.equals("") && Config.levelNumber.equals("")) {
	    									IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
	    									chatMessageComponent.appendSibling(sbLevelComp);
	    									
    									} else if(!Config.levelColor.equals("") && !Config.levelNumber.equals("")) {
    										IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + Config.levelNumber);
	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
	    									chatMessageComponent.appendSibling(sbLevelComp);
	    									
    									} else if(Config.levelColor.equals("") && Config.levelNumber.equals("")) {
    										dosmth1 = false;
    										
    									}
    								} else if(Siblings.get(i).getUnformattedText().length() > 2 && Siblings.get(i).getUnformattedText().substring(2).equals(potsblevel)) {
    									String psbl = Siblings.get(i).getUnformattedText().substring(2);
    									ChatStyle sbLevelChatStyle = Siblings.get(i).getChatStyle();
    									//sbLevelChatStyle.setColor(null);
    									/*IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
    									sbLevelComp.setChatStyle(sbLevelChatStyle);
    									chatMessageComponent.appendSibling(sbLevelComp);*/
    									if(!Config.levelColor.equals("") && Config.levelNumber.equals("")) {
	    									IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
	    									chatMessageComponent.appendSibling(sbLevelComp);
	    									
    									} else if(!Config.levelColor.equals("") && !Config.levelNumber.equals("")) {
    										IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + Config.levelNumber);
	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
	    									chatMessageComponent.appendSibling(sbLevelComp);
	    									
    									} else if(Config.levelColor.equals("") && Config.levelNumber.equals("")) {
    										dosmth1 = false;
    										
    									}
    								} else {
    									chatMessageComponent.appendSibling(Siblings.get(i));
    								}
    							}
    							if(dosmth1) {
    								loadedPlayer.setDisplayName(chatMessageComponent);
    								
    							} else {
    								//loadedPlayer.setDisplayName(oldDisplayName);
    								//Minecraft.getMinecraft().thePlayer.addChatMessage(oldDisplayName);
    								
    							}
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
            	dosmth = true;
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
    	    						if(potsblevel.length() > 3 && Config.levelColor != "") {
    	    						potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
    	    						
    	    						}
    	    						if(potsblevel.length() > 2 && potsblevel.substring(potsblevel.length() - 2).equals("\u00A7r")) {
    	    							potsblevel = potsblevel.substring(0, potsblevel.length() - 2);
    	    						}
    	    						
    	    						if (NumberUtils.isNumber(potsblevel) || potsblevel.equals(Config.prevLevel)) {
    	    							
    	    							
    	    							
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
    	    									if(!Config.levelColor.equals("") && Config.levelNumber.equals("")) {
	    	    									IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + potsblevel);
	    	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
	    	    									chatMessageComponent.appendSibling(sbLevelComp);
    	    									} else if(!Config.levelColor.equals("") && !Config.levelNumber.equals("")) {
    	    										IChatComponent sbLevelComp = new ChatComponentText("\u00A7"+Config.levelColor + Config.levelNumber);
	    	    									sbLevelComp.setChatStyle(sbLevelChatStyle);
	    	    									chatMessageComponent.appendSibling(sbLevelComp);
    	    									} else if(Config.levelColor.equals("") && Config.levelNumber.equals("")) {
    	    										dosmth = false;
    	    									}
    	    								} else {
    	    									chatMessageComponent.appendSibling(Siblings.get(i));
    	    								}		
    	    							}
    	    							if(dosmth) {
    	    								loadedPlayer.setDisplayName(chatMessageComponent);
    	    								
    	    							}
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
