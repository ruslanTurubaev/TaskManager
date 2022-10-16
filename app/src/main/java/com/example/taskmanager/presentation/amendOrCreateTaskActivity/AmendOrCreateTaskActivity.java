package com.example.taskmanager.presentation.amendOrCreateTaskActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.data.utils.Constants;
import com.example.taskmanager.R;
import com.example.taskmanager.presentation.mainActivity.MainActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AmendOrCreateTaskActivity extends AppCompatActivity {
    private EditText etUser, etInputDocumentType, etInputDocumentNumber, etInputDocumentDate, etInputDocumentContent, etOutcomeDocumentType, etOutcomeDocumentNumber, etOutcomeDocumentDate, etOutcomeDocumentContent, etOutcomeDocumentAddress, etStatus;
    private Button deleteButton, saveButton;
    private String taskId;

    private AmendOrCreateViewModel amendOrCreateViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_or_create_task);

        LinearLayout linearLayout = findViewById(R.id.amend_or_create_task_background);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(30);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        amendOrCreateViewModel = new ViewModelProvider(this).get(AmendOrCreateViewModel.class);

        etUser = findViewById(R.id.etUser);
        etInputDocumentType = findViewById(R.id.etInputDocumentType);
        etInputDocumentNumber = findViewById(R.id.etInputDocumentNumber);
        etInputDocumentDate = findViewById(R.id.etInputDocumentDate);
        etInputDocumentContent = findViewById(R.id.etInputDocumentContent);
        etOutcomeDocumentType = findViewById(R.id.etOutcomeDocumentType);
        etOutcomeDocumentNumber = findViewById(R.id.etOutcomeDocumentNumber);
        etOutcomeDocumentDate = findViewById(R.id.etOutcomeDocumentDate);
        etOutcomeDocumentContent = findViewById(R.id.etOutcomeDocumentContent);
        etOutcomeDocumentAddress = findViewById(R.id.etOutcomeDocumentAddress);
        etStatus = findViewById(R.id.etStatus);
        deleteButton = findViewById(R.id.delete_button);
        saveButton = findViewById(R.id.save_button);

        etInputDocumentType.setShowSoftInputOnFocus(false);
        etOutcomeDocumentType.setShowSoftInputOnFocus(false);
        etStatus.setShowSoftInputOnFocus(false);
        etUser.setFocusable(false);

        etInputDocumentType.setOnFocusChangeListener((view, b) -> {
            if(etInputDocumentType.isFocused()){
                showTypeWindow(etInputDocumentType);
            }
        });

        etOutcomeDocumentType.setOnFocusChangeListener((view, b) -> {
            if(etOutcomeDocumentType.isFocused()){
                showTypeWindow(etOutcomeDocumentType);
            }
        });

        etStatus.setOnFocusChangeListener((view, b) -> {
            if(etStatus.isFocused()){
                showStatusWindow();
            }
        });

        enableAmendmentMode();

        /*
        *If task id exist, viewModel will download it from database by its id
        * all fields will be filled and be enabled for amendments
         */
        amendOrCreateViewModel.getTaskDomainModelMutableLiveData().observe(this, task ->{
            etUser.setText(task.getUserName());
            etInputDocumentType.setText(task.getInputDocumentType());
            etInputDocumentNumber.setText(task.getInputDocumentNumber());
            etInputDocumentDate.setText(task.getInputDocumentDate());
            etInputDocumentContent.setText(task.getInputDocumentContent());
            etOutcomeDocumentType.setText(task.getOutcomeDocumentType());
            etOutcomeDocumentNumber.setText(task.getOutcomeDocumentNumber());
            etOutcomeDocumentDate.setText(task.getOutcomeDocumentDate());
            etOutcomeDocumentContent.setText(task.getOutcomeDocumentContent());
            etOutcomeDocumentAddress.setText(task.getOutcomeDocumentAddress());
            etStatus.setText(task.getOutcomeDocumentStatus());
        });

        /*
        *check whether user id in task equal with the id of the current user
        *if yes task will be able for amendments
        * if not task will be open for view only
         */
        amendOrCreateViewModel.getIsMyTaskMutableLiveData().observe(this, isMyTask ->{
            if(isMyTask){
                deleteButton.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
            }
            else {
                deleteButton.setVisibility(View.GONE);
                saveButton.setVisibility(View.GONE);
            }
        });

        amendOrCreateViewModel.getUserNameMutableLiveData().observe(this, userName ->{
            etUser.setText(userName);
        });

    }

    /*
    *check whether task id is exist
    * If yes, download task by its id, delete button is enabled
    * if not, it is a new task, delete button is disabled
     */
    private void enableAmendmentMode(){
        taskId = getIntent().getStringExtra(Constants.ID);
        if(taskId != null){
            deleteButton.setVisibility(View.VISIBLE);
            amendOrCreateViewModel.getTask(taskId, this);
        }
        else{
            deleteButton.setVisibility(View.GONE);
            amendOrCreateViewModel.getUserName();
        }
    }

    public void showTypeWindow(EditText focusedEditText){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        LayoutInflater inflater = LayoutInflater.from(this);
        View typeWindow = inflater.inflate(R.layout.type_window, null);

        if(focusedEditText.getId() == R.id.etInputDocumentType){
            RadioButton radioButton = typeWindow.findViewById(R.id.rbInitiative);
            radioButton.setVisibility(View.GONE);
        }

        dialog.setView(typeWindow);

        dialog.setNegativeButton("Delete", (dialogInterface, i) -> {
            focusedEditText.setText("");
            dialogInterface.dismiss();
        });

        dialog.setNeutralButton("Cancel", (dialog1, which) -> dialog1.dismiss());

        dialog.setPositiveButton("Accept", (dialogInterface, i) -> {
            RadioGroup radioGroup = typeWindow.findViewById(R.id.rgTypeWindow);
            RadioButton radioButton = typeWindow.findViewById(radioGroup.getCheckedRadioButtonId());
            if(radioButton != null) {
                focusedEditText.setText(radioButton.getText());
            }
            checkOutcomeDocumentType();
        });

        dialog.show();
    }

    public void showStatusWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        LayoutInflater inflater = LayoutInflater.from(this);
        View statusWindow = inflater.inflate(R.layout.status_window, null);
        dialog.setView(statusWindow);

        dialog.setNegativeButton("Delete", (dialogInterface, i) -> {
            etStatus.setText("");
            dialogInterface.dismiss();
        });

        dialog.setNeutralButton("Cancel", (dialogInterface, which) -> dialogInterface.dismiss());

        dialog.setPositiveButton("Accept", (dialogInterface, i) -> {
            RadioGroup radioGroup = statusWindow.findViewById(R.id.rgStatus);
            RadioButton radioButton = statusWindow.findViewById(radioGroup.getCheckedRadioButtonId());
            if(radioButton != null) {
                etStatus.setText(radioButton.getText());
            }
        });

        dialog.show();
    }

    /*
    *If type of an outcome document is initiative, then there will be no information about income document
    *Therefore fields related to an income document will be disabled for filling
     */
    private void checkOutcomeDocumentType(){
        String etOutcomeDocumentTypeText = etOutcomeDocumentType.getText().toString();
        String initiativeText = getText(R.string.rb_initiative).toString();

        if(etOutcomeDocumentTypeText.equals(initiativeText)){
            etInputDocumentType.setText("");
            etInputDocumentNumber.setText("");
            etInputDocumentDate.setText("");
            etInputDocumentContent.setText("");

            etInputDocumentType.setFocusable(false);
            etInputDocumentNumber.setFocusable(false);
            etInputDocumentDate.setFocusable(false);
            etInputDocumentContent.setFocusable(false);
        }
        else {
            etInputDocumentType.setFocusableInTouchMode(true);
            etInputDocumentNumber.setFocusableInTouchMode(true);
            etInputDocumentDate.setFocusableInTouchMode(true);
            etInputDocumentContent.setFocusableInTouchMode(true);
        }
    }

    public void onClickSave (View view){
        String user = etUser.getText().toString();
        String inputDocumentType = etInputDocumentType.getText().toString();
        String inputDocumentNumber = etInputDocumentNumber.getText().toString();
        String inputDocumentDate = etInputDocumentDate.getText().toString();
        String inputDocumentContent = etInputDocumentContent.getText().toString();
        String outcomeDocumentType = etOutcomeDocumentType.getText().toString();
        String outcomeDocumentNumber = etOutcomeDocumentNumber.getText().toString();
        String outcomeDocumentDate = etOutcomeDocumentDate.getText().toString();
        String outComeDocumentContent = etOutcomeDocumentContent.getText().toString();
        String outcomeDocumentAddress = etOutcomeDocumentAddress.getText().toString();
        String outcomeDocumentStatus = etStatus.getText().toString();

        String id = taskId != null ? taskId : "";

        amendOrCreateViewModel.saveTask(this ,user, inputDocumentType, inputDocumentNumber, inputDocumentDate, inputDocumentContent, outcomeDocumentType, outcomeDocumentNumber, outcomeDocumentDate, outComeDocumentContent, outcomeDocumentAddress, outcomeDocumentStatus, id);
        onClickBackToMain(view);
    }

    public void onClickBackToMain(View view){
        Intent intent = new Intent(AmendOrCreateTaskActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickDelete(View view){
        amendOrCreateViewModel.deleteTask(this, taskId);
        onClickBackToMain(view);
    }
}
