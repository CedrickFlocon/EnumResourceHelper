EnumResource
=================

EnumResource is a tiny Android library use to bind Enum to Android resource (base on reflexion).

### Implemented Resource
	* String
	* Color
	* Drawable

Quick Setup
------------

In the repository you have a sample to help you.

**Gradle dependency:**

``` groovy
	compile 'com.github.CedrickFlocon:enumresource:0.2'
```

**Maven dependency:**
``` groovy
	<dependency>
		<groupId>com.github.CedrickFlocon</groupId>
		<artifactId>enumresource</artifactId>
		<version>0.2</version>
	</dependency>
```

**Resource File (string.xml):**

``` xml
	<resources>
		<string name="BURMA_STRING">Myanmar</string>
	</resources>
```


**Java Code:**
``` java
	public class MainActivity extends Activity {

		private enum Country{
			BURMA,
			ICELAND,
			TURKEY,
			AUSTRALIA
		}

		@Override
		public void onCreate() {
			super.onCreate();

			EnumResourceStringHelper<Country> countryEnumResourceHelper = new EnumResourceStringHelper<>(Country.class, EnumResourceHelper.ResourceType.STRING, this, null, "_STRING");

			String country = countryEnumResourceHelper.getResource(Country.BURMA);
			Toast toast = Toast.makeText(this, country, Toast.LENGTH_LONG); // Display "Myanmar"
			Country burma = countryEnumResourceHelper.getEnum(country); // burma == Country.BURMA
			...
		}
	}
```

**Proguard configuration:**
As this library use reflexion to bind enum to resource ou can't obfuscate the enum.
So add this to your proguard rules :
Public enum :
``` proguard
-keep class my.package.name.MyEnum { *; }
```

Inner enum :
``` proguard
-keep class my.package.name.MyClass$MyEnum { *; }
```


Contributing
------------
Feel free to improve the code and send me pull requests!


License
------------

    Copyright 2014 Cédrick Flocon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
