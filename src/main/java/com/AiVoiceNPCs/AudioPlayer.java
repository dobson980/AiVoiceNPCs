package com.AiVoiceNPCs;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip;

    public void play(File audioFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
