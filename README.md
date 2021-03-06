#Item Exchange

Item Exchange is a minecraft shop mod that enables different chests (and other inventory blocks) to perform an exchange of items with a player. The two items which an inventory block and player can exchange are defined by special rule items stored in the inventory block. An exchange rule item is a stone button generated by via the mod which contains information detailing either the input or output item stack of an exchange within its lore. For an inventory block to perform an exchange it must contain both an input exchange rule item and an output exchange rule item, if it does when a player left clicks it while holding the correct input item type an exchange will be conducted.

###[Video Tutorial of Basics](http://www.youtube.com/watch?v=uLIy3UlvAz0&feature=youtu.be)

##Interacting with exchanges

When a player left clicks an inventory block containing exchange rules it will first report the input and outputs of the exchange. If the player subsequently clicks the inventory block while holding the current input an exchange between the player and the inventory block will be conducted. An inventory block may contain multiple sets of input and output rules, if this is the case then the player can cycle through the different exchanges by clicking on the inventory block with their fist. When placing multiple sets of rules in a inventory block The first input will be matched with the first output and so on.

##Creating an Exchange

A single command, "/iecreate" is used to create exchanges, it can be used in a variety of ways as detailed below.

###/iecreate (or /iec) [input or output] [common name* or ID:durability] [amount]

-  "/iec": If a player is looking at an acceptable inventory block (for example a chest) which contains exactly two types of items, multiple stacks are okay, it will create an exchange with the first item as the input and the second item as the output. The player requires citadel access to the block to operate this command.
-  "/iec [input (or i) or output (or o)]": If an input or output is specified an item exchange rule that represents the item stack held in the players hand is created and placed in the player's inventory.
-  "/iec [input or output] [common name* or ID:durability]": If common name* (example: Blue Wool) or a material ID:durability (example: 35:11) is specified in addition to an input or output an exchange rule item is created in the player's inventory representing the specified ItemStack with an amount of one.
-  "/iec [input or output] [common name* or ID:durability] [amount]": If an amount is specified this command performs similiar to the one above except with an amount set as specified.

##Editing an Exchange

After an exchange rule is created its various fields can be changed through the set command.

###/ieset (or /ies) \<field\> [value]

This command allows you to change specific values of an exchange rule item held in the players hand.

*Fields*

-  "/ies commonname (or c)": Changes the item to that specified by the given common name. 
-  "/ies material (or m)": Changes the material of the item to that given, either a bukkit material name or a minecraft ID number may be used.
-  "/ies durability (or d)": Changes the durability (aka data) value of the item.
-  "/ies amount (or a)": Changes amount of the item
-  "/ies enchantment (or e)": In the format \<+/?/-\>\<enchantment abbrv.\>\<level\>. A "+" requires the enchantment for the item, a "-" excludes the enchantment from that item, and a "?" makes the enchantment neither required nor excluded. The enchantment abbrv. is one of the following two letter codes: "_____".
-  "/ies [allowenchantments denyenchantments]": This sets whether or not to allow enchantments not explicitly listed. For example, if you want a Looting 3 (L3) sword that doesn't have Fire Aspect 1 (FA1) or Fire Aspect 2 (FA2), but could have other enchantments like Sharpness 1 (S1), you would need the following commands: "/iec o Diamond Sword", "/ies e +L3", "/ies e -FA1", "/ies e -FA2", "/ies allowenchantments".
-  "/ies displayname (or n)": Changes the display of the item, if no value is given removes the requirement for a DisplayName. Note: An item which has never been renamed will not have a DisplayName field, but an item renamed to its original name will have a DisplayName field.
-  "/ies lore (or l)": Changes the lore of the item,  multiple lines of lore can be entered by placing a ";" at each linebreak, if no value is given remove the requirement for lore.
-  "/ies switchio (or s)": Toggles the item rule between an input and an output, does not require a value.
-  "/ies group": Retricts use of the exchange to members of that citadel group. Only works on input RuleBlocks.

##Bulk exchange rules
It is possible to merge multiple exchange rules into one item by putting them all in a crafting grid together. This will create a bulk exchange rule.
-  Any amount of exchange rules and bulk exchange rules can be put into the grid to craft a new bulk exchange rule.
-  Dropping a bulk exchange rule will convert it into the exchange rules that it represented.
-  When an ItemExchange is interacted with bulk rules are "metaphorically" seperated into their individual input and output rules in the same order they were crafted in, these are then handled like normal rule blocks, where the first input is matched to the first output.

##Redstone support
After a successful transaction, the item exchange will power a button attached to the block behind the exchange. (only works for chests, trapped chests, and furnaces, currently)
![Image showing a button behind a chest.](http://imgur.com/OQaoaVu.png)
![Another image showing a button behind a chest.](http://imgur.com/nGnu83v.png)

##Other Notes
-  Shift left clicking and shift right clicking can respectively be used to decrease or increase the amount required by a particular RuleBlock
-  To increase usability where possible the english displayed name (referred to as "common name") is used in place of the bukkit material name and durability value.
-  Current accepted inventory blocks are chests, double chests, dispensors, trapped chests and double trapped chests. Nothing prohibits additional inventory blocks from being added except for testing time.
