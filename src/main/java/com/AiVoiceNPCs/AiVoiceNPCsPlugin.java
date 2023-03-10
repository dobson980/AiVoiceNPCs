package com.AiVoiceNPCs;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.InteractingChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private AudioPlayer audioPlayer;
	private NPCVoiceVariationMap voiceVariety;
	private String playerName;
	private RetrieveAudio retrieveAudio;
	private int npcID;
	private int voiceNumber;
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Inject
	private AiVoiceNPCsConfig config;

	@Override
	protected void startUp() throws Exception
	{
		// Create an instance of the ApiCaller class
		audioPlayer = new AudioPlayer();
		retrieveAudio = new RetrieveAudio();
		voiceVariety = new NPCVoiceVariationMap();

	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged event)
	{
		if (event.getTarget() != null && event.getTarget() instanceof NPC)
		{
			NPC currentNpc = (NPC) event.getTarget();
			npcID = currentNpc.getId();
			voiceNumber = voiceVariety.mapVariation(npcID);
			//log.info("Interacting with NPC: {}", npcID);
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{

		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage) throws IOException {
		ChatMessageType messageType = chatMessage.getType();
		String messageContents = chatMessage.getMessage();

		//check for dialogue
		if (messageType.toString() == "DIALOG") {

			//get playerName if not Set
			if(playerName == null) {
				playerName = client.getLocalPlayer().getName();
			}

			//Split Message Sender from Message contents
			String[] parts = messageContents.split("\\|", 2);
			String sender = parts[0];
			String message = parts[1];
			File audioFile;

			if(sender.equals(playerName)) {

				audioFile = retrieveAudio.getAudio("Player", message, 1);

			} else {
				log.info("NPC: "+npcID);
				audioFile = retrieveAudio.getAudio(sender, message, voiceNumber);

			}
			if (audioFile != null) {
				if(audioPlayer.isPlaying()) {
					audioPlayer.stop();
					audioPlayer.play(audioFile);
				} else {
					audioPlayer.play(audioFile);
				}
			} else {
				//no audio file - do nothing
				log.warn("NO AUDIO FOUND | Sender: " + sender + " Message: " + message);

			}


		}

	}
	@Provides
	AiVoiceNPCsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AiVoiceNPCsConfig.class);
	}
}
