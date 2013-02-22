mower
=====
Hi,

To build and launch unit test : 

* mvn clean install

To stand alone :

* mvn clean install && java -jar target\original-mower-1.0.0-SHADED.jar -f !!absolute file path ( xml or txt )!!

Available test file :

	* XML :
	
		src\test\resources\mower_case_1.xml ( first mower  ) 
		src\test\resources\mower_case_2.xml ( second mower )
		src\test\resources\mower.xml        ( both mower   )
		src\test\resources\mower_case_3.xml ( extrem mower )

	* TXT :

		src\test\resources\mower_case_1.txt ( first mower  ) 
		src\test\resources\mower_case_2.txt ( second mower )
	
Hope you'll enjoy !! 
