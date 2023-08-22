package com.sermister1.sblevelchanger;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.sermister1.sblevelchanger.utils.IsOnSkyblock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
	public static final String MODID = "sblevelchanger";
	public static final String VERSION = "2.0";

	public static int fsttime = 0;
	public int lvstrlength;
	public static String potsblevel;
	public static int sblevel;
	static boolean gotLevel = false;
	private static boolean isOnServer;
	private static boolean isClean;
	public static boolean hasJoined = false;
	
	public static NetworkPlayerInfo GP;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		File configFile = new File(event.getModConfigurationDirectory(), "sblevelchanger.cfg");
		Config.init(configFile);

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new CommandColor());
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new GuiTabPlayerName());

	}
	public static void pr(String input) {
		System.out.println(input);
	}
	
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
	
	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
		
		isClean = false;
		if(isOnServer) {
			IsOnSkyblock ios = new IsOnSkyblock();
			if(ios.isOnSkyblock()) {
				gotLevel = false;
				String message = event.message.getUnformattedText();
				String chatmessage = event.message.getFormattedText();
				String nickandlevel = (event.message.getFormattedText().replaceAll("\\:.*", ""));
				if (nickandlevel.contains(Minecraft.getMinecraft().thePlayer.getName())) {
					if (nickandlevel.contains("[") && nickandlevel.contains("]")) {
						potsblevel = nickandlevel.substring(nickandlevel.indexOf("[") + 1, nickandlevel.indexOf("]"));
						if (potsblevel.contains("VIP") || potsblevel.contains("MVP") || potsblevel.contains("ADMIN")
								|| potsblevel.contains("OWNER") || potsblevel.contains("GM") || potsblevel.contains("MOJANG")
								|| potsblevel.contains("EVENTS") || potsblevel.contains("MCP") || potsblevel.contains("NPC")
								|| potsblevel.contains("YOUTUBE") || potsblevel.contains("PIG")) { // RIP Technoblade
						} else {
							potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
							
							if (NumberUtils.isNumber(potsblevel)) {
								
								IChatComponent Chatmsg = event.message;
								
								List<IChatComponent> Siblings = Chatmsg.getSiblings();
			    				
								sblevel = Integer.parseInt(potsblevel);
								IChatComponent chatMessageComponent = new ChatComponentText("");
								
								for(int i = 0; i < Siblings.size(); i++) {
									
									String Emblem = "\u00A78] ";
									if(Siblings.get(i).getUnformattedText().contains("[") && Siblings.get(i).getUnformattedText().contains("]")) {
										{
										String Level = Siblings.get(i).getUnformattedText().substring(Siblings.get(i).getUnformattedText().indexOf("[") + 1, Siblings.get(i).getUnformattedText().indexOf("]"));
										if(Level.contains("VIP") || Level.contains("MVP") || Level.contains("ADMIN")
												|| Level.contains("OWNER") || Level.contains("GM") || Level.contains("MOJANG")
												|| Level.contains("EVENTS") || Level.contains("MCP") || Level.contains("NPC")
												|| Level.contains("YOUTUBE") || Level.contains("PIG")) {
											chatMessageComponent.appendSibling(Siblings.get(i));
											gotLevel = true;
											continue;
										} else {
											Emblem = Siblings.get(i).getUnformattedText().substring(Siblings.get(i).getUnformattedText().indexOf("]") - 2);
											if(Level.contains(potsblevel)) {
												Level = Level.substring(2, Level.length() - 2);
											}
										}
										if(Level.equals(potsblevel) && gotLevel == false) {
											if(Config.levelNumber.equals("") && !Config.levelColor.equals("")) {
												IChatComponent componentSbLevel = new ChatComponentText("\u00A7r\u00A78[\u00A7"+Config.levelColor.toLowerCase()+ potsblevel+Emblem);
												componentSbLevel.getChatStyle().setChatClickEvent((Siblings.get(i).getChatStyle().getChatClickEvent()));
												componentSbLevel.getChatStyle().setChatHoverEvent((Siblings.get(i).getChatStyle().getChatHoverEvent()));
												chatMessageComponent.appendSibling(componentSbLevel);
												gotLevel = true;
												
											} else if(!Config.levelColor.equals("") && !Config.levelNumber.equals("")){
												IChatComponent componentSbLevel = new ChatComponentText("\u00A7r\u00A78[\u00A7"+Config.levelColor.toLowerCase()+ Config.levelNumber+Emblem);
												componentSbLevel.getChatStyle().setChatClickEvent((Siblings.get(i).getChatStyle().getChatClickEvent()));
												componentSbLevel.getChatStyle().setChatHoverEvent((Siblings.get(i).getChatStyle().getChatHoverEvent()));
												chatMessageComponent.appendSibling(componentSbLevel);
												gotLevel = true;
												
											} else if(Config.levelColor.equals("") && Config.levelNumber.equals("")){
												isClean = true;
											}
										} else {
										chatMessageComponent.appendSibling(Siblings.get(i));
										continue;
										}
									}
									} else {
										chatMessageComponent.appendSibling(Siblings.get(i));
									}
								}
								lvstrlength = potsblevel.length(); //coconut.jpg? idk may use it in the future lol
								if(isClean == false) {
									event.setCanceled(true);
									chatmessage = chatmessage.substring(chatmessage.indexOf("]") + 1);
									
									Minecraft.getMinecraft().thePlayer.addChatMessage(chatMessageComponent);
								}
						}
					}
				} 
			} 
		} 
		}
	}
}
