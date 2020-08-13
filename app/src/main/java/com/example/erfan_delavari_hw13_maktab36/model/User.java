package com.example.erfan_delavari_hw13_maktab36.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private UUID mUUID;
    private List<Task> mTaskList = new ArrayList<>();
    private String mUserName;
    private String mPassword;
    private Date mRegisterDate;

    public User(String userName, String password,int numberOfTasks) {
        mUserName = userName;
        mPassword = password;
        mUUID = UUID.randomUUID();
        for (int i = 0; i < numberOfTasks; i++) {
            mTaskList.add(Task.randomTaskCreator(userName+"#"+(i+1)));
        }
        mRegisterDate = new Date();
    }

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public Date getRegisterDate() {
        return mRegisterDate;
    }

    public Task getTaskByUUID(UUID uuid) {
        for (Task task : mTaskList) {
            if(task.getUUID().equals(uuid)){
                return task;
            }
        }
        return null;
    }

    public void setTaskList(List<Task> list) {
        mTaskList = list;
    }

    public void deleteTask(Task task) {
        for (int i = 0; i < mTaskList.size(); i++) {
            if (mTaskList.get(i).getUUID().equals(task.getUUID())) {
                mTaskList.remove(i);
                return;
            }
        }
    }

    public void updateTask(Task task) {
        Task updateTask = getTaskByUUID(task.getUUID());
        if(updateTask == null){
            return;
        }
        updateTask.setName(task.getName());
        updateTask.setTaskState(task.getTaskState());
        updateTask.setDescription(task.getDescription());
        updateTask.setDate(task.getDate());
    }

    public void insertTask(Task task) {
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

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean loginCheck(String userName,String password){
        return this.mUserName.equals(userName) && this.mPassword.equals(password);
    }

    public void deleteAllTasks(){
        mTaskList = new ArrayList<>();
    }
}
