+peerialism-task
+===============
+
+This repo includes two directories:
+    The first one is called "Java Job Consumer & TextRipper" which has the Netbeans project for the JobConsumer and TextRipper. You should open it as a project in Netbeans or you can just use the classes with any otehr IDE. The project must be configured to work with both RabbitMQ and Selenium libraries (already configured in the NetBeans Project folder). Once you start the project, you can run it and the JobConsumer will be waiting for new Jobs to consume. You will be able to check that in the output of the application itself.
+    
+    The second folder is calle "PHP Web interface & Job Producer" which has the PHP files for the Web Interface, JobProducer, and also the database being used. To be able to run it, you have to have an Apache web server installed. Usually, I use Wamp Webserver. You will copy the files to the www directory of your web server and then you will need to configure the database connection in the file named "db.php" according to your own MySQL Server username and password
+            
+            $con = mysql_connect("localhost", "username", "password");
+            mysql_select_db("database name", $con);
+    You will need to create a new database called "peerialism" in your phpmyadmin and then import the peerialism.sql file that is included in the folder.
+    
+After that, you will just to to "http://127.0.0.1/index.php" and you will find the web interface. Once the page is loaded, you will be able to see two links and a sentence. the first link to to add a job to the queue, the second one is to check the current queue (finished jobs and their results & pending jobs). As for the sentence, I created it as a test case for the application. It is inside a DIV that has the id "testid". So if you want to test the application, you can just type the url as "http://127.0.0.1/index.php" and the id as "testid" and submit the job.
0 notes on commit deb12ad Show line notes below

    Write
    Preview

Comments are parsed with GitHub Flavored Markdown

Attach images by dragging & dropping or selecting them.
Commit_comment_tip

Tip: You can also add notes to lines in a file. Hover to the left of a line to make a note

You are receiving notifications because you are watching this repository.