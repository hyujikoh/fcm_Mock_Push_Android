package com.example.myapplication;

import com.google.firebase.messaging.FirebaseMessagingService;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        // SharedPreference 객체 생성
        SharedPreferences sharedPref = getSharedPreferences("fcm_test", Context.MODE_PRIVATE);

        // 값을 저장하기 위한 Editor 객체 생성
        SharedPreferences.Editor editor = sharedPref.edit();

        // 값을 저장
        editor.putString("fcm_token", token);

        // 변경사항 저장
        editor.apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String notificationTitle = remoteMessage.getNotification().getTitle();
            String notificationBody = remoteMessage.getNotification().getBody();

            Log.d("push","title : "+notificationTitle + " , body : " + notificationBody);
        }
    }

    private void displayNotificationToast(String title, String message) {
        final String toastMessage = title + ": " + message;

        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();

    }
}
