/*
 * This class play a role of NLU client. It analyzes the sms fed to it and extracts entity data.
 */

package com.ibm.watson.nlu;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ListModelsResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions.Builder;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class SimpleNLUClient {

	private static Logger logger = Logger.getLogger(SimpleNLUClient.class.getName());
	private NaturalLanguageUnderstanding service;
	private int maxResponses = Integer.MAX_VALUE;  // maximum # of responses to return, default value
	private String versionDate = "2018-03-16";


	public void initService(String userName, String password, String optionalURL){
		service = null;
		logger.info("create NLU service with uname/pwd");
		service= new NaturalLanguageUnderstanding(versionDate, userName, password);
		if(optionalURL !="")
		{
				service.setEndPoint(optionalURL);
				logger.info("set endpoint to: " + optionalURL);
		}
	}

	public void initIamService(String apikey, String url){
		logger.info("create NLU service with apikey");
		service = null;
		IamOptions options = new IamOptions.Builder()
			.apiKey(apikey)
			.build();

		service= new NaturalLanguageUnderstanding(versionDate, options);
		service.setEndPoint(url);
		logger.info("set endpoint to: " + url);
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
		//builder.text("We Miss U @Sree; Order Ur Fav Pizza Now; Buy 1 Regular Pizza & Get 40% OFF. Walk-In/Order@ 68886888/ goo.gl/CQThqp Cpn: CRM7BEA217E4F Valid till 21 Mar T&C");
		builder.text(smsText);
		builder.language("en");
	 	AnalyzeOptions parameters = builder.build();
		return service.analyze(parameters).execute();

	}
	/*public static void main(String a[]){
		SimpleNLUClient client = new SimpleNLUClient();
		client.initService("de40afab-f410-4012-bb00-2d878ffc18f2", "oclD356nMDxV", "");
		AnalysisResults response = client.analyze("10:8a91f680-4eb0-4c7b-b37e-193bb124bc18","Get 1+1 on booking Cinepolis Cinemas tickets on XYZ. Use code: LOVEFEB to avail the offer. Max. Cashback* Rs.500. Click http://m.p-y.tm/mcn. *T&C apply");
		System.out.println("Result:"+response);
	}*/
}
