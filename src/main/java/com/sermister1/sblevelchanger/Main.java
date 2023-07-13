package com.sermister1.sblevelchanger;

import java.io.File;

import org.apache.commons.lang3.math.NumberUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "sblevelchanger";
    public static final String VERSION = "1.0";
    
    public static int fsttime = 0;
    public int lvstrlength;
    public static String potsblevel;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "sblevelchanger.cfg");
        Config.init(configFile);

    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	ClientCommandHandler.instance.registerCommand(new CommandColor());
    	FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
    	
        String message = event.message.getUnformattedText();
        String chatmessage = event.message.getFormattedText();
        String nickandlevel = (event.message.getFormattedText().replaceAll("\\:.*", ""));
        	if(nickandlevel.contains(Minecraft.getMinecraft().thePlayer.getName())) {
        		if(message.contains("[") && message.contains("]")) {
        			potsblevel = nickandlevel.substring(nickandlevel.indexOf("[") + 1, nickandlevel.indexOf("]"));
        			
        			if(potsblevel.contains("VIP") || potsblevel.contains("MVP") || potsblevel.contains("ADMIN") || potsblevel.contains("OWNER") || potsblevel.contains("GM") || potsblevel.contains("MOJANG") || potsblevel.contains("EVENTS") || potsblevel.contains("MCP") || potsblevel.contains("NPC") || potsblevel.contains("YOUTUBE") || potsblevel.contains("PIG")) { //RIP Technoblade	
        			} else {	
        				potsblevel = potsblevel.substring(2, potsblevel.length() - 2);
        				if(NumberUtils.isNumber(potsblevel)) {
        					//System.out.println(potsblevel);
	        				lvstrlength = potsblevel.length();
	        				event.setCanceled(true);
	        				chatmessage = chatmessage.substring(chatmessage.indexOf("]") + 1);
	        				//System.out.println(chatmessage);
	        				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7r\u00A78[\u00A7"+Config.levelColor+potsblevel+"\u00A78]"+chatmessage));
        				}
        			}
        		
        		}
        		
        	}        
    }
}
