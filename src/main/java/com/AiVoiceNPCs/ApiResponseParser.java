package com.AiVoiceNPCs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class ApiResponseParser {
    public void parseApiResponse(String response) {
        // Parse the JSON response using GSON
        JsonParser parser = new JsonParser();
        JsonObject jsonResponse = parser.parse(response).getAsJsonObject();
        JsonArray voices = jsonResponse.getAsJsonArray("voices");

        // Extract the voice_id and name values from each voice object
        for (JsonElement voice : voices) {
            JsonObject voiceObj = voice.getAsJsonObject();
            String voiceId = voiceObj.get("voice_id").getAsString();
            String name = voiceObj.get("name").getAsString();

            // Do something with the voice_id and name values
            System.out.println("Voice ID: " + voiceId);
            System.out.println("Name: " + name);
        }
    }

    public String getVoiceIdForName(String response, String name) {
        // Parse the JSON response using GSON
        JsonParser parser = new JsonParser();
        JsonObject jsonResponse = parser.parse(response).getAsJsonObject();
        JsonArray voices = jsonResponse.getAsJsonArray("voices");

        // Look for voice_id of the given name
        for (JsonElement voice : voices) {
            JsonObject voiceObj = voice.getAsJsonObject();
            String voiceName = voiceObj.get("name").getAsString();
            if (voiceName.equals(name)) {
                return voiceObj.get("voice_id").getAsString();
            }
        }

        return null; // Name not found
    }

}
