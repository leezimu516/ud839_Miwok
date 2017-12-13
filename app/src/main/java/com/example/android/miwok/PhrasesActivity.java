/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAm;

    // audio focus change listener
    final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // resume play the audio file
                mMediaPlayer.start();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // stop the MediaPlayer and release the resource
                mMediaPlayer.stop();
                releaseMediaPlayer();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                //pause the audio
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //pause the audio
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        // create the {@link AudioManager} to request audio focus
        mAm = (AudioManager) getSystemService(AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",  R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",  R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",  R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",  R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",  R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",  R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",  R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",  R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",  R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView)findViewById(R.id.phases_list);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);

                //release the media player if it currently exists because we are about to play a different file
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                if (requestFocus()) {
                    mMediaPlayer = MediaPlayer.create(getBaseContext(), word.getAudioResourceId());
                    mMediaPlayer.start();

                    //setup a listener on the media player, so that we can stop and release
                    //media player once the sound has finished
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    // request audio foucs, return true if requested
    private boolean requestFocus() {
        int result = mAm.requestAudioFocus(
                audioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    @Override
    protected void onStop() {
        super.onStop();
        // when the activity is stopped, release the media player resource because we wont need them
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAm.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}
