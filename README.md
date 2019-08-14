# FastContactsPicker
  A kotlin library taht loads all contacts from your device very Fast
  

## How to Add Dependency  
  For Gradle
  
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

For Maven
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


## How to Fetch Contacts

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
