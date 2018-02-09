# Analyzing SMS messages with Watson Knowledge Studio

![Build Status](https://travis-ci.org/IBM/wks-nlu-SMS-analysis.svg?branch=master)
<!--
![Bluemix Deployments](https://deployment-tracker.mybluemix.net/stats/2beb5ac0f2b130c628328825c48f65c5/badge.svg)
-->

This is cognitive SMS client which uses natural language understanding capability to analyze the SMS and extracts entity data required.

Background: Current natural language processing techniques cannot extract/interpret the data as required by domain/industry specific. The data(entities) represent different meaning in different domain. Best answer to such problem is IBM Watson Knowledge Studio. Consider an example where we need to extract entities present in commercial SMS.

In such commercial SMS usually interesting entities to be extracted are:
1. what is the offer
2. who is providing(merchant)
3. offer name(if present in SMS)
4. offer validity period

We can also additional info like merchant name, merchant location, merchant website, merchant phone number in case this info is available in SMS.

The above requirement can be achieved by using our cognitive SMS analysis.

## Maven

If Apache Maven is being used, the following dependency should be included:

```xml
<dependency>
    <groupId>com.ibm.watson.developer_cloud</groupId>
    <artifactId>java-sdk</artifactId>
  	<version>1.0</version>
</dependency> 	
<dependency>
    <groupId>com.ibm.watson.developer_cloud</groupId>
    <artifactId>java-sdk</artifactId>
    <version>1.0</version>
</dependency>
```

## Process Flow

![](doc/source/images/wks-nlu-process.png)

## Technical Architecture

![](doc/source/images/technical_architecture_2.png)


## Features
1. User can feed sms to NLU which has machine learning model deployed in it.
2. The NLU analyzes the sms and extracts the domain specific entities.
3. The extracted entities will provide info like what is the offer, who is providing offer and offer valid date etc.

# Steps

## 1. Create a NLU service
![](doc/source/images/nlu/create_nlu_service-1.png)
![](doc/source/images/nlu/create_nlu_service-2.png)
![](doc/source/images/nlu/create_nlu_service-3.png)
![](doc/source/images/nlu/create_nlu_service-4.png)
![](doc/source/images/nlu/create_nlu_service-5.png)

## 2. Create a WKS service

![](doc/source/images/wks/dashboard.png)
![](doc/source/images/wks/creating_knowledge_studio_service-1.png)
![](doc/source/images/wks/creating_knowledge_studio_service-2.png)

## 3. Create a WKS workspace

![](doc/source/images/wks/knowledge_studio_launch_page.png)
![](doc/source/images/wks/workspace_page.png)
![](doc/source/images/wks/create_new_workspace.png)
![](doc/source/images/wks/workspace_creation_done.png)

## 4. Upload Type System

Use the file located at: [wks-resources/types-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.json](wks-resources/types-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.json)

![](doc/source/images/wks/type_system-4_upload_type_system_entry.png)
![](doc/source/images/wks/type_system-5_entries.png)
![](doc/source/images/wks/type_system-6_create_relation_entry.png)
![](doc/source/images/wks/type_system-7_relation_entries.png)

## 5. Import Corpus Documents

Use the file located at[wks-resources/corpus-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.zip](wks-resources/corpus-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.zip)

> It is not necessary to use the corpus documents, however for beginners we recommend to use the corpus documents provided.

![](doc/source/images/wks/documents-2-upload_corpus_documents.png)
![](doc/source/images/wks/documents-3-upload_corpus_documents.png)
![](doc/source/images/wks/documents-4-upload_corpus_documents.png)

## 6. Create an Annotation Set

![](doc/source/images/wks/documents-9-create_annotation_set.png)
![](doc/source/images/wks/documents-10-create_annotation_set.png)

## 7. Create a Task for Human Annotation

![](doc/source/images/wks/task-1-page.png)
![](doc/source/images/wks/task-2-create_task.png)

### 7.1 Select Annotation Set for this task

![](doc/source/images/wks/task-3-create_task-select_annotation_set.png)
![](doc/source/images/wks/task-4-task_created.png)
![](doc/source/images/wks/task-5-list_of_annotation_set_for_this_task.png)
![](doc/source/images/wks/task-6-list_of_documents_within_annotation_set_chosen.png)
![](doc/source/images/wks/task-7-start_annotation-ground_truth_editor.png)

Start the Human Annotation by clicking the `Annotate` button.  Since we previously imported the corpus documents already have the annotations completed. You can look at entities and relationships already annotated. You can annotate mentions (occurrences of words/phrases which can be annotated as entity) to play around or you can modify by annotating mention with different entity.

![](doc/source/images/wks/task-8-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-9-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-10-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-11-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-12-annotation-documents_status.png)
![](doc/source/images/wks/task-13-annotation-documents_status.png)

## 8. Submit Annotation Set

![](doc/source/images/wks/task-14-annotation-submit_annotated_documents.png)
![](doc/source/images/wks/task-15-annotation-documents_completed_status.png)
![](doc/source/images/wks/task-16-annotation-green-status_completed.png)
![](doc/source/images/wks/task-17-annotation-annotation_set_submitted_status.png)
![](doc/source/images/wks/task-18-annotation-annotation_set_accept.png)
![](doc/source/images/wks/task-19-annotation-annotation_set_accept.png)
![](doc/source/images/wks/task-20-annotation-annotation_set_accept-status_completed.png)

## 9. Create model, train and evaluate

![](doc/source/images/wks/model_training_and_evaluation-1.png)
![](doc/source/images/wks/model_training_and_evaluation-2.png)
![](doc/source/images/wks/model_training_and_evaluation-3.png)
![](doc/source/images/wks/model_training_and_evaluation-4-training_in_progress.png)
![](doc/source/images/wks/model_training_and_evaluation-5-training_and_evaluation_completed.png)
![](doc/source/images/wks/model_training_and_evaluation-6-logs.png)
![](doc/source/images/wks/model_training_and_evaluation-7-multiple_training_and_evaluation_completed.png)

## 10. Deploy the machine learning model to NLU

![](doc/source/images/wks/model_deployment-1.png)
![](doc/source/images/wks/model_deployment-2.png)
![](doc/source/images/wks/model_deployment-3.png)
![](doc/source/images/wks/model_deployment-4.png)

# Usage

## Java Client
You can run the simple java client provided in this project to extract the entities from SMS messages.

## cURL
Alternatively you can use the curl commands.

### NLU without WKS model

```
curl -u "username":"password" "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=DUNKI%20DONUTS%20is%20now%20open%20at%20Girgaum%20Chowpatty.%20Walk-in%20and%20enjoy%20the%20Valentaine%20SPL%20offer%20on%20your%20favorite%20Donuts.%20Buy%203%20%26%20Get%203%20FREE.%20Valid%20till%2015%20Feb%202017.%20T%26C&features=entities"

Output:{ "language": "en", "entities": [ { "type": "Company", "text": "DUNKI DONUTS", "relevance": 0.976076, "count": 1 }, { "type": "GeographicFeature", "text": "Girgaum Chowpatty", "relevance": 0.65276, "count": 1 } ] }
The api is able to capture company(merchant) and location which are generic entities. It fails to extract the offer details as per our expectation.
```

### NLU with WKS model

```
curl -u "username":"password" "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=DUNKI%20DONUTS%20is%20now%20open%20at%20Girgaum%20Chowpatty.%20Walk-in%20and%20enjoy%20the%20Valentaine%20SPL%20offer%20on%20your%20favorite%20Donuts.%20Buy%203%20%26%20Get%203%20FREE.%20Valid%20till%2015%20Feb%202017.%20T%26C&features=entities&entities.model=10:a5172791-b31b-4b0d-b546-3610ec652ca4"


Output:{ "language": "en", "entities": [ { "type": "Merchant", "text": "DUNKI DONUTS", "count": 1 }, { "type": "Location", "text": "Girgaum", "count": 1 }, { **"type": "Offer", "text": "Get 3 FREE", "count": 1 }, { "type": "Offer_Period", "text": "Valid till 15 Feb 2017", "count": 1 }, { "type": "Term_and_Conditions", "text": "T&C",** "count": 1 } ] }
```

### Differences

If we look the entities extracted in the form of JSON, we get domain specific entities like `offer`, `offer period`, `merchant`. The model I used is trained and evaluated based on few sample sms. The sample sms are available under data folder.

Once the WKS model is built and the NLU service you can replace username/password highlighted with your NLU service credentials. Replace WKS model id (entities.model) with your WKS model id. The SMS text has to be URL encoded as it is passed as URL query string in curl command.

## Run JUnits using maven command

* Download Maven : https://maven.apache.org/download.cgi

* Install Maven: https://maven.apache.org/install.html

Configure maven: Open .bash_profile if exists, else create new .bash_profile file. Make below entries into .bash_profile file.

```
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home

export JAVA_HOME

M2_HOME=/usr/local/apache-maven/apache-maven-3.1.1

export M2_HOME

PATH=$PATH:$JAVA_HOME/bin:$M2_HOME/bin

export PATH
```

Now from terminal run below command

```
mvn test
```

# Additional Resources

* You can register at https://console.bluemix.net to access NLU.

* You can try free version of WKS by logging into https://console.bluemix.net and select knowledge studio service under Watson

* You can refer https://github.com/IBM/procurement-analysis-with-wks for procurement use case where we have used WKS, Discovery and IBM Graph.

# Deploy the App

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https://github.com/IBM/sms-analysis-with-wks)