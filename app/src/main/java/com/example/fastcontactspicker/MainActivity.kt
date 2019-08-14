package com.example.fastcontactspicker

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aryan.fastcontacts_picker.ContactInfo
import com.aryan.fastcontacts_picker.FastContactPicker
import com.example.fastcontactspicker.adapters.Adapter
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber

class MainActivity : AppCompatActivity() {
    lateinit var pb_bar:ProgressBar
    lateinit var recyler_view:RecyclerView
    lateinit var linearLayoutManager:LinearLayoutManager
    lateinit var adapter: Adapter
    //lateinit var arrayList:ArrayList<String>
    lateinit var fetch_contacts:Button
    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
init()
    }

    private fun init() {

        recyler_view=findViewById(R.id.recyler_view)
        linearLayoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recyler_view.layoutManager=linearLayoutManager
        pb_bar=findViewById(R.id.pb_bar)
        fetch_contacts=findViewById(R.id.fetch_contacts)
        fetch_contacts.setOnClickListener {
             if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                 != PackageManager.PERMISSION_GRANTED) {
                 // Permission is not granted
                     // No explanation needed, we can request the permission.

                     ActivityCompat.requestPermissions(this,
                         arrayOf(Manifest.permission.READ_CONTACTS),
                         MY_PERMISSIONS_REQUEST_READ_CONTACTS)

                     // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                     // app-defined int constant. The callback method gets the
                     // result of the request.



             }else{
            displaycontacts()


             }


        }


    }
     fun displaycontacts(){

         pb_bar.visibility=View.VISIBLE
         FastContactPicker.getInstance(this).fetchContacts().subscribeWith(object :Observer<Any>{
             override fun onComplete() {

             }

             override fun onSubscribe(d: Disposable) {

             }

             override fun onNext(t: Any) {

                 pb_bar.visibility=View.GONE
                 var arrayList=t as ArrayList<ContactInfo>
                 adapter= Adapter(this@MainActivity,arrayList)
                 recyler_view.adapter=adapter

             }

             override fun onError(e: Throwable) {

                 pb_bar.visibility=View.GONE
                 Toast.makeText(this@MainActivity,"error"+e.localizedMessage,Toast.LENGTH_LONG).show()
             }

         })
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the

                        displaycontacts()

                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

}
