# FastContactsPicker
FastContactPicker is a library that fetches contacts from the device very fast. The library does all the fetching work in the background with the help of coroutines and also use Rxjava for the callback. This library is very fast, efficient and easy to use.
  

 [![API](https://img.shields.io/badge/API-15%2B-red.svg)](https://android-arsenal.com/api?level=15) [![Twitter URL](https://img.shields.io/twitter/url/https/twitter.com/fold_left.svg?style=social&label=Follow%20%40elite_novice)](https://twitter.com/elite_novice)
 <p align="center">
 
<b>Android library for Fetching Contacts from Device</b> 
</p>
 <p align="center">
 Built with ❤︎ by <a href="https://medium.com/@EliteNovice">Aryan Dhankar</a>.  
 </p>
 

## Including in your project 
 ### For Gradle
  
  Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.GitEliteNovice:FastContactsPicker:v1.0'
	}

### For Maven
           <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.GitEliteNovice</groupId>
	    <artifactId>FastContactsPicker</artifactId>
	    <version>v1.0</version>
	</dependency>


## Usage

         FastContactPicker.getInstance(this).fetchContacts().subscribeWith(object :Observer<Any>{
             override fun onComplete() {

             }

             override fun onSubscribe(d: Disposable) {

             }

             override fun onNext(t: Any) {

           
                 var arrayList=t as ArrayList<ContactInfo>
           //list of contacts
             }

             override fun onError(e: Throwable) {
                    // error
                 Toast.makeText(this@MainActivity,"error"+e.localizedMessage,Toast.LENGTH_LONG).show()
             }

         })


*Note:-
If you want to say thanks or want to share how much you like my efforts, then you can share it with me with [![Say Thanks!](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg)](https://saythanks.io/to/GitEliteNovice) 
