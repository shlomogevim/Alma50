package com.sg.alma50.utilities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sg.alma50.activities.*
import com.sg.alma50.modeles.User
import com.sg.alma50.utilities.Constants.LOGGED_IN_USERNAME
import com.sg.alma50.utilities.Constants.MYSHOPPAL_PREFERENCES
import com.sg.alma50.utilities.Constants.USER_PROFILE_IMAGE
import com.sg.alma50.utilities.Constants.USER_REF
import com.sg.alma50.utilities.Constants.getFileExtension

import java.util.HashMap

class FirestoreClass{
    private val mFirestore=FirebaseFirestore.getInstance()
   // val util=Utility()


    fun registerUser(activity: RegisterActivity, userInfo: User) {
        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFirestore.collection(USER_REF)
            // Document ID for users fields. Here the document it is the User ID.
            .document(userInfo.uid)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // Here call a function of base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    fun getCurrentUserID(): String {
         val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun getUserDetails(activity: Activity) {
        val currentId=getCurrentUserID()
        if (currentId.isNotEmpty()) {
            mFirestore.collection(USER_REF).document(getCurrentUserID())
                .get()
                .addOnSuccessListener { document ->
                    //Log.i(activity.javaClass.simpleName, document.toString())
                    // Here we have received the document snapshot which is converted into the User Data model object.
                    val user = document.toObject(User::class.java)!!

                    insertToSharedPref(activity, user)             // for what ??

                    when (activity) {
                        is LoginActivity -> {
                            activity.userLoggedInSuccess(user)
                        }

                        is PostDetailesActivity -> {
                            activity.getUserNameSetting(user)
                        }
                        is SettingActivity -> {
                            activity.getUserNameSetting(user)
                        }
                    }
                }

                .addOnFailureListener { e ->
                    when (activity) {
                        is LoginActivity -> {
                            activity.hideProgressDialog()
                        }
                        is SettingActivity -> {
                            activity.hideProgressDialog()
                        }
                    }

                    Log.e(
                        activity.javaClass.simpleName,
                        "Error while getting user details.",
                        e
                    )
                }
        }
    }

    private fun insertToSharedPref(activity: Activity, user: User) {
        val sharedPreferences = activity.getSharedPreferences(
            MYSHOPPAL_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(
            LOGGED_IN_USERNAME,
            "${user.userName} ${user.lastName}"
        )
        editor.apply()
    }

  fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
      // Collection Name
      mFirestore.collection(USER_REF).document(getCurrentUserID()).update(userHashMap)
          .addOnSuccessListener {
              when (activity) {
                  is UserProfileActivity -> {
                      // Call a function of base activity for transferring the result to it.
                      activity.userProfileUpdateSuccess()
                  }
              }
          }
          .addOnFailureListener { e ->

              when (activity) {
                  is UserProfileActivity -> {
                      // Hide the progress dialog if there is any error. And print the error in log.
                      activity.hideProgressDialog()
                  }
              }

              Log.e(
                  activity.javaClass.simpleName,
                  "Error while updating the user details.",
                  e
              )
          }
  }

    fun uploadImageToCloudStorage(activity: Activity, imageFileURI: Uri?) {
        //getting the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            USER_PROFILE_IMAGE + System.currentTimeMillis() + "."
                    + getFileExtension( activity, imageFileURI )
        )
        //adding the file to reference
        sRef.putFile(imageFileURI!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e("gg", taskSnapshot.metadata!!.reference!!.downloadUrl.toString() )
                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())
                        // Here call a function of base activity for transferring the result to it.
                        when (activity) {
                            is UserProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                        }
                    }
            }
            .addOnFailureListener { exception ->

                // Hide the progress dialog if there is any error. And print the error in log.
                when (activity) {
                    is UserProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }



}