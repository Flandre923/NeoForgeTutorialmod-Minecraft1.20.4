package net.flandre923.examplemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class ExampleCommand implements Command<CommandSourceStack> {
    public static final Command<CommandSourceStack> INSTANCE = new ExampleCommand();
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        commandContext.getSource().sendSystemMessage(Component.literal("cmd.example.hello"));
        return 0;
    }
}
