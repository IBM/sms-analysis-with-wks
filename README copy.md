
# wks-nlu-sms-analysis
![Build Status](https://travis-ci.org/ragudiko/wks-nlu-sms-analysis.svg?branch=master)
![Bluemix Deployments](https://deployment-tracker.mybluemix.net/stats/2beb5ac0f2b130c628328825c48f65c5/badge.svg)
This is cognitive sms client which uses natural language understanding capability to analyze the sms and extracts entity data required.

Background: Current natural language processing techniques cannot extract/interpret the data as required by domain/industry specific. The data(entities) represent different meaning in different domain. Best answer to such problem is IBM Watson Knowledge Studio.
Consider an example where we need to extract entities present in commercial sms.
In such commercial sms usually interesting entities to be extracted are
1. what is the offer
2. who is providing(merchant)
3. offer name(if present in sms)
4. offer validity period

We can also additional info like merchant name, merchant location, merchant website, merchant phone number in case this info is available in sms.

The above requirement can be achieved by using our cognitive sms analysis.

## Maven
If Apache Maven is being used, the following dependency should be included:
```xml
  	<dependency>
  		<groupId>com.ibm.watson.developer_cloud</groupId>
  		<artifactId>java-sdk</artifactId>
  		<version>1.0</version>
  	</dependency> 	
```

## Process Flow

<img src="images/WKS-NLU-process.png" width="800" height="350" align="center">

## Technical Architecture

<img src="images/Technical Architecture - 2.png" width="800" height="350" align="center">


## Features
1. User can feed sms to NLU which has machine learning model deployed in it.
2. The NLU analyzes the sms and extracts the domain specific entities.
3. The extracted entities will provide info like what is the offer, who is providing offer and offer valid date etc.

## Create NLU service
<img src="images/nlu/Create nlu service-1.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-2.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-3.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-4.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-5.png" width="800" height="350" align="center">

## Create WKS model and deploy the model to nlu
1. Create wks service instance
<img src="images/wks/Dashboard.png" width="800" height="350" align="center">
<img src="images/wks/Creating knowledge studio service-1.png" width="800" height="350" align="center">
<img src="images/wks/Creating knowledge studio service-2.png" width="800" height="350" align="center">

2. Create workspace
<img src="images/wks/knowledge studio launch page.png" width="800" height="350" align="center">
<img src="images/wks/Workspace page.png" width="800" height="350" align="center">
<img src="images/wks/Create new workspace.png" width="800" height="350" align="center">
<img src="images/wks/Workspace creation done.png" width="800" height="350" align="center">

3. Create Type System (Step 3 is provided for reference, you can go directly to step 4)
<img src="images/wks/Type System-1.png" width="800" height="350" align="center">
<img src="images/wks/Type System-2- create type system entry.png" width="800" height="350" align="center">
<img src="images/wks/Type System-3- type system entry saved.png" width="800" height="350" align="center">

4. Import Type System
<img src="images/wks/Type System-4- upload type system entry.png" width="800" height="350" align="center">
<img src="images/wks/Type System-5- entries.png" width="800" height="350" align="center">
<img src="images/wks/Type System-6- create relation entry.png" width="800" height="350" align="center">
<img src="images/wks/Type System-7- relation entries.png" width="800" height="350" align="center">

5. Upload Documents (Step 5 is provided for reference, you can go directly to step 6)
<img src="images/wks/Documents-1-page.png" width="800" height="350" align="center">
<img src="images/wks/Documents-4-upload documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-3-upload documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-4-upload documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-5-upload documents.png" width="800" height="350" align="center">

6. Import Corpus Documents
<img src="images/wks/Documents-4-upload corpus documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-5-upload corpus documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-6-upload corpus documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-6-rename.png" width="800" height="350" align="center">
<img src="images/wks/Documents-7-rename.png" width="800" height="350" align="center">

7. Create Annotation Set
<img src="images/wks/Documents-9-create annotation set.png" width="800" height="350" align="center">
<img src="images/wks/Documents-10-create annotation set.png" width="800" height="350" align="center">

8. Create Task for Annotation
<img src="images/wks/Task-1-page.png" width="800" height="350" align="center">
<img src="images/wks/Task-2-create task.png" width="800" height="350" align="center">

### Select Annotation Set for this task
<img src="images/wks/Task-3-create task-select annotation set.png" width="800" height="350" align="center">
<img src="images/wks/Task-4-task created.png" width="800" height="350" align="center">
<img src="images/wks/Task-5-list of annotation set for this task.png" width="800" height="350" align="center">
<img src="images/wks/Task-6-list of documents within annotation set chosen.png" width="800" height="350" align="center">
<img src="images/wks/Task-7-start annotation-ground truth editor.png" width="800" height="350" align="center">

### Start Human Annotation clicking Annotate button
<img src="images/wks/Task-8-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-9-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-10-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-11-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-12-annotation-documents status.png" width="800" height="350" align="center">
<img src="images/wks/Task-13-annotation-documents status.png" width="800" height="350" align="center">

### Submit Annotation Set for which human annotation is complete
<img src="images/wks/Task-14-annotation-submit annotated documents.png" width="800" height="350" align="center">
<img src="images/wks/Task-15-annotation-documents completed status.png" width="800" height="350" align="center">
<img src="images/wks/Task-16-annotation-green-status completed.png" width="800" height="350" align="center">
<img src="images/wks/Task-17-annotation-annotation set -submitted status.png" width="800" height="350" align="center">
<img src="images/wks/Task-18-annotation-annotation set -accept.png" width="800" height="350" align="center">
<img src="images/wks/Task-19-annotation-annotation set -accept.png" width="800" height="350" align="center">
<img src="images/wks/Task-20-annotation-annotation set -accept-status completed.png" width="800" height="350" align="center">

### Create model, train and evaluate
<img src="images/wks/Model Training and Evaluation-1.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-2.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-3.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-4-training in progress.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-5-training and evaluation completed.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-6-logs.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-7-multiple training and evaluation completed.png" width="800" height="350" align="center">

## Deploy the Machine Learning model to NLU
<img src="images/wks/Model Deployment-1.png" width="800" height="350" align="center">
<img src="images/wks/Model Deployment-2.png" width="800" height="350" align="center">
<img src="images/wks/Model Deployment-3.png" width="800" height="350" align="center">
<img src="images/wks/Model Deployment-4.png" width="800" height="350" align="center">




Usage
You can run simple java client provided in this project to extract the entities from sms.

Alternatively you can use the curl commands.

NLU without wks model

curl -u "username":"password" "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=DUNKI%20DONUTS%20is%20now%20open%20at%20Girgaum%20Chowpatty.%20Walk-in%20and%20enjoy%20the%20Valentaine%20SPL%20offer%20on%20your%20favorite%20Donuts.%20Buy%203%20%26%20Get%203%20FREE.%20Valid%20till%2015%20Feb%202017.%20T%26C&features=entities"

Output:{ "language": "en", "entities": [ { "type": "Company", "text": "DUNKI DONUTS", "relevance": 0.976076, "count": 1 }, { "type": "GeographicFeature", "text": "Girgaum Chowpatty", "relevance": 0.65276, "count": 1 } ] }
The api is able to capture company(merchant) and location which are generic entities. It fails to extract the offer details as per our expectation.

NLU with WKS model

curl -u "username":"password" "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=DUNKI%20DONUTS%20is%20now%20open%20at%20Girgaum%20Chowpatty.%20Walk-in%20and%20enjoy%20the%20Valentaine%20SPL%20offer%20on%20your%20favorite%20Donuts.%20Buy%203%20%26%20Get%203%20FREE.%20Valid%20till%2015%20Feb%202017.%20T%26C&features=entities&entities.model=10:2845cc50-8099-4cc6-89a1-95593e460cf0"


Output:{ "language": "en", "entities": [ { "type": "Merchant", "text": "DUNKI DONUTS", "count": 1 }, { "type": "Location", "text": "Girgaum", "count": 1 }, { **"type": "Offer", "text": "Get 3 FREE", "count": 1 }, { "type": "Offer_Period", "text": "Valid till 15 Feb 2017", "count": 1 }, { "type": "Term_and_Conditions", "text": "T&C",** "count": 1 } ] }

### If we look the entities extracted in the form of json, we got the domain specific entities like offer, offer period, merchant. The model I used is trained and evaluated based on few sample sms. The sample sms are available under data folder.

Once you build wks model and NLU api service you can replace username/password highlighted with your NLU service credentials. Replace wks model id(entities.model) with your wks model id. The sms text has to be URL encoded as it is passed as url query string in Curl command.

## Run JUnits using maven command
Download Maven : https://maven.apache.org/download.cgi

Install Maven: https://maven.apache.org/install.html

Configure maven: Open .bash_profile if exists, else create new .bash_profile file. Make below entries into .bash_profile file.

-- -- entry starts

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home

export JAVA_HOME


M2_HOME=/usr/local/apache-maven/apache-maven-3.1.1

export M2_HOME

PATH=$PATH:$JAVA_HOME/bin:$M2_HOME/bin

export PATH

-- -- entry ends

Now from terminal run below command

mvn test

# Get your hands on with the NLU api and WKS tool
You can register at https://console.bluemix.net to access NLU.

You can try free version of WKS : https://www.ibm.com/in-en/marketplace/supervised-machine-learning

## Additional Resource
You can refer https://github.com/rameshpoomalai/ProcurementSystem for procurement use case where we have used WKS, Discovery and IBM Graph.

## Deploy the App

   [![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https://github.com/ragudiko/wks-nlu-sms-analysis)
