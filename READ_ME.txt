RSS Parser Engine(Rsspe)
========================

Author : Salih kallai
Version : 1.1(beta)
Last modified : 11 Jun 16,11:46 PM
Licence : Free for personal and commercial use, No credits required
			You can modify and redistribute my work, But selling is not permitted


=================================
INSTALLATION
=================================
1. Copy the 'Rsspe' folder into your src directory

2. Copy the 'rsspe_description.xml' file under layout folder to your projects /layouts folder

3. We uses the following 3rd party libraries, so add dependencies properly

	compile 'com.mcxiaoke.volley:library:1.0.19'
    compile 'com.einmalfel:earl:1.0.0'
    compile 'com.fabiendevos:nanotasks:1.1.0'
	
4. Add the following line in AndroidManifest.xml under Application tag

	<activity android:name=".Rsspe.Rsspe_description"></activity>
	
Done!

===================================

BASIC USAGE

======================================

	//LinearLayout for displaying the results,Make sure the id of the layout should be 'parent'
	LinearLayout parent = (LinearLayout)findViewById(R.id.parent);
	
	Rsspe rsspe = new Rsspe();
    rsspe.ex(parent, this, "url", true, true, false);
	
	/*parameters description
	1 & 2 default ,nothing to change
	3. Url of the link which contains the RSS Feed, Data type-String
	4. Fetch_description = TRUE/FALSE, Data type-Boolean
	5. Fetch_date = TRUE/FALSE, Data type-Boolean
	6. Fetch_Image_Link = TRUE/FALSE, Data type = Boolean (NOTE: Currently this parameter is not functional,We will working on it)
	*/
	
	
=====================================
ADVANCED USAGE
=====================================

1. setDbg(String);

	This method changes the background color of the description page(optional)
	NOTE: Use proper hexadecimal color formal
	
	Sample code:
	
		rsspe.setDbg("#D3B6B6");
		
=====================================

2. setMaxItems(int);

	This method defines the max no.of items to be fetched,If you skip this all data will be loaded by default
	
	Sample code:
	
		rsspe.setMaxItems(5);
		
====================================


Change log
==========
Version 1.1(beta)
===========
	1.'setDbg()' method added 
	2. 'setMaxItems' method added
	
	
	
===================================
E-mail:mymailhomes@gmail.com	
Issues forum will be created later
Thanks!	
	
	