**Task Description:** 

This project is created for demo purpose.
1. Retrieve list of file objects in /mnt/share1/tes on node 1 and node 2 in parallel
2. Return Success if contents match ; error if there is a mismatch

**Bonus Points :**

1. Include validation for metadata of the file
2. Include dat integrity checks for file

**Deliverables:** 
1. code
2. Links to any non built in packages and the reason those packages were used
               

**Languages allowed to implement :** 

JAVA, C++, Python

**Language used:** 
JAVA

**Classes:**

There are two classes in this project
1. Runnable Task which contains file reading methods
2. Runnable Example which contains the thread to invoke the reading of the files in parallel and comparing them

**Assumptions made :**

Here the nodes are considered as directory and assuming there are list of file objects under them

**Otherways to achieve this :**

Multi threading in Java can be achieved through many ways Runnable, Cyclic Barrier, Callable + Future etc. 

**DataIntegrity check :**

Dataintegrity of the files are checked using some inbuilt classes like DigestUtils

**MetaDataValidation:** 

MetaData validation of the files are checked using some inbuilt classes like File and using its 
capabilities called attributes

**Other resources:**

1. The files are stored in folders called java-concurrency in the project path and files and files2 folder
2. All are text files that are stored.

**Exceptions**

1. File not found Exception is handled
2. IO exception is handled

**How to run the Project and test**

1. Launch the project in your IDE and right click on the Runnable Example class inside src folder and click on Run
2. Play around by input data in the files java-concurrency/files/test1.txt and java-concurrency/files/test2.txt to 
check the comparison


**Another Try**

1. Tried in python initially but since it is not my comfortable language to start with it was bit of a struggle. 
2. It is not complete but I manage to read two files sequentially.
3. Some comments I have left for which I tried for multi processing although it is not a complete one.





