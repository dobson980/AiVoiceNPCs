package com.AiVoiceNPCs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class RetrieveAudio {
    private static final Logger log = Logger.getLogger(RetrieveAudio.class.getName());

    public File getAudio(String folderPath, String message, int voicenumber) {
        // Find JSON file
        File folder = new File("src/main/resources/" + folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null || files.length == 0) {
            log.info("No .json file found in src/main/resources/"+folderPath);
            return null;

        }

        File jsonFile = files[0];
//        log.info("Found Files: "+jsonFile);

        // Read JSON file and extract audio file name
        List<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String jsonString = String.join("", lines);
//        log.info("Json String: "+jsonString);

        // Parse JSON string using Gson
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(jsonString).getAsJsonArray();
        JsonObject obj = jsonArray.get(0).getAsJsonObject();
        JsonArray messagesArray = obj.getAsJsonArray("dialogue");

        String audioFileName = null;
        boolean found = false;
        for (JsonElement element : messagesArray) {
            JsonObject messageObj = element.getAsJsonObject();
            String currentMessage = messageObj.get("text").getAsString();

            if (currentMessage.equals(message)) {
                audioFileName = messageObj.get("audio_file").getAsString();
                found = true;
//                log.info("found message: "+ message);
                break;
            }
        }

        if (!found || audioFileName == null) {
//            log.info("didn't find message");
            return null;
        }

        // Check if audio file exists
        File audioFile = new File(folder.getParent() + "\\" + folderPath + "\\media\\" + audioFileName.substring(0, audioFileName.length() - 4) + "-"+voicenumber+".wav");

        if (!audioFile.exists()) {
            log.info("did not find audio file: " + folder.getParent() + "\\" + folderPath + "\\media\\" + audioFileName.substring(0, audioFileName.length() - 4) + "-"+voicenumber+".wav");
            return null;
        }

        return audioFile;
    }
}
