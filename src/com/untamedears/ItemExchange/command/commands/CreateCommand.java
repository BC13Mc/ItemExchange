package com.untamedears.ItemExchange.command.commands;

import com.untamedears.ItemExchange.ItemExchangePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.untamedears.ItemExchange.command.PlayerCommand;
import com.untamedears.ItemExchange.exceptions.ExchangeRuleParseException;
import com.untamedears.ItemExchange.utility.InteractionResponse;
import com.untamedears.ItemExchange.utility.InteractionResponse.InteractionResult;
import com.untamedears.ItemExchange.utility.ExchangeRule;
import com.untamedears.ItemExchange.utility.ExchangeRule.RuleType;
import static com.untamedears.citadel.Utility.getReinforcement;
import static com.untamedears.citadel.Utility.isReinforced;
import com.untamedears.citadel.entity.PlayerReinforcement;
import org.bukkit.block.Block;

public class CreateCommand extends PlayerCommand {
	public CreateCommand() {
		super("Create Exchange");
		setDescription("Automatically creates an exchange inside the chest the player is looking at");
		setUsage("/iecreate");
		setArgumentRange(0, 1);
		setIdentifiers(new String[] {"iecreate", "iec"});
	}		

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		Player player=(Player) sender;
		Block block=player.getLastTwoTargetBlocks(null,20).get(1).getLocation().getBlock();
		//If no input or ouptut is specified player attempt to set up ItemExchange at the block the player is looking at
		if(args.length==0){
			if(ItemExchangePlugin.ACCEPTABLE_BLOCKS.contains(block.getState().getType())) {
					if ((!ItemExchangePlugin.CITADEL_ENABLED || ItemExchangePlugin.CITADEL_ENABLED && !isReinforced(block)) || 
						(((PlayerReinforcement) getReinforcement(block)).isAccessible(player))){
						InteractionResponse.messagePlayerResult(player,ItemExchangePlugin.createExchange(block.getLocation(),player));
					}
					else{
						InteractionResponse.messagePlayerResult(player,new InteractionResponse(InteractionResult.FAILURE,"You do not have access to that block."));
					}
				}
			else {
				InteractionResponse.messagePlayerResult(player,new InteractionResponse(InteractionResult.FAILURE,"Block in view is not suitable for an Item Exchange."));
			}			
		}
		//Create a RuleBlock in the players inventory
		else {
			//Player much have sapce in their inventory for the RuleBlock
			if(player.getInventory().firstEmpty()!=-1) {
				//If only and input is specified create the RuleBlock based on the item held in the players hand
				if(args.length==1) {
					//Assign a ruleType
					RuleType ruleType=null;
					if(args[0].equalsIgnoreCase("input")) {
						ruleType=ExchangeRule.RuleType.INPUT;
					}
					else if(args[0].equalsIgnoreCase("output")) {
						ruleType=ExchangeRule.RuleType.INPUT;
					}
					if(ruleType!=null) {
						//Creates the ExchangeRule, converts it to an ItemStack and places it in the player's inventory
						player.getInventory().addItem(ExchangeRule.parseItemStack(player.getItemInHand(),ruleType).toItemStack());
						InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResult.SUCCESS,"Created Rule Block!"));
					}
					else {
						InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResult.FAILURE,"Please specify and input or output."));
					
					}
				}
				else if(args.length>=2){
					try {
						//Attemptes to create the ExchangeRule, converts it to an ItemStack and places it in the player's inventory
						player.getInventory().addItem(ExchangeRule.parseCreateCommand(args).toItemStack());
						InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResult.SUCCESS,"Created Rule Block!"));
					}
					catch(ExchangeRuleParseException e) {
						InteractionResponse.messagePlayerResult(player, new InteractionResponse(InteractionResult.FAILURE,"Incorrect entry format."));
					}
				}
			}
			else {
				InteractionResponse.messagePlayerResult(player,new InteractionResponse(InteractionResult.FAILURE,"Player inventory is full!"));
			}
		}
		return true;
	}
	
}