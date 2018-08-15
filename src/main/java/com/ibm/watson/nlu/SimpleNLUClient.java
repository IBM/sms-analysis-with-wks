/*
 * This class play a role of NLU client. It analyzes the sms fed to it and extracts entity data.
 */

package com.ibm.watson.nlu;

import java.util.logging.Level;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ListModelsResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions.Builder;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class SimpleNLUClient {

	private NaturalLanguageUnderstanding service;
	private int maxResponses = Integer.MAX_VALUE;  // maximum # of responses to return, default value
	private String versionDate = "2018-03-16";


	public void initService(String userName, String password, String optionalURL){
		service = null;
		service= new NaturalLanguageUnderstanding(versionDate, userName, password);
		if(optionalURL !="")
		{
			service.setEndPoint(optionalURL);
		}
	}

	public void initIamService(String apikey, String url){
		service = null;
		IamOptions options = new IamOptions.Builder()
			.apiKey(apikey)
			.build();

		service= new NaturalLanguageUnderstanding(versionDate, options);
		service.setEndPoint(url);
	}

	public SimpleNLUClient(){

	}

	public AnalysisResults analyze(String modelId, String smsText){

		EntitiesOptions entities = new EntitiesOptions.Builder()
	            .emotion(false)
	            //.limit(maxResponses)
	            .sentiment(true)
	            .model(modelId)
	            .build();

		Features features = new Features.Builder().entities(entities).build();

		Builder builder = new AnalyzeOptions.Builder()
	            .features(features)
	            .returnAnalyzedText(true);
		builder.text(smsText);
		builder.language("en");
	 	AnalyzeOptions parameters = builder.build();
		return service.analyze(parameters).execute();

	}
}
