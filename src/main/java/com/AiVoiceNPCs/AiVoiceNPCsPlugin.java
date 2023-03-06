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

import javax.inject.Inject;
import java.io.IOException;

import static net.runelite.http.api.RuneLiteAPI.GSON;

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

	private ApiCaller apiCaller;

	@Inject
	private AiVoiceNPCsConfig config;

	@Override
	protected void startUp() throws Exception
	{
		// Create an instance of the ApiCaller class
		apiCaller = new ApiCaller();
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
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Your AI API Key: " + config.getApiKey(), null);

				// Make an API call to get available voices
				String apiUrl = "https://api.elevenlabs.io/v1/voices";
				try {
					String response = apiCaller.callApi(apiUrl, apiKey);

					// Parse the API response using ApiResponseParser class and Look up the voice_id for the selected name
					ApiResponseParser parser = new ApiResponseParser();
					String voiceId = parser.getVoiceIdForName(response, config.getVoice().toString());
					System.out.println("Voice ID for " + config.getVoice() + ": " + voiceId);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				log.info("No API Key provided");
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		ChatMessageType messageType = chatMessage.getType();
		String messageContents = chatMessage.getMessage();

		//check for dialogue
		if (messageType.toString() == "DIALOG") {

			//Split Message Sender from Message contents
			String[] parts = messageContents.split("\\|", 2);
			String sender = parts[0];
			String message = parts[1];

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
