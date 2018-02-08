@@ -19,20 +19,20 @@ The above requirement can be achieved by using our cognitive sms analysis.
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

<img src="doc/source/images/wks-nlu-process.png" width="800" height="350" align="center">
![](doc/source/images/wks-nlu-process.png)

## Technical Architecture

<img src="doc/source/images/technical_architecture_2.png" width="800" height="350" align="center">
![](doc/source/images/technical_architecture_2.png)


## Features
@@ -41,95 +41,94 @@ If Apache Maven is being used, the following dependency should be included:
3. The extracted entities will provide info like what is the offer, who is providing offer and offer valid date etc.

## Create NLU service
<img src="doc/source/images/nlu/create_nlu_service-1.png" width="800" height="350" align="center">
<img src="doc/source/images/nlu/create_nlu_service-2.png" width="800" height="350" align="center">
<img src="doc/source/images/nlu/create_nlu_service-3.png" width="800" height="350" align="center">
<img src="doc/source/images/nlu/create_nlu_service-4.png" width="800" height="350" align="center">
<img src="doc/source/images/nlu/create_nlu_service-5.png" width="800" height="350" align="center">

![](doc/source/images/nlu/create_nlu_service-1.png)
![](doc/source/images/nlu/create_nlu_service-2.png)
![](doc/source/images/nlu/create_nlu_service-3.png)
![](doc/source/images/nlu/create_nlu_service-4.png)
![](doc/source/images/nlu/create_nlu_service-5.png)

## Create WKS model and deploy the model to nlu
1. Create wks service instance
<img src="doc/source/images/wks/dashboard.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/creating_knowledge_studio_service-1.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/creating_knowledge_studio_service-2.png" width="800" height="350" align="center">
![](doc/source/images/wks/dashboard.png)
![](doc/source/images/wks/creating_knowledge_studio_service-1.png)
![](doc/source/images/wks/creating_knowledge_studio_service-2.png)

2. Create workspace
<img src="doc/source/images/wks/knowledge_studio_launch_page.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/workspace_page.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/create_new_workspace.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/workspace_creation_done.png" width="800" height="350" align="center">
![](doc/source/images/wks/knowledge_studio_launch_page.png)
![](doc/source/images/wks/workspace_page.png)
![](doc/source/images/wks/create_new_workspace.png)
![](doc/source/images/wks/workspace_creation_done.png)

3. Import Type System navigating to wks-resources/types-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.json
<img src="doc/source/images/wks/type_system-4_upload_type_system_entry.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/type_system-5_entries.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/type_system-6_create_relation_entry.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/type_system-7_relation_entries.png" width="800" height="350" align="center">
![](doc/source/images/wks/type_system-4_upload_type_system_entry.png)
![](doc/source/images/wks/type_system-5_entries.png)
![](doc/source/images/wks/type_system-6_create_relation_entry.png)
![](doc/source/images/wks/type_system-7_relation_entries.png)

4. Import Corpus Documents navigating to wks-resources/corpus-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.zip
<img src="doc/source/images/wks/documents-2-upload_corpus_documents.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/documents-3-upload_corpus_documents.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/documents-4-upload_corpus_documents.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/Documents-6-rename.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/Documents-7-rename.png" width="800" height="350" align="center">
![](doc/source/images/wks/documents-4-upload_corpus_documents.png)
![](doc/source/images/wks/documents-6-rename.png)
![](doc/source/images/wks/documents-7-rename.png)

5. Create Annotation Set
<img src="doc/source/images/wks/documents-9-create_annotation_set.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/documents-10-create_annotation_set.png" width="800" height="350" align="center">
![](doc/source/images/wks/documents-9-create_annotation_set.png)
![](doc/source/images/wks/documents-10-create_annotation_set.png)

6. Create Task for Human Annotation
## Since we imported corpus documents, we have already have annotation done for you. You can look at entities and relationships already annotated. You can annotate mentions(occurrences of words/phrases which can be annotated as entity). If you do not want corpus documents, you can fresh documents in step 4.
<img src="doc/source/images/wks/task-1-page.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-2-create_task.pngg" width="800" height="350" align="center">
![](doc/source/images/wks/task-1-page.png)
![](doc/source/images/wks/task-2-create_task.png)

