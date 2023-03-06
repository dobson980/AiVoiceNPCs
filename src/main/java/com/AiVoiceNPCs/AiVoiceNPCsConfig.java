package com.AiVoiceNPCs;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("AiVoiceNPC")
public interface AiVoiceNPCsConfig extends Config
{

	//Available player voices
	enum PlayerVoice
	{
		Adam, Antoni, Arnold, Bella, Domi, Elli, Josh, Rachel, Sam
	}

	//API Key Input Box - has no default value
	@ConfigItem(
			keyName = "API_Key",
			name = "Enter API Key",
			description = "Enter your ElevenLabs API key here"
	)
	default String getApiKey()
	{
		return "";
	}

	//Voice Selection Box - defaults to Adam
	@ConfigItem(
			keyName = "Voice_Type",
			name = "Voice",
			description = "Select your player voice",
			position = 1
	)
	default PlayerVoice getVoice()
	{
		return PlayerVoice.Adam;
	}
}