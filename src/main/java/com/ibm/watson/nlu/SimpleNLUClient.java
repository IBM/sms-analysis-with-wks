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
	
	NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
			  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
			  "d953cbc9-7376-4b0f-929e-6cffae715179",
			  "F7HF1ARcPIgC"
			);
	private int maxResponses = Integer.MAX_VALUE;  // maximum # of responses to return, default value
	
	public SimpleNLUClient(){
		
	}

	public void analyze(){
		
		ListModelsResults models = service
				  .getModels()
				  .execute();
				System.out.println(models);
				
				
		EntitiesOptions entities = new EntitiesOptions.Builder()
	            .emotion(false)
	            //.limit(maxResponses)
	            .sentiment(true)
	            .model("10:99ce088d-d8d8-4da3-8bd3-5d33d14685f5") //Alpharithm
	            //.model("10:7b2e3bde-f937-4318-aa71-54760405aab1") //Alpharithm_msg21_to_30_msg37_msg39_duplicates
	            .build();

		Features features = new Features.Builder().entities(entities).build();
		
		
		Builder builder = new AnalyzeOptions.Builder()
	            .features(features)
	            .returnAnalyzedText(true);
		//builder.text("We Miss U @Sree; Order Ur Fav Pizza Now; Buy 1 Regular Pizza & Get 40% OFF. Walk-In/Order@ 68886888/ goo.gl/CQThqp Cpn: CRM7BEA217E4F Valid till 21 Mar T&C");
		builder.text("Get 1+1 on booking Cinepolis Cinemas tickets on XYZ. Use code: LOVEFEB to avail the offer. Max. Cashback* Rs.500. Click http://m.p-y.tm/mcn. *T&C apply");
		 AnalyzeOptions parameters = builder.build();
	        AnalysisResults results;
	        try {
	            results = service.analyze(parameters).execute();
	            System.out.println(results);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
	public static void main(String a[]){
		SimpleNLUClient client = new SimpleNLUClient();
		client.analyze();
	}
}