### Select Annotation Set for this task
<img src="doc/source/images/wks/task-3-create_task-select_annotation_set.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-4-task_created.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-5-list_of_annotation_set_for_this_task.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-6-list_of_documents_within_annotation_set_chosen.pn" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-7-start_annotation-ground_truth_editor.png" width="800" height="350" align="center">
![](doc/source/images/wks/task-3-create_task-select_annotation_set.png)
![](doc/source/images/wks/task-4-task_created.png)
![](doc/source/images/wks/task-5-list_of_annotation_set_for_this_task.png)
![](doc/source/images/wks/task-6-list_of_documents_within_annotation_set_chosen.png)
![](doc/source/images/wks/task-7-start_annotation-ground_truth_editor.png)

### Start Human Annotation clicking Annotate button
<img src="doc/source/images/wks/task-8-annotation-ground_truth_editor.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-9-annotation-ground_truth_editor.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-10-annotation-ground_truth_editor.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-11-annotation-ground_truth_editor.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-12-annotation-documents_status.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-13-annotation-documents_status.png" width="800" height="350" align="center">
![](doc/source/images/wks/task-8-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-9-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-10-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-11-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-12-annotation-documents_status.png)
![](doc/source/images/wks/task-13-annotation-documents_status.png)

7. Submit Annotation Set for which human annotation is complete
<img src="doc/source/images/wks/task-14-annotation-submit_annotated_documents.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-15-annotation-documents_completed_status.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-16-annotation-green-status_completed.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-17-annotation-annotation_set_-submitted_status.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-18-annotation-annotation_set_-accept.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-19-annotation-annotation_set_-accept.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/task-20-annotation-annotation_set_-accept-status_completed" width="800" height="350" align="center">
![](doc/source/images/wks/task-14-annotation-submit_annotated_documents.png)
![](doc/source/images/wks/task-15-annotation-documents_completed_status.png)
![](doc/source/images/wks/task-16-annotation-green-status_completed.png)
![](doc/source/images/wks/task-17-annotation-annotation_set_-submitted_status.png)
![](doc/source/images/wks/task-18-annotation-annotation_set_-accept.png)
![](doc/source/images/wks/task-19-annotation-annotation_set_-accept.png)
![](doc/source/images/wks/task-20-annotation-annotation_set_-accept-status_completed)

8. Create model, train and evaluate
<img src="doc/source/images/wks/model_training_and_evaluation-1.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_training_and_evaluation-2.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_training_and_evaluation-3.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_training_and_evaluation-4-training_in_progress.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_training_and_evaluation-5-training_and_evaluation_completed.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_training_and_evaluation-6-logs.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_training_and_evaluation-7-multiple_training_and_evaluation_completed.png" width="800" height="350" align="center">
![](doc/source/images/wks/model_training_and_evaluation-1.png)
![](doc/source/images/wks/model_training_and_evaluation-2.png)
![](doc/source/images/wks/model_training_and_evaluation-3.png)
![](doc/source/images/wks/model_training_and_evaluation-4-training_in_progress.png)
![](doc/source/images/wks/model_training_and_evaluation-5-training_and_evaluation_completed.png)
![](doc/source/images/wks/model_training_and_evaluation-6-logs.png)
![](doc/source/images/wks/model_training_and_evaluation-7-multiple_training_and_evaluation_completed.png)

9. Deploy the Machine Learning model to NLU
<img src="doc/source/images/wks/model_deployment-1.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_deployment-2.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_deployment-3.png" width="800" height="350" align="center">
<img src="doc/source/images/wks/model_deployment-4.png" width="800" height="350" align="center">
![](doc/source/images/wks/model_deployment-1.png)
![](doc/source/images/wks/model_deployment-2.png)
![](doc/source/images/wks/model_deployment-3.png)
![](doc/source/images/wks/model_deployment-4.png)
