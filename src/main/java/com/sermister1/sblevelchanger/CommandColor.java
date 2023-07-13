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
        return "/clc <color>";
    }

    // Execute the command
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            // Get the argument and store it in the "color" variable
            Config.levelColor = args[0];
            //Config.config.save();
            Config.config.get(Configuration.CATEGORY_GENERAL, "levelcolor",  "f", "The color for the level").set(Config.levelColor);
            Config.config.save();
            // Do whatever you want with the color variable here
            // For example, you can store it in a global variable or use it in another method
            sender.addChatMessage(new ChatComponentText("Color set to " + "\u00A7"+Config.levelColor+Config.levelColor));
        } else {
            sender.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(sender)));
            sender.addChatMessage(new ChatComponentText("0 - \u00A70Black - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("1 - \u00A71Dark Blue - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("2 - \u00A72Dark Green - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("3 - \u00A73Dark Aqua - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("4 - \u00A74Dark Red - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("5 - \u00A75Dark Purple - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("6 - \u00A76Gold - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("7 - \u00A77Gray - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("8 - \u00A78Dark Gray - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("9 - \u00A79Blue - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("a - \u00A7aGreen - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("b - \u00A7bAqua - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("c - \u00A7cRed - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("d - \u00A7dLight Purple - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("e - \u00A7eYellow - " + Main.potsblevel));
            sender.addChatMessage(new ChatComponentText("f - \u00A7fWhite - " + Main.potsblevel));
        }
    }

    // Check if the command can be executed by a player
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
    
}
