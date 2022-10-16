package com.example.data.storage.storages;

import androidx.annotation.NonNull;

import com.example.data.storage.interfaces.callbackInterfaces.GetTaskDataCallback;
import com.example.data.storage.interfaces.storageIntrfaces.TaskStorageInterface;
import com.example.data.storage.models.TaskStorageModel;
import com.example.data.storage.models.TaskStorageResponseModel;
import com.example.data.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TasksStorage implements TaskStorageInterface {

    /*
    *put task to database
    *all records duplicate, in one nest stores all tasks, in another nest stores each specific user's tasks
    * it made in order to get easier access to each user's tasks and to get all tasks
     */
    @Override
    public void saveTask(TaskStorageModel task) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        task.setUserID(UID);

        String taskId = task.getTaskID();

        if(taskId.equals("")) {
            String key = mDatabase.child(UID).push().getKey();

            task.setTaskID(key);

            assert key != null;
            mDatabase.child(UID).child(key).setValue(task);
            mDatabase.child(Constants.TASKS).child(key).setValue(task);
        }
        else {
            mDatabase.child(UID).child(taskId).setValue(task);
            mDatabase.child(Constants.TASKS).child(taskId).setValue(task);
        }
    }

    /*
    *prepare reference to user's nest
    *invoke internal private method getTasks to download a list of all available tasks by this reference
     */
    @Override
    public void getTasksByUser(GetTaskDataCallback callback) {
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(UID);
        getTasks(mDatabase, callback);
    }

    /*
     *prepare reference to all tasks nest
     *invoke internal private method getTasks to download a list of all available tasks by this reference
     */
    @Override
    public void getAllTasks(GetTaskDataCallback callback) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(Constants.TASKS);
        getTasks(mDatabase, callback);
    }

    /*
    *get single task by its id
     */
    @Override
    public void getTask(String taskId, GetTaskDataCallback callback) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.TASKS).child(taskId);

        mDatabase.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                TaskStorageModel taskStorageModel = task.getResult().getValue(TaskStorageModel.class);
                ArrayList<TaskStorageModel> taskList = new ArrayList<>();
                taskList.add(taskStorageModel);

                TaskStorageResponseModel response = new TaskStorageResponseModel(taskList);

                callback.onGetTaskResponse(response);
            }
            else {
                TaskStorageResponseModel response = new TaskStorageResponseModel(true);
                callback.onGetTaskResponse(response);
            }
        });
    }

    /*
    delete task by its id
     */
    @Override
    public void deleteTask(String taskId) {
        String UID = FirebaseAuth.getInstance().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(UID).child(taskId).removeValue();
        mDatabase.child(Constants.TASKS).child(taskId).removeValue();
    }

    /*
    *download a list of tasks by given reference
    * reference may be either user nest or all tasks nest
     */
    private void getTasks(DatabaseReference reference, GetTaskDataCallback callback){
        ArrayList<TaskStorageModel> tasks = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tasks.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    TaskStorageModel task = ds.getValue(TaskStorageModel.class);
                    tasks.add(task);
                }

                TaskStorageResponseModel response = new TaskStorageResponseModel(tasks);
                callback.onGetTaskResponse(response);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                TaskStorageResponseModel response = new TaskStorageResponseModel(true);
                callback.onGetTaskResponse(response);
            }
        };

        reference.addValueEventListener(valueEventListener);
    }

}
