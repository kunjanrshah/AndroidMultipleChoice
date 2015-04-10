package com.drc.multichoice;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidMultipleChoiceActivity extends Activity implements OnClickListener {
	protected Button selectColoursButton;

	protected CharSequence[] colours = { "Red", "Green", "Blue", "Yellow", "Orange", "Purple" };
	protected ArrayList<CharSequence> selectedColours = new ArrayList<CharSequence>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		selectColoursButton = (Button) findViewById(R.id.select_colours);
		selectColoursButton.setOnClickListener(this);
	}


	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.select_colours:
				showSelectColoursDialog();
				break;

			default:
				break;
		}
	}

	protected void onChangeSelectedColours() {
		StringBuilder stringBuilder = new StringBuilder();

		for(CharSequence colour : selectedColours)
			stringBuilder.append(colour + ",");
		
		selectColoursButton.setText(stringBuilder.toString());
	}

	protected void showSelectColoursDialog() {
		boolean[] checkedColours = new boolean[colours.length];
		int count = colours.length;

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedColours.contains(colours[i]);

		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedColours.add(colours[which]);
				else
					selectedColours.remove(colours[which]);

				onChangeSelectedColours();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Colours");
		builder.setMultiChoiceItems(colours, checkedColours, coloursDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}