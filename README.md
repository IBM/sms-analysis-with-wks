# wks-nlu-sms-analysis
[![Build Status](https://travis-ci.org/IBM/watson-online-store.svg?branch=master)](https://travis-ci.org/ragudiko/wks-nlu-sms-analysis)
This is cognitive sms client which uses natural language understanding capability to analyze the sms and extracts entity data required.

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

<img src="images/WKS-NLU-processh.png" width="800" height="350" align="center">

## Technical Architecture

<img src="images/Technical Architecture.png" width="800" height="350" align="center">

<img src="images/Technical Architecture - 2.png" width="800" height="350" align="center">


## Features
1. User can feed sms to NLU which has machine learning model deployed in it.
2. The NLU analyzes the sms and extracts the domain specific entities.
3. The extracted entities will provide info like what is the offer, who is providing offer and offer valid date etc.


## Deploy the Machine learning model to NLU
<img src="images/wks-nlu-deploy-1.png" width="800" height="350" align="center">

<img src="images/wks-nlu-deploy-2.png" width="800" height="350" align="center">
