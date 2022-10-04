**Task Description:** 

This project is created for demo purpose.
1. Retrieve list of file objects in /mnt/share1/tes on node 1 and node 2 in parallel
2. Return Success if contents match ; error if there is a mismatch


-------------                 -------------
Node 1  (n1)                    Node 2 (n2)
------|------                  -------|------
      |                               |          
      ---------------|------------------
                     |       
               --------------
                   Node 3 (mgr)
               ---------------
**Bonus Points :**

Include validation for metadata of the file
Include dat integrity checks for file

**Deliverables:** 
code
Links to any non built in packages and the reason those packages were used
               

**Languages allowed to implement :** JAVA, C++, Python

**Language used:** JAVA

**Classes:**

There are two classes in this project
Runnable Task === > Which contains file reading methods
Runnable Example ====> Which contains the thread to invoke the reading of the files in parallel and comparing them

**Assumptions made :**

Here the nodes are considered as directory and assuming there are list of file objects under them

**Otherways to achieve this :**

Multi threading in Java can be achieved through many ways Runnable, Cyclic Barrier etc. 

**DataIntegrity check :**

Dataintegrity of the files are checked using some inbuilt classes like DigestUtils

**MetaDataValidation:** 

MetaData validation of the files are checked using some inbuilt classes like File and using its 
capabilities called attributes

**Other resources:**

1. The files are stored in folders called java-concurrency in the project path and files and files2 folder
2. All are text files that are stored.

**How to run the Project and test**

1. Launch the project in your IDE and right click on the Runnable Example class inside src folder and click on Run
2. Play around by input data in the files java-concurrency/files/test1.txt and java-concurrency/files/test2.txt to 
check the comparison






