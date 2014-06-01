package com.example.apidae;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AudioMain extends Activity{
	final static int REQUESTCODE_RECORDING = 12123;
	Uri savedUri;

	Button start_button = null;
	Button exit_button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.audio_main);

		start_button = (Button)findViewById(R.id.start_audio_recording);
		exit_button = (Button)findViewById(R.id.exit_audio_recording);

		start_button.setOnClickListener(clickListener);
		exit_button.setOnClickListener(clickListener);

	}

	protected void onActivityResult(int requestCode, 
			int resultCode, Intent intent) {
		if (requestCode == REQUESTCODE_RECORDING) {

			if (resultCode == RESULT_OK) {
				savedUri = intent.getData();
				Toast.makeText(getApplicationContext(), "Audio Saved here: "+savedUri.getPath(), Toast.LENGTH_LONG).show();
			}
			else {
				Toast.makeText(getApplicationContext(), "Problem in saving audio", Toast.LENGTH_LONG).show();
			}
		}
		else {
			super.onActivityResult(requestCode, 
					resultCode, intent);
		}
	}

	final View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			
			if (v.getId() == R.id.start_audio_recording) {
				Toast.makeText(getApplicationContext(), "Starting Recording", Toast.LENGTH_LONG).show();
				//startRecording();
				Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
				startActivityForResult(recordIntent, REQUESTCODE_RECORDING);
			}
			else if (v.getId() == R.id.exit_audio_recording) {
				Toast.makeText(getApplicationContext(), "Exit Recording", Toast.LENGTH_LONG).show();
				//stopRecording();
			}
		}
	};
}
