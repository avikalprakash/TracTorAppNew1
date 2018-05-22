package com.lueinfo.tractorapp.Utils;

/**
 * Created by Fujitsu on 18-09-2017.
 */

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class AudioRecorder {

    private MediaRecorder recorder ;

    String AudioSavePathInDevice = null;
   // MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;

    private File outfile = null;
    private String filepath = "MyFileStorage";
    private static final String TAG="RecordActivity";
   public String a1;
    public AudioRecorder(){}

    public void startRecording(String audioFile) throws IOException {
        try {
            String state = Environment.getExternalStorageState();
            if (!state.equals(Environment.MEDIA_MOUNTED)) {
                throw new IOException("SD Card is not mounted.  It is " + state + ".");
            }

            // make sure the directory we plan to store the recording in exists
            File directory = new File(File.pathSeparator).getParentFile();
       /* if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created.");
        }*/
        }catch (Exception e){
        }

//            File storageDir = new File(Environment
//                    .getExternalStorageDirectory(), "/Cloud 9/");
//            storageDir.mkdir();
//            outfile=File.createTempFile(audioFile, ".wav",storageDir);
//            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
//            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            recorder.setOutputFile(outfile.getAbsolutePath());
//             a1= outfile.getAbsolutePath();
//            Log.d("recorded string file", a1);
//            Log.i(TAG, "Saving in Directory");

//        AudioSavePathInDevice =
//                Environment.getExternalStorageDirectory().getAbsolutePath()+
//                        "/Cloud 9/" + audioFile + ".mp3";

                    File storageDir = new File(Environment
                    .getExternalStorageDirectory(), "/Cloud 9/");
            storageDir.mkdir();
            outfile= File.createTempFile(audioFile, ".mp3",storageDir);

        MediaRecorderReady();


        try{
            recorder.prepare();
            recorder.start();
        }catch(IllegalStateException e){
            e.printStackTrace();
        }


    }

    public void stop() throws IOException {
        recorder.stop();
        //recorder.release();
    }

    public String fetchaudio(){

        return a1;
    }

    public void MediaRecorderReady(){
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(outfile.getAbsolutePath());
        a1 = outfile.getAbsolutePath();
    }

    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }


}
