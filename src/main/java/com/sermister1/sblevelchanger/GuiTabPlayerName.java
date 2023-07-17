package com.sermister1.sblevelchanger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Ordering;
import com.sermister1.sblevelchanger.utils.ChatComponentBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class GuiTabPlayerName extends Gui {
	public static Boolean isOnServer;
	//debug, made to test in live environment
	
    /*@SubscribeEvent
	public void ChangeTabName(ClientChatReceivedEvent event) {
    	IChatComponent tablistreverseeng = ChatComponentBuilder.of("")
    			.append("[").setColor(EnumChatFormatting.DARK_GRAY)
    			.append("162").setColor(EnumChatFormatting.DARK_GREEN)
    			.append("] ").setColor(EnumChatFormatting.DARK_GRAY)
    			.append("SermisterOne").setColor(EnumChatFormatting.AQUA)
    			.build();
		IChatComponent testicc = ChatComponentBuilder.of("Hello, world!")
	            .setColor(EnumChatFormatting.RED)
	            .setItalic(true)
	            .append("Test")
	            .setColor(EnumChatFormatting.DARK_BLUE)
	            .build();
		List<IChatComponent> SibList = tablistreverseeng.getSiblings();
		SibList.get(1).setChatStyle(SibList.get(1).getChatStyle().setColor(EnumChatFormatting.AQUA));
		Minecraft mc = Minecraft.getMinecraft();
		Ordering<NetworkPlayerInfo> TabField = ReflectionHelper.getPrivateValue(GuiPlayerTabOverlay.class, null, "field_175252_a");
		NetHandlerPlayClient netHandler = mc.thePlayer.sendQueue;
        List<NetworkPlayerInfo> fullList = TabField.sortedCopy(netHandler.getPlayerInfoMap());
        GuiPlayerTabOverlay tabList = Minecraft.getMinecraft().ingameGUI.getTabList();
        System.out.println(Integer.toString(fullList.size()));
        fullList.forEach((loadedPlayer) -> {
			loadedPlayer.setDisplayName(tablistreverseeng);
		});
        Collection<NetworkPlayerInfo> playerCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
		List<NetworkPlayerInfo> playerList = new ArrayList(playerCollection);
    	playerList.forEach((loadedPlayer) -> {
			System.out.println(loadedPlayer.getGameProfile().getName());
    	});
	}*/
    
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
    //didn't want to use reflection but fuck it
    //handle command run
    public void TabListDisplayNameCommand() {
    	Minecraft mc = Minecraft.getMinecraft();
    	//System.out.println("fired");
    	Ordering<NetworkPlayerInfo> TabField = ReflectionHelper.getPrivateValue(GuiPlayerTabOverlay.class, null, "field_175252_a");
		NetHandlerPlayClient netHandler = mc.thePlayer.sendQueue;
        List<NetworkPlayerInfo> fullList = TabField.sortedCopy(netHandler.getPlayerInfoMap());
    	if(isOnServer == true) {
    		//playerCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
    		//List<NetworkPlayerInfo> playerList = new ArrayList(playerCollection);
        	fullList.forEach((loadedPlayer) -> {
        		if(loadedPlayer.getDisplayName() != null) {
	    			if(loadedPlayer.getDisplayName().toString().contains(Minecraft.getMinecraft().thePlayer.getName())) {
	    				IChatComponent playerDisplayName = loadedPlayer.getDisplayName();
	    				System.out.println(playerDisplayName);
	    				
	    				List<IChatComponent> Siblings = playerDisplayName.getSiblings();
	    				switch(Config.levelColor.toLowerCase()) {
	    				case("0"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.BLACK));
	    					break;
	    				case("1"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_BLUE));
	    					break;
	    				case("2"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_GREEN));
	    					break;
	    				case("3"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_AQUA));
	    					break;
	    				case("4"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_RED));
	    					break;
	    				case("5"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE));
	    					break;
	    				case("6"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.GOLD));
	    					break;
	    				case("7"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.GRAY));
	    					break;
	    				case("8"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_GRAY));
	    					break;
	    				case("9"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.BLUE));
	    					break;
	    				case("a"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.GREEN));
	    					break;
	    				case("b"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.AQUA));
	    					break;
	    				case("c"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.RED));
	    					break;
	    				case("d"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE));
	    					break;
	    				case("e"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.YELLOW));
	    					break;
	    				case("f"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.WHITE));
	    					break;
	    				}
    				}
    			}
    			
    		});
    	}
    	
    }
    //handle server nick update
    @SubscribeEvent
    public void TabListDisplayName(PlayerEvent.NameFormat event) {
    	Minecraft mc = Minecraft.getMinecraft();
    	//System.out.println("fired");
    	Ordering<NetworkPlayerInfo> TabField = ReflectionHelper.getPrivateValue(GuiPlayerTabOverlay.class, null, "field_175252_a");
		NetHandlerPlayClient netHandler = mc.thePlayer.sendQueue;
        List<NetworkPlayerInfo> fullList = TabField.sortedCopy(netHandler.getPlayerInfoMap());
    	//System.out.println("fired");
    	//Collection<NetworkPlayerInfo> playerCollection;
    	if(isOnServer == true) {
    		//playerCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
    		//List<NetworkPlayerInfo> playerList = new ArrayList(playerCollection);
        	fullList.forEach((loadedPlayer) -> {
        		if(loadedPlayer.getDisplayName() != null) {
	    			if(loadedPlayer.getDisplayName().toString().contains(Minecraft.getMinecraft().thePlayer.getName())) {
	    				IChatComponent playerDisplayName = loadedPlayer.getDisplayName();
	    				System.out.println(playerDisplayName+" NameFormat");
    				
	    				List<IChatComponent> Siblings = playerDisplayName.getSiblings();
	    				switch(Config.levelColor.toLowerCase()) {
	    				case("0"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.BLACK));
	    					break;
	    				case("1"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_BLUE));
	    					break;
	    				case("2"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_GREEN));
	    					break;
	    				case("3"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_AQUA));
	    					break;
	    				case("4"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_RED));
	    					break;
	    				case("5"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE));
	    					break;
	    				case("6"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.GOLD));
	    					break;
	    				case("7"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.GRAY));
	    					break;
	    				case("8"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.DARK_GRAY));
	    					break;
	    				case("9"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.BLUE));
	    					break;
	    				case("a"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.GREEN));
	    					break;
	    				case("b"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.AQUA));
	    					break;
	    				case("c"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.RED));
	    					break;
	    				case("d"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE));
	    					break;
	    				case("e"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.YELLOW));
	    					break;
	    				case("f"):
	    					Siblings.get(1).setChatStyle(Siblings.get(1).getChatStyle().setColor(EnumChatFormatting.WHITE));
	    					break;
	    				}
    				}
    			}
    			
    		});
    	}
    	
    }
}
