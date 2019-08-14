package com.aryan.fastcontacts_picker

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

class FastContactPicker {
  private var context:Context;
private val contactList = ArrayList<ContactInfo>()
    private  var publishSubject :PublishSubject<ArrayList<ContactInfo>>
    constructor(context :Context){
        publishSubject= PublishSubject.create()
    this.context=context
    }


    companion object{
        private var fastContactPicker: FastContactPicker?=null
        fun  getInstance(context :Context): FastContactPicker {

              if(fastContactPicker ==null){
                  fastContactPicker =
                      FastContactPicker(context)
              }
            return fastContactPicker as FastContactPicker
        }
    }

 fun fetchContacts() : PublishSubject<ArrayList<ContactInfo>> {

    GlobalScope.launch (Dispatchers.Main){
   try {
       contacts()

       publishSubject.onNext(contactList)

   }catch (e:Exception){
       publishSubject.onError(e)

   }
    }
return publishSubject

}

   private suspend fun contacts()  = withContext(Dispatchers.Default){


       val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
       val selection = ContactsContract.Contacts.HAS_PHONE_NUMBER
       var cursor = context.getContentResolver().query(
           uri,
           arrayOf(
               ContactsContract.CommonDataKinds.Phone.NUMBER,
               ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
               ContactsContract.CommonDataKinds.Phone._ID,
               ContactsContract.Contacts._ID
           ),
           selection,
           null,
           ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
       )

       cursor!!.moveToFirst()
       while (cursor!!.isAfterLast() == false) {

           val contactNumber =
               cursor!!.getString(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
           val contactName =
               cursor!!.getString(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
           val phoneContactID = cursor!!.getInt(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
           val contactID = cursor!!.getInt(cursor!!.getColumnIndex(ContactsContract.Contacts._ID))
           //  Log.d("con ", "name " + contactName + " " + " PhoeContactID " + phoneContactID + "  ContactID " + contactID);
           contactList.add(
               ContactInfo(
                   contactName,
                   (contactNumber.replace("\\s+".toRegex(), "").replace(("[-" + "^]*").toRegex(), ""))
               )
           )
           cursor!!.moveToNext()
       }
       cursor!!.close()

         Log.d("value::","=-"+contactList.size+"----");


    }

}