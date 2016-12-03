package com.isec.boxreminder.Classes;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Miguel on 01-12-2016.
 */

public class Gravacao {
    MediaRecorder gravacao;
    MediaPlayer reproducao;

    String caminhoFicheiro;

    //INICIA A GRAVACAO
    public void comecaGravar() {
        gravacao = new MediaRecorder();
        gravacao.setAudioSource(MediaRecorder.AudioSource.MIC);
        gravacao.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        gravacao.setOutputFile(caminhoFicheiro);
        gravacao.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            gravacao.prepare();
            gravacao.start();
        } catch (IOException e) {
            Log.e("GRAVACAO", e.toString());
        }
    }

    //PARAR A GRAVACAO
    public void stopGravar() {
        gravacao.stop();
        gravacao.release();
        gravacao = null;
    }

    //REPRODUCAO
    public void reproduzGravacao(){

        try {
            reproducao = new MediaPlayer();
            reproducao.setDataSource(caminhoFicheiro);
            reproducao.prepare();
            reproducao.start();
        } catch (IOException e) {
            Log.e("REPRODUCAO", e.toString());
        }
    }

    public void setCaminhoFicheiro(String caminhoFicheiro) {
        this.caminhoFicheiro = caminhoFicheiro;
    }
}
