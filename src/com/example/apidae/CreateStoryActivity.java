package com.example.apidae;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class CreateStoryActivity extends Activity{
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_FILE = 1899;
	
	public static String NAME = "Name";
	public static String TAGS_LIST = "TagsList";
	
	
	EditText storyNameField;
	LinearLayout imageScrollLayout;
	LinearLayout audioScrollLayout;
	LinearLayout tagScrollLayout;

	ImageButton imageAddBtn;
	ImageButton audioAddBtn;
	ImageButton tagAddBtn;
	
	//Tag[] tags;
	ArrayList<Bitmap> photos = new ArrayList<Bitmap>();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_story);
		
		Bundle data = getIntent().getExtras();
		if(data != null){
			String[] tags = data.getStringArray(TAGS_LIST);
			String storyName = data.getString(NAME);
		}

		imageScrollLayout = (LinearLayout) findViewById(R.id.photoScroll);
		audioScrollLayout = (LinearLayout) findViewById(R.id.audioScroll);
		tagScrollLayout = (LinearLayout) findViewById(R.id.tagScroll);

		imageAddBtn = (ImageButton)findViewById(R.id.photoImageButton);
		audioAddBtn = (ImageButton)findViewById(R.id.audioImageButton);
		tagAddBtn = (ImageButton)findViewById(R.id.tagImageButton);
	}
	
	public void onAddPhotoClick(View v){
		
		final CharSequence[] items = { "Take Photo", "Cancel" };
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Add Photo");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
	                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}
	
	private void updateImageScroll(){
		imageScrollLayout.removeAllViews();
		for( Bitmap b : photos){
			ImageView picB = new ImageView(this);
			picB.setImageBitmap(b);
			picB.setScaleType(ScaleType.FIT_CENTER);
			LayoutParams lp = new LayoutParams((int)(b.getWidth()*2), LayoutParams.MATCH_PARENT, 1);
			imageScrollLayout.addView(picB, lp);
		}
		if(photos.size()>0){
			imageAddBtn.setImageResource(R.drawable.cam_button_pressed);
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		super.onActivityResult(requestCode, resultCode, data); 
		switch(requestCode) {
			case CAMERA_REQUEST:  
			    if(resultCode == RESULT_OK){ 
		            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
		            photos.add(photo);
			    }
            	break;
            /*	
			case SELECT_FILE:
			    if(resultCode == RESULT_OK){  
			    	Uri selectedImage = data.getData();
			         String[] filePathColumn = { MediaStore.Images.Media.DATA };
			 
			         Cursor cursor = getContentResolver().query(selectedImage,
			                 filePathColumn, null, null, null);
			         cursor.moveToFirst();
			 
			         int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			         String picturePath = cursor.getString(columnIndex);
			         cursor.close();
			         
			         photos.add(BitmapFactory.decodeFile(picturePath));
			    }
			    break;*/
		}
		updateImageScroll();
    } 
}
