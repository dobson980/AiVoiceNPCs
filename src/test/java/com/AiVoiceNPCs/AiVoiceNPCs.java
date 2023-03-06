package com.AiVoiceNPCs;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AiVoiceNPCs
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AiVoiceNPCsPlugin.class);
		RuneLite.main(args);
	}
}