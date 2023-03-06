package com.AiVoiceNPCs;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
//Plugin Menu Name
@PluginDescriptor(
		name = "AiVoiceNPCs",
		description = "A Speech to Text plugin that uses AI generated voices for a more immersive experience."
)

public class AiVoiceNPCsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private AiVoiceNPCsConfig config;

	@Override
	protected void startUp() throws Exception
	{

	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			String apiKey = config.getApiKey();
			if (!apiKey.isEmpty()) {
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.getApiKey(), null);
			} else {
				log.info("No API Key provided");
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		String voice = config.getVoice().toString();
		ChatMessageType messageType = chatMessage.getType();
		String messageContents = chatMessage.getMessage();

		//check for dialogue
		if (messageType.toString() == "DIALOG") {

			//Split Message Sender from Message contents
			String[] parts = messageContents.split("\\|", 2);
			String sender = parts[0];
			String message = parts[1];

			log.info("Voice Selected: " + voice);
			log.info("Received dialog message from " + sender);
			log.info("Message: " + message);
		}

	}
	@Provides
	AiVoiceNPCsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AiVoiceNPCsConfig.class);
	}
}
