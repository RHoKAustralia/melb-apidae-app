package com.example.apidae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.*;

public class TagSelect extends Activity{

	RadioGroup healthRadioGroup;
	RadioGroup educationRadioGroup;
	RadioGroup moneyRadioGroup;
	RadioGroup foodRadioGroup;
	
	int[] ids = {R.id.radioButtonEducationBad, R.id.radioButtonEducationGood,R.id.radioButtonFoodBad,
			R.id.radioButtonFoodGood,R.id.radioButtonHealthBad,R.id.radioButtonHealthGood,
			R.id.radioButtonMoneyBad,R.id.radioButtonMoneyGood};
	
	Hashtable<Integer,Integer> IDtoTag = new Hashtable<Integer,Integer>();
	
	private void initIDtoTag(){
		IDtoTag.put(ids[0], Tag.RID.EducationBad.value);
		IDtoTag.put(ids[1], Tag.RID.EducationGood.value);
		IDtoTag.put(ids[2], Tag.RID.FoodBad.value);
		IDtoTag.put(ids[3], Tag.RID.FoodGood.value);
		IDtoTag.put(ids[4], Tag.RID.HealthBad.value);
		IDtoTag.put(ids[5], Tag.RID.HealthGood.value);
		IDtoTag.put(ids[6], Tag.RID.MoneyBad.value);
		IDtoTag.put(ids[7], Tag.RID.MoneyGood.value);
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tags_add);
		
		initIDtoTag();

		healthRadioGroup = (RadioGroup)findViewById(R.id.radioGroupHealth);
		educationRadioGroup = (RadioGroup)findViewById(R.id.radioGroupEducation);
		moneyRadioGroup = (RadioGroup)findViewById(R.id.radioGroupMoney);
		foodRadioGroup = (RadioGroup)findViewById(R.id.radioGroupFood);
	}
	
	public void onSaveReturnClick(View v){
		Intent saveIntent = new Intent();
		ArrayList<Integer> tags = new ArrayList<Integer>();
		int hID = healthRadioGroup.getCheckedRadioButtonId();
		if(hID != -1)
			tags.add(IDtoTag.get(hID));
		int eID = educationRadioGroup.getCheckedRadioButtonId();
		if(eID != -1)
			tags.add(IDtoTag.get(eID));
		int mID = moneyRadioGroup.getCheckedRadioButtonId();
		if(mID != -1)
			tags.add(IDtoTag.get(mID));
		int fID = foodRadioGroup.getCheckedRadioButtonId();
		if(fID != -1)
			tags.add(IDtoTag.get(fID));
		
		saveIntent.putExtra(CreateStoryActivity.TAGS_LIST, tags);
		setResult(RESULT_OK, saveIntent);
		finish();
	}
}