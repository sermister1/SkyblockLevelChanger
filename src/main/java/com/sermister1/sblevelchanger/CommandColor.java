package com.sermister1.sblevelchanger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;

public class CommandColor extends CommandBase {
	// Command name
    @Override
    public String getCommandName() {
        return "clc";
    }

    // Command usage
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/clc <color> [custom level]";
    }

    // Execute the command
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
        	String reset = "reset";
        	String test = args[0];
        	
            // Get the argument and store it in the "color" variable
        	if(args[0].equals(reset)) {
        		Config.levelColor = "";
        		Config.levelNumber = "";
        		Config.config.get(Configuration.CATEGORY_GENERAL, "levelcolor",  "", "The color for the level").set("");
        		Config.config.get(Configuration.CATEGORY_GENERAL, "levelnumber", "", "Level number").set("");
	            Config.config.save();
	            GuiTabPlayerName GTPN = new GuiTabPlayerName();
	        	GTPN.TabListDisplayNameCommand();

	            sender.addChatMessage(new ChatComponentText("Level reset; tab list will update only after server sends update to it"));
        	} else {
	        	if(args.length == 1) {
		            Config.levelColor = args[0];
		            //Config.config.save();
		            Config.config.get(Configuration.CATEGORY_GENERAL, "levelcolor",  "", "The color for the level").set(Config.levelColor);
		            Config.config.save();
		            // Do whatever you want with the color variable here
		            // For example, you can store it in a global variable or use it in another method
		            GuiTabPlayerName GTPN = new GuiTabPlayerName();
		        	GTPN.TabListDisplayNameCommand();
		            sender.addChatMessage(new ChatComponentText("Color set to " + "\u00A7"+Config.levelColor+Config.levelColor));
	        	} else {
	        		Config.prevLevel = Config.levelNumber;
	        		Config.levelColor = args[0];
	        		Config.levelNumber = args[1];
	        		
		            //Config.config.save();
	        		Config.config.get(Configuration.CATEGORY_GENERAL, "levelcolor",  "", "The color for the level").set(Config.levelColor);
		            Config.config.get(Configuration.CATEGORY_GENERAL, "levelnumber", "", "Level number").set(Config.levelNumber);
		            Config.config.save();
		            // Do whatever you want with the color variable here
		            // For example, you can store it in a global variable or use it in another method
		            GuiTabPlayerName GTPN = new GuiTabPlayerName();
		        	GTPN.TabListDisplayNameCommand();
		            sender.addChatMessage(new ChatComponentText("Color set to " + "\u00A7"+Config.levelColor+Config.levelColor+"\u00A7f, Level set to "+Config.levelNumber));
	        	}
        	}
        } else {
        	
        	if(Main.sblevel == 0) {
        		sender.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(sender)));
        		sender.addChatMessage(new ChatComponentText("/clc reset - reset color and level to their initial state. Warning: due to how Minecraft works, level in tab list will be reset only on tab list update"));
                sender.addChatMessage(new ChatComponentText("0 - \u00A70Black - " + 100));
                sender.addChatMessage(new ChatComponentText("1 - \u00A71Dark Blue - " + 100));
                sender.addChatMessage(new ChatComponentText("2 - \u00A72Dark Green - " + 100));
                sender.addChatMessage(new ChatComponentText("3 - \u00A73Dark Aqua - " + 100));
                sender.addChatMessage(new ChatComponentText("4 - \u00A74Dark Red - " + 100));
                sender.addChatMessage(new ChatComponentText("5 - \u00A75Dark Purple - " + 100));
                sender.addChatMessage(new ChatComponentText("6 - \u00A76Gold - " + 100));
                sender.addChatMessage(new ChatComponentText("7 - \u00A77Gray - " + 100));
                sender.addChatMessage(new ChatComponentText("8 - \u00A78Dark Gray - " + 100));
                sender.addChatMessage(new ChatComponentText("9 - \u00A79Blue - " + 100));
                sender.addChatMessage(new ChatComponentText("a - \u00A7aGreen - " + 100));
                sender.addChatMessage(new ChatComponentText("b - \u00A7bAqua - " + 100));
                sender.addChatMessage(new ChatComponentText("c - \u00A7cRed - " + 100));
                sender.addChatMessage(new ChatComponentText("d - \u00A7dLight Purple - " + 100));
                sender.addChatMessage(new ChatComponentText("e - \u00A7eYellow - " + 100));
                sender.addChatMessage(new ChatComponentText("f - \u00A7fWhite - " + 100));
                sender.addChatMessage(new ChatComponentText("z - \u00A7zSBA Chroma(if SBA is installed) - " + 100));
        	} else {
        		sender.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(sender)));
        		sender.addChatMessage(new ChatComponentText("/clc reset - reset color and level to their initial state. Warning: due to how Minecraft works, level in tab list will be reset only on tab list update"));
                sender.addChatMessage(new ChatComponentText("0 - \u00A70Black - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("1 - \u00A71Dark Blue - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("2 - \u00A72Dark Green - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("3 - \u00A73Dark Aqua - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("4 - \u00A74Dark Red - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("5 - \u00A75Dark Purple - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("6 - \u00A76Gold - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("7 - \u00A77Gray - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("8 - \u00A78Dark Gray - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("9 - \u00A79Blue - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("a - \u00A7aGreen - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("b - \u00A7bAqua - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("c - \u00A7cRed - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("d - \u00A7dLight Purple - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("e - \u00A7eYellow - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("f - \u00A7fWhite - " + Main.sblevel));
                sender.addChatMessage(new ChatComponentText("z - \u00A7zSBA Chroma(if SBA is installed) - " + Main.sblevel));
                //sender.addChatMessage(new ChatComponentText("Warning: "));
        	}
            
        }
    }

    // Check if the command can be executed by a player
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
    
}
