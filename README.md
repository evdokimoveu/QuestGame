# QuestGame
xml files need put into "game" folder



XML format:
<?xml version="1.0" encoding="utf-8"?>
<file id="1" type="file" name="1_game.xml">
	<action id="1" type="action" text="">
		<var id="1" move_to="f1a2" damage="">
      some text
		</var>		
		<var id="2" move_to="f1a4" damage="-50">
		  some text
		</var>
	</action>

	<action id="2" type="action" text="">
    ......
	</action>
	<action id="-1" type="action" text=""/>
</file>


Action without vars signals about game finish. 
Vars id into one action must be unique.
Actions id into one file must be unique.
