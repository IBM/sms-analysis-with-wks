# Analyzing SMS messages with Watson Knowledge Studio

![Build Status](https://api.travis-ci.org/IBM/sms-analysis-with-wks.svg?branch=master)

This code pattern describes how to analyze SMS messages with Watson Knowledge Studio (WKS) and Watson's Natural Language Understanding (NLU) capability to extract entities in the data. Current natural language processing techniques cannot extract or interpret data that is domain or industry specific because entities have different meanings in different domains. The best answer to such a problem is IBM's Watson Knowledge Studio. Consider a case where we need to extract entities present in a commercial SMS. For example:

```
PIZZA! Don't Cook Wednesdays are here! Get 50% off a Medium Pizza.
Offer available for single Pizza in-store and two for Home Delivery.
Walk-In/Call @ 555-555-5555
```

The example above has a few interesting entities which could not be extracted with conventional NLP techniques, but by using Watson services we can find out the following:

1. What is the offer?
2. Who is the merchant?
3. What is the offer name?
4. What is the offer's validity period?
5. What is the merchant's phone number?
6. What is the merchant's website?

After completing this code pattern, the user will learn how to:

* Upload a corpus with WKS
* Import types to WKS
* Use WKS to create a model
* Deploy a WKS model to NLU
* Call NLU APIs with a WKS model specified

## Flow

![](doc/source/images/architecture.png)

1. Load type system and corpus files into Watson Knowledge Studio.
1. A user generates a model by training and evaluating data.
1. The WKS model is deployed to Watson NLU.
1. A user provides an SMS message to the app for analysis.
1. The SMS message is analyzed by Watson NLS for processing and returns extracted domain specific entities based on the WKS model are returned.

### How does Watson Knowledge Studio work?

The image below explains the process of how Watson Knowledge Studio works in light detail. For greater detail see Steps [4. Upload Type System](#4-upload-type-system) through [9. Deploy the machine learning model to NLU](#9-deploy-the-machine-learning-model-to-nlu).

![](doc/source/images/wks-nlu-process.png)

In short, a type system is built and supporting documents are uploaded that have domain specific wording. From here a model must be built to properly understand the documents, this is where the annotations come in. Once the corpus and annotations are set you are free to create a model and deploy it to a Watson Natural Language Understanding instance.

## Included components

* [Watson Natural Language Understanding](https://www.ibm.com/watson/developercloud/natural-language-understanding.html): An IBM Cloud service that can analyze text to extract meta-data from content such as concepts, entities, keywords, categories, sentiment, emotion, relations, semantic roles, using natural language understanding.
* [Watson Knowledge Studio](https://www.ibm.com/watson/services/knowledge-studio/): Teach Watson the language of your domain with custom models that identify entities and relationships unique to your industry, in unstructured text. Use the models in Watson Discovery, Watson Natural Language Understanding, and Watson Explorer.

## Featured Technologies

* [Artificial Intelligence](https://medium.com/ibm-data-science-experience): Artificial intelligence can be applied to disparate solution spaces to deliver disruptive technologies.
* [Java](https://java.com): A secure, object-oriented programming language for creating applications.

# Watch the Video

[![](https://img.youtube.com/vi/lwW97UQj0RM/0.jpg)](https://youtu.be/lwW97UQj0RM)

# Steps

1. [Clone the repo](#1-clone-the-repo)
2. [Create IBM Cloud services](#2-create-ibm-cloud-services)
3. [Create a Watson Knowledge Studio workspace](#3-create-a-watson-knowledge-studio-workspace)
4. [Upload Type System](#4-upload-type-system)
5. [Import Corpus Documents](#5-import-corpus-documents)
6. [Create an Annotation Set](#6-create-an-annotation-set)
7. [Create a Task for Human Annotation](#7-create-a-task-for-human-annotation)
8. [Create the model](#8-create-the-model)
9. [Deploy the machine learning model to NLU](#9-deploy-the-machine-learning-model-to-nlu)
10. [Test the model with cURL](#10-test-the-model-with-curl)
11. [Run the application](#11-run-the-application)

## 1. Clone the repo

```
$ git clone https://github.com/IBM/sms-analysis-with-wks
```

## 2. Create IBM Cloud services

Create the following services:

* [**Watson Natural Language Understanding**](https://cloud.ibm.com/catalog/services/natural-language-understanding)
* [**Watson Knowledge Studio**](https://cloud.ibm.com/catalog/services/knowledge-studio)

> NOTE: Keep the Natural Language Understanding's API Key and URL handy, you'll need it later. For example if you create your service in the Dallas region then the URL will be: https://api.us-south.natural-language-understanding.watson.cloud.ibm.com/instances/{instance-id}

> NOTE: It is recommended that you name your NLU service `sms-nlu-service`. This will eliminate some steps if you eventually decide to deploy your app to the IBM Cloud.

## 3. Create a Watson Knowledge Studio workspace

Launch the **WKS** tool and create a new **workspace**.

![](doc/source/images/wks/create_new_workspace.png)

## 4. Upload Type System

A type system allows us to define things that are specific to our SMS messages. The type system controls how content can be annotated by defining the types of entities that can be labeled and how relationships among different entities can be labeled.

To upload our pre-defined type system, from the **Access & Tools -> Entity Types** panel, press the **Upload** button to import the **Type System** file  [data/types-8f342360-1c8f-11e8-9ded-ddbbc0ccb99a.json](data/types-8f342360-1c8f-11e8-9ded-ddbbc0ccb99a.json) found in the local repository.

![](doc/source/images/wks/type_system-4_upload_type_system_entry.png)

This will upload a set of **Entity Types** and **Relation Types**.

![](doc/source/images/wks/type_system-5_entries.png)

![](doc/source/images/wks/type_system-6_create_relation_entry.png)

## 5. Import Corpus Documents

Corpus documents are required to train our machine-learning annotator component. For this Code Pattern, the corpus documents will contain example SMS messages.

> NOTE: Individual SMS sample text messages are located in the [data](data) directory of the local respoitory.

From the **Access & Tools -> Documents** panel, press the **Upload Document Sets** button to import a **Document Set** file. Use the corpus documents file [data/corpus-8f342360-1c8f-11e8-9ded-ddbbc0ccb99a.zip](data/corpus-8f342360-1c8f-11e8-9ded-ddbbc0ccb99a.zip) found in the local repository.

> NOTE: Uploading the corpus documents provided in this Code Pattern is not required, but recommended to simplify the annotation process (all provided documents will come pre-annotated). An alternative approach would be to is to upload standard text files and perform the annotations manually.

> NOTE: Select the option to "upload the original workspace's type system first".

![](doc/source/images/wks/documents-3-upload_corpus_documents.png)

![](doc/source/images/wks/documents-4-upload_corpus_documents.png)

## 6. Create an Annotation Set

Once the corpus documents are loaded, we can start the human annotation process. This begins by dividing the corpus into multiple document sets and assigning the document sets to human annotators (for this Code Pattern, we will just be using using one document set and one annotator).

From the **Access & Tools -> Documents** panel, press the **Create Annotation Sets** button. Select a valid **Annotator** user, and provide a unique name for **Set name**.

![](doc/source/images/wks/documents-9-create_annotation_set.png)

![](doc/source/images/wks/documents-10-create_annotation_set.png)

## 7. Create a Task for Human Annotation

> Note: With the latest release of Watson Knowledge Studio, most of the remainig steps below reference menu actions that have been moved. There is a new **Machine Learning Model** menu item that has been added to the left side of the UI panel. It contains actions associated with **Tasks** (now referred to as **Annotation Tasks**), **Performance**, and **Versions**. Please keep this in mind as you navigate the following steps.

Add a task for human annotation by creating a task and assigning it annotation sets.

From the **Access & Tools -> Documents** panel, select the **Task** tab and press the **Add Task** button.


![](doc/source/images/wks/task-2-create_task.png)

Enter a unique **Task name** and press the **Create** button.

A panel will then be displayed of the available annotation sets that can be assigned to this task. Select the **Annotation Set** you created in the previous step, and press the **Create Task** button.

![](doc/source/images/wks/task-3-create_task-select_annotation_set.png)

![](doc/source/images/wks/task-4-task_created.png)

### 7.1 Start the Human Annotation task

Click on the task card to view the task details panel.

![](doc/source/images/wks/task-5-list_of_annotation_set_for_this_task.png)

Click the **Annotate** button to start the **Human Annotation** task.

![](doc/source/images/wks/task-6-list_of_documents_within_annotation_set_chosen.png)

If you select any of the documents in the list, the **Document Annotation** panel will be displayed. Since we previously imported the corpus documents, the entity and relationship annotations are already completed (as shown in the following examples). You can annotate mentions (occurrences of words/phrases which can be annotated as an entity) to play around, or you can modify one by annotating mentions with a different entity.

![](doc/source/images/wks/task-8-annotation-ground_truth_editor.png)

![](doc/source/images/wks/task-9-annotation-ground_truth_editor.png)

### 7.2 Submit Annotation Set

From the **Task** details panel, press the **Submit All Documents** button.

![](doc/source/images/wks/task-14-annotation-submit_annotated_documents.png)

All documents should change status to **Completed**.

![](doc/source/images/wks/task-15-annotation-documents_completed_status.png)

Press the blue "File" icon to toggle back to the **Task** panel, which will show the completion percentage for each task.

![](doc/source/images/wks/task-16-annotation-green-status_completed.png)

From the **Access & Tools -> Documents** panel, select the **Task** tab and select the task to view the details panel.

![](doc/source/images/wks/task-17-annotation-annotation_set_submitted_status.png)

Select your **Annotation Set Name** and then press the **Accept** button. This step is required to ensure that the annotation set is considered **ground truth**.

> NOTE: The objective of the annotation project is to obtain ground truth, the collection of vetted data that is used to adapt WKS to a particular domain.

![](doc/source/images/wks/task-18-annotation-annotation_set_accept.png)

**Status** should now be set to **COMPLETED**.

![](doc/source/images/wks/task-20-annotation-annotation_set_accept-status_completed.png)

## 8. Create the model

Go to the **Model Management -> Performance** panel, and press the **Train and evaluate** button.

![](doc/source/images/wks/model_training_and_evaluation-1.png)

From the **Document Set** name list, select the **Annotation Set Name** you created previously and press the **Train & Evaluate** button.

![](doc/source/images/wks/model_training_and_evaluation-2.png)

This process may take several minutes to complete. Progress will be shown in the upper right corner of the panel.

> Note: In practice, you would create separate annotation sets (each containing thousands of messages) for training and evaluation.

Once complete, you will see the results of the train and evaluate process.

![](doc/source/images/wks/model_training_and_evaluation-5-training_evaluation_completed.png)

<!--
![](doc/source/images/wks/model_training_and_evaluation-7-evaluation_completed.png)
-->
You can view the log files of the process by clicking the **View Log** button.

![](doc/source/images/wks/model_training_and_evaluation-8-logs.png)

## 9. Deploy the machine learning model to NLU

Now we can deploy our new model to the already created **NLU** service. Navigate to the **Version** menu on the left and press **Take Snapshot**.

![](doc/source/images/wks/model_deployment-1.png)

The snapshot version will now be available for deployment to NLU.

![](doc/source/images/wks/model_deployment-2.png)

To start the process, click the **Deploy** button associated with your snapshot version.

Select the option to deploy to **Natural Language Understanding**.

![](doc/source/images/wks/model_deployment-3.png)

Then enter your IBM Cloud account information to locate your **NLU** service to deploy to.

![](doc/source/images/wks/model_deployment-4.png)

Once deployed, a **Model ID** will be created. Keep note of this value as it will be required later in this Code Pattern.

![](doc/source/images/wks/model_deployment-5.png)

> NOTE: You can also view this **Model ID** by pressing the **NLU** button listed with your snapshot version.

## 10. Test the model with cURL

Using cURL is the quickest way to show the advantages of WKS. Let's see the result of using NLU with and without a WKS model.

### NLU with a WKS model

In the following examples, replace `$USERNAME` and `$PASSWORD` (or `$APIKEY`) with your own **NLU** credentials. In this first example, we will also be adding an `entites.model` argument to the query string. Replace `$MODEL_ID` with your own **WKS** model ID.

The SMS text is URL encoded as it is passed as a query argument. Note that the model used to train and evaluate entities is based on a few sample SMS offers, which are located in the [data](data) directory of the local repository.

After issuing this cURL command, it is clear in the server response that we can see domain specific entities like `Offer`, `Offer_Period`, and `Merchant`.

> NOTE: Replace the API Key and URL in the command below with the one copied [from Step 2](#2-create-ibm-cloud-services).

```
curl -u "apikey:${APIKEY}" "${APIURL}/v1/analyze?version=2018-03-16&text=Valentines%20Day%20Offer%2c%20get%20Rs.10000%20cashback%20%2b%20No%20Cost%20EMI%20on%20all%20models%20of%20iPad.%20Valid%20till%20Feb%2014%20at%20your%20nearest%20Imagine.%20https%3a%2f%2fgoo.gl%2fhFJcfk&features=entities&entities.model=${MODEL_ID}"
```

```
{
  "usage": {
    "text_units": 1,
    "text_characters": 145,
    "features": 1
  },
  "language": "en",
  "entities": [
    {
      "type": "Festival_Occasion_Offers",
      "text": "Valentines Day",
      "disambiguation": {
        "subtype": [
          "NONE"
        ]
      },
      "count": 1
    },
    {
      "type": "Offer",
      "text": "get Rs.10000 cashback + No Cost EMI",
      "disambiguation": {
        "subtype": [
          "NONE"
        ]
      },
      "count": 1
    },
    {
      "type": "Offer_Applicable",
      "text": "on all models of iPad",
      "disambiguation": {
        "subtype": [
          "NONE"
        ]
      },
      "count": 1
    },
    {
      "type": "Validity_Period",
      "text": "Valid till Feb 14",
      "disambiguation": {
        "subtype": [
          "NONE"
        ]
      },
      "count": 1
    },
    {
      "type": "Merchant",
      "text": "Imagine",
      "disambiguation": {
        "subtype": [
          "NONE"
        ]
      },
      "count": 1
    },
    {
      "type": "Merchant_URL",
      "text": "https://goo.gl/hFJcfk",
      "disambiguation": {
        "subtype": [
          "NONE"
        ]
      },
      "count": 1
    }
  ]
}
```

### NLU without a WKS model

Using **NLU** without a **WKS** model ID is less ideal, as the server does not extract the entities we are looking for. It extracts generic data such as company name and some location details, but it does not extract the domain specific offer details we desire.

> NOTE: Replace the API Key and URL in the command below with the one copied [from Step 2](#2-create-ibm-cloud-services).

```
curl -u "apikey:${APIKEY}" "${APIURL}/v1/analyze?version=2018-03-16&text=Valentines%20Day%20Offer%2c%20get%20Rs.10000%20cashback%20%2b%20No%20Cost%20EMI%20on%20all%20models%20of%20iPad.%20Valid%20till%20Feb%2014%20at%20your%20nearest%20Imagine.%20https%3a%2f%2fgoo.gl%2fhFJcfk&features=entities"
```

```
{
  "usage": {
    "text_units": 1,
    "text_characters": 145,
    "features": 1
  },
  "language": "en",
  "entities": [
    {
      "type": "Location",
      "text": "Imagine. https://goo.gl/hFJcfk",
      "relevance": 0.249854,
      "disambiguation": {
        "subtype": [
          "City"
        ]
      },
      "count": 1
    }
  ]
}
```

## 11. Run the application

Use the ``Deploy to IBM Cloud`` button **OR** run locally.

### Deploy to IBM Cloud

[![Deploy to Bluemix](https://cloud.ibm.com/devops/setup/deploy/button.png)](https://cloud.ibm.com/devops/setup/deploy?repository=https://github.com/IBM/sms-analysis-with-wks)

1. Press the above ``Deploy to IBM Cloud`` button and then click on ``Deploy``.

2. In Toolchains, click on Delivery Pipeline to watch while the app is deployed. Once deployed, the app can be viewed by clicking 'View app'.

![](doc/source/images/toolchain-pipeline.png)

3. To see the app and services created and configured for this journey, use the IBM Cloud dashboard. The app is named `sms-analysis-with-wks` with a unique suffix. The following service is created:
    * sms-nlu-service

4. One last step is required to configure the app to run with your WKS model.

In the IBM Cloud dashboard, find the app that was created and click on it to access the details panel. Click on `Runtime` on the menu and navigate to the `Environment variables` tab.

![](doc/source/images/set-env.png)

Set the `MODEL_ID` to the value you saved in Step 9.

Save the new values and restart the application (re-start should occur automatically), and watch the log for errors.  

### Run locally

#### Pre-requisite

Maven >= 3.5 is used to build, test, and run. Check your maven version using the following command:

```
mvn -v
```

To download and install maven, refer to [maven.](https://maven.apache.org/download.cgi)

#### Add NLU Credentials and WKS Model ID to config.properties file

The config.properties file is located in the `src/main/resources` directory. Replace the default values with the appropriate credentials (either API key, or username/password) and model ID values (quotes are not required).

```
# Watson Natural Language Understanding
NATURAL_LANGUAGE_UNDERSTANDING_URL=<add_nlu_url>
## Un-comment and use either username+password or IAM apikey.
NATURAL_LANGUAGE_UNDERSTANDING_IAM_APIKEY=<add_nlu_iam_apikey>
#NATURAL_LANGUAGE_UNDERSTANDING_USERNAME=<add_nlu_username>
#NATURAL_LANGUAGE_UNDERSTANDING_PASSWORD=<add_nlu_password>

# Watson Knowledge Studio Model ID
WATSON_KNOWLEDGE_STUDIO_MODEL_ID=<add_model_id>
```

#### Build and Run

In your project root directory, use maven to build and deploy the WAR file and start the app.

```
mvn clean install
mvn liberty:run-server
```

Browse to http://localhost:9080 to see the app.

#### Testing

To run the unit tests:

```
mvn test
```

# Sample UI layout

![](doc/source/images/deployed-app.png)

# Troubleshooting

### Errors associated with running the app:

**Error:com.ibm.watson.developer_cloud.service.exception.UnauthorizedException: Unauthorized: Access is denied due to invalid credentials**

> This should only occur if running locally. Check to ensure the credentials listed in `/src/main/resources/config.properties` matches the credentials assigned to your NLU service.

**Error:com.ibm.watson.developer_cloud.service.exception.NotFoundException: model not found**

> If running locally, check to ensure the **WATSON_KNOWLEDGE_STUDIO_MODEL_ID** listed in `/src/main/resources/config.properties` matches the the model ID you deployed to your NLU instance, as described in [Step #9](#9-deploy-the-machine-learning-model-to-nlu) above.

> If you have deployed your app on the IBM Cloud, check to ensure the MODEL_ID environment variable in your runtime instance is set correctly, as described in the **Deploy to IBM Cloud** section of [Step #11](#11-run-the-application) above.

# Learn more

* **Artificial Intelligence Code Patterns**: Enjoyed this Code Pattern? Check out our other [AI Code Patterns](https://developer.ibm.com/technologies/artificial-intelligence/).
* **AI and Data Code Pattern Playlist**: Bookmark our [playlist](https://www.youtube.com/playlist?list=PLzUbsvIyrNfknNewObx5N7uGZ5FKH0Fde) with all of our Code Pattern videos
* **With Watson**: Want to take your Watson app to the next level? Looking to utilize Watson Brand assets? [Join the With Watson program](https://www.ibm.com/watson/with-watson/) to leverage exclusive brand, marketing, and tech resources to amplify and accelerate your Watson embedded commercial solution.
* [Procurement Analysis with WKS](https://github.com/IBM/procurement-analysis-with-wks): Another code pattern that is focused on a procurement use case where WKS is used with Discovery and IBM Graph.

# License

This code pattern is licensed under the Apache Software License, Version 2.  Separate third party code objects invoked within this code pattern are licensed by their respective providers pursuant to their own separate licenses. Contributions are subject to the [Developer Certificate of Origin, Version 1.1 (DCO)](https://developercertificate.org/) and the [Apache Software License, Version 2](https://www.apache.org/licenses/LICENSE-2.0.txt).

[Apache Software License (ASL) FAQ](https://www.apache.org/foundation/license-faq.html#WhatDoesItMEAN)