package com.sg.alma50.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap

/**
 * A custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
 */
object Constants {


    const val IMAGE: String = "image"
    const val COMPLETE_PROFILE: String = "profileCompleted"
    const val MYSHOPPAL_PREFERENCES: String = "MyShopPalPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 3
    const val MALE: String = "Male"
    const val FEMALE: String = "Female"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val USER_PROFILE_IMAGE: String = "User_Profile_Image"

    //---------------------------------------------------------------
    const val SHAR_PREF = "char_pref"
    const val CURRENT_USER_EXIST = "current_user_exist"
    const val FIRESTORE_USER_ID="firststore_user_id"
    const val EXIST = "exist"
    const val NOT_EXIST = "not_exist"
    const val CONSTANT = "constant"
    const val NOT_CONSTANT = "notConstant"
    const val POST_PICTURE = "Posts Picture"
    const val POST_REF = "Posts"
    const val POST_ID = "post_id"
    const val POST_NUM = "post_num"
    const val POST_LINE_NUM = "post_lineNum"
    const val POST_IMAGE_URI = "post_image_uri"
    const val POST_TEXT = "post_text"
    const val POST_MARGIN = "post_margin"
    const val POST_BACKGROUND = "post_background"
    const val POST_TRANPARECY = "post_tranparecy"
    const val POST_TEXT_SIZE = "post_textSize"
    const val POST_PADDING = "post_padding"
    const val POST_TEXT_COLOR = "post_textColor"
    const val POST_FONT_FAMILY = "post_fontFamily"
    const val POST_RADIUS = "post_radius"
    const val POST_TIME_STAMP = "post_time_stamp"

    const val POST_REF_STAM = "Posts Stam"
    const val POST_IMAGE_STAM = "Posts Image Stam"

    const val SHARPREF_REF = "POST_PREF"


    const val POST_EXSTRA="post_exstra"
    const val USER_EXTRA="user_exstra_two"

//    const val USER_REF = "users"
    const val USER_REF = "New users"

    const val USERNAME = "userName"
    const val LASTNAME = "lastName"
    const val USER_EMAIL = "email"
    const val USER_GENDER = "gender"
    const val USER_MOTO = "moto"
    const val USER_IMAGE = "image"
    const val USER_TIME = "time"

    const val USER_ID = "userId"


    const val USER_PASSWORD = "user password"


    const val USER_BIO = "user bio"

    const val USER_EXSRTA = "user_exstra"
    const val USER_USERNAMEEXSRTA = "user_usernameexstra"
    const val USER_FULLNAME = "full name1"
    const val USER_USERNAME = "user name1"

    const val COMMENT_REF = "Comments"
    const val COMMENT_LIST = "Comment List"
    const val COMMENT_TEXT = "comment_text"
    const val COMMENT_USER_NAME = "comment_user_name"
    const val COMMENT_USER_ID = "comment_user_id"
    const val COMMENT_POST_ID = "comment_post_id"
    const val COMMENT_ID = "comment_id"
    const val COMMEND_TIME_STAMP = "comment_time_stamp"

    const val DIALOG_EXSTRA = "dealogexstra"





    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI  )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }


    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        /*
         * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
         *
         * getSingleton(): Get the singleton instance of MimeTypeMap.
         *
         * getExtensionFromMimeType: Return the registered extension for the given MIME type.
         *
         * contentResolver.getType: Return the MIME type of the given content URL.
         */
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}