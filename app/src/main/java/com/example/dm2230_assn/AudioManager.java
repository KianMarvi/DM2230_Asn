package com.example.dm2230_assn;

import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

public class AudioManager
{

    public final static AudioManager Instance = new AudioManager();
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    private SurfaceView view = null;

    // protect the singleton

    public AudioManager()
    {

    }
    public void Init(SurfaceView _view)
    {
        view = _view;
        Exit();
    }

    public void Exit()
    {
        for (HashMap.Entry<Integer, MediaPlayer> entry: audioMap.entrySet())
        {
            entry.getValue().stop();
            entry.getValue().reset();
            entry.getValue().release();
        }

        audioMap.clear();
    }

    public void PlayAudio(int _id, float _volume)
    {
        if (audioMap.containsKey(_id)) {
            MediaPlayer curr = audioMap.get(_id);
            curr.seekTo(0);
            curr.setVolume(_volume, _volume);
            curr.start();
        }
        else
        {
            // Load the audio and play immediately
            MediaPlayer curr = MediaPlayer.create(view.getContext(), _id);
            audioMap.put(_id, curr);
            curr.start();
        }
    }

    // Stop the audio
    public void StopAudio(int _id)
    {
        MediaPlayer Audio = audioMap.get(_id);
        Audio.pause();
    }
}
