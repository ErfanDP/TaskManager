package com.example.erfan_delavari_hw13_maktab36.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private UUID mUUID;
    private List<Task> mTaskList;
    private String mUserName;
    private int mPassword;

    public User(String userName, int password,int numberOfTasks) {
        mUserName = userName;
        mPassword = password;
        mUUID = UUID.randomUUID();
        for (int i = 0; i < numberOfTasks; i++) {
            mTaskList.add(Task.randomTaskCreator(userName));
        }
    }

    public List<Task> getList() {
        return mTaskList;
    }


    public Task get(UUID uuid) {
        for (Task task : mTaskList) {
            if(task.getUUID().equals(uuid)){
                return task;
            }
        }
        return null;
    }

    public void setList(List<Task> list) {
        mTaskList = list;
    }

    public void delete(Task task) {
        for (int i = 0; i < mTaskList.size(); i++) {
            if (mTaskList.get(i).getUUID().equals(task.getUUID())) {
                mTaskList.remove(i);
                return;
            }
        }
    }

    public void update(Task task) {
        Task updateTask = get(task.getUUID());
        updateTask.setName(task.getName());
        updateTask.setTaskState(task.getTaskState());
    }

    public void insert(Task task) {
        mTaskList.add(task);
    }

    public void insertToList(List<Task> list) {
        mTaskList.addAll(list);}

    public List<Task> getTaskListByTaskState(TaskState taskState){
        List<Task> taskList = new ArrayList<>();
        for (Task task: mTaskList) {
            if(task.getTaskState() == taskState ){
                taskList.add(task);
            }
        }
        return taskList;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getPassword() {
        return mPassword;
    }

    public void setPassword(int password) {
        mPassword = password;
    }
}