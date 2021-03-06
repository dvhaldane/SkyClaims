package net.mohron.skyclaims.command;

import net.mohron.skyclaims.SkyClaims;
import net.mohron.skyclaims.lib.Permissions;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

import static net.mohron.skyclaims.PluginInfo.NAME;
import static net.mohron.skyclaims.PluginInfo.VERSION;

public class CommandHelp implements CommandExecutor {

	private static final SkyClaims PLUGIN = SkyClaims.getInstance();
	public static String helpText = String.format("display info on %s's commands and their uses.", NAME);
	public static CommandSpec commandSpec = CommandSpec.builder()
			.description(Text.of(helpText))
			.executor(new CommandHelp())
			.build();

	public static void register() {
		try {
			PLUGIN.getGame().getCommandManager().register(PLUGIN, commandSpec);
			PLUGIN.getLogger().info("Registered command: CommandHelp");
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			PLUGIN.getLogger().error("Failed to register command: CommandHelp");
		}
	}

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Text helpContents = Text.EMPTY;
		boolean hasPerms = false;

		if (src.hasPermission(Permissions.COMMAND_CREATE)) {
			helpContents = Text.join(helpContents, Text.builder("is create").onClick(TextActions.runCommand("is create")).append(Text.of(TextColors.DARK_GREEN, " - ", CommandCreate.helpText)).build());
			hasPerms = true;
		}

		if (src.hasPermission(Permissions.COMMAND_RESET)) {
			helpContents = Text.join(helpContents, Text.builder("is reset").onClick(TextActions.runCommand("is reset")).append(Text.of(TextColors.DARK_GREEN, " - ", CommandReset.helpText)).build());
			hasPerms = true;
		}

		if (hasPerms) {
			PaginationList.Builder paginationBuilder = PaginationList.builder().title(Text.of(TextColors.AQUA, NAME, " Help")).padding(Text.of("-")).contents(helpContents);
			paginationBuilder.sendTo(src);
		} else {
			src.sendMessage(Text.of(NAME + " " + VERSION));
		}

		return CommandResult.success();
	}
}