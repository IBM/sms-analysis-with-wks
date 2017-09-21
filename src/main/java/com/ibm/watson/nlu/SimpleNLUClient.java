/*
 * This class play a role of NLU client. It analyzes the sms fed to it and extracts entity data.
 */

package com.ibm.watson.nlu;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ListModelsResults;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions.Builder;

public class SimpleNLUClient {

	private NaturalLanguageUnderstanding service;
	private int maxResponses = Integer.MAX_VALUE;  // maximum # of responses to return, default value


	public void initService(String userName,String password, String optionalURL){
		service = null;
		if(optionalURL !="")
		{
				service= new NaturalLanguageUnderstanding( NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
				service.setApiKey("");
				service.setEndPoint(optionalURL);
		}
		else
		{
			service= new NaturalLanguageUnderstanding( NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,userName,password);
		}

	}


	public SimpleNLUClient(){

	}

	public AnalysisResults	 analyze(String modelId, String smsText){

		// ListModelsResults models = service
		// 		  .getModels()
		// 		  .execute();
		// 		System.out.println(models);


		EntitiesOptions entities = new EntitiesOptions.Builder()
	            .emotion(false)
	            //.limit(maxResponses)
	            .sentiment(true)
	            .model(modelId) //Alpharithm
	            //.model("10:7b2e3bde-f937-4318-aa71-54760405aab1") //Alpharithm_msg21_to_30_msg37_msg39_duplicates
	            .build();

		Features features = new Features.Builder().entities(entities).build();


		Builder builder = new AnalyzeOptions.Builder()
	            .features(features)
	            .returnAnalyzedText(true);
		//builder.text("We Miss U @Sree; Order Ur Fav Pizza Now; Buy 1 Regular Pizza & Get 40% OFF. Walk-In/Order@ 68886888/ goo.gl/CQThqp Cpn: CRM7BEA217E4F Valid till 21 Mar T&C");
		builder.text(smsText);
	 	AnalyzeOptions parameters = builder.build();
    AnalysisResults results = null;
    try {

        results = service.analyze(parameters).execute();
    }
    catch (Exception e) {
    	e.printStackTrace();
			System.out.println("Error:"+e);
    }
		return results;
	}
	public static void main(String a[]){
		SimpleNLUClient client = new SimpleNLUClient();
		client.initService("de40afab-f410-4012-bb00-2d878ffc18f2", "oclD356nMDxV", "");
		AnalysisResults response = client.analyze("10:8a91f680-4eb0-4c7b-b37e-193bb124bc18","Get 1+1 on booking Cinepolis Cinemas tickets on XYZ. Use code: LOVEFEB to avail the offer. Max. Cashback* Rs.500. Click http://m.p-y.tm/mcn. *T&C apply");
		System.out.println("Result:"+response);
	}
}
