# wks-nlu-sms-analysis
[![Build Status](https://travis-ci.org/IBM/watson-online-store.svg?branch=master)](https://travis-ci.org/ragudiko/wks-nlu-sms-analysis)
This is cognitive sms client which uses natural language understanding capability to analyze the sms and extracts entity data required.

Background: Current natural language processing techniques cannot extract/interpret the data as required by domain/industry specific. The data(entities) represent different meaning in different domain. Best answer to such problem is IBM Watson Knowledge Studio.
Consider an example where we need to extract entities present in commercial sms.
In such commercial sms usually interesting entities to be extracted are
   1. what is the offer
   2. who is providing(merchant)
   3. offer name(if present in sms)
   4. offer validity period

The secondary info like merchant phone, website link etc. may also be captured if required.

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


## Deploy the Machine learning model to NLU
<img src="images/wks-nlu-deploy-1.png" width="800" height="350" align="center">

<img src="images/wks-nlu-deploy-2.png" width="800" height="350" align="center">

Usage
You can run simple java client provided in this project to extract the entities from sms.

Alternatively you can use the curl commands.

NLU without wks model

curl -u "username":"password" "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=DUNKI%20DONUTS%20is%20now%20open%20at%20Girgaum%20Chowpatty.%20Walk-in%20and%20enjoy%20the%20Valentaine%20SPL%20offer%20on%20your%20favorite%20Donuts.%20Buy%203%20%26%20Get%203%20FREE.%20Valid%20till%2015%20Feb%202017.%20T%26C&features=entities"

NLU with WKS model

curl -u "username":"password" "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=DUNKI%20DONUTS%20is%20now%20open%20at%20Girgaum%20Chowpatty.%20Walk-in%20and%20enjoy%20the%20Valentaine%20SPL%20offer%20on%20your%20favorite%20Donuts.%20Buy%203%20%26%20Get%203%20FREE.%20Valid%20till%2015%20Feb%202017.%20T%26C&features=entities&entities.model=10:2845cc50-8099-4cc6-89a1-95593e460cf0"

Replace username/password highlighted with your NLU service credentials. The entities.model is the wks machine learning model id. The sms text has to be URL encoded as it is passed as url query string in Curl command.

# Get your hands on with the NLU api and WKS tool
You can register at https://console.bluemix.net to access NLU.

You can try free version of WKS : https://www.ibm.com/in-en/marketplace/supervised-machine-learning

## Additional Resource
You can refer https://github.com/rameshpoomalai/ProcurementSystem for procurement use case where we have used WKS, Discovery and IBM Graph.
