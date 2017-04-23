import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;

public class AudioPlayer {

        private Clip clip;
        private File file;

        public AudioPlayer(File file) {
            this.file = file;
        }

        public boolean isPlaying() {
            return clip != null && clip.isRunning();
        }

        protected void open() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        }

        public void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
            if (clip == null || !clip.isRunning()) {
                open();
                //clip.setFramePosition(0);
                clip.start();
            }
        }

        public void stop() {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.flush();
                dispose();
            }
        }

        public void dispose() {
            try {
                clip.close();
            } finally {
                clip = null;
            }
        }

    }
	
