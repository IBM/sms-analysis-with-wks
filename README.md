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

<img src="images/WKS-NLU-process.png" width="800" height="350" align="center">
![](doc/source/images/wks-nlu-process.png)

## Technical Architecture

<img src="images/Technical Architecture - 2.png" width="800" height="350" align="center">
![](doc/source/images/technical_architecture_-_2.png)


## Features
@@ -41,95 +41,94 @@ If Apache Maven is being used, the following dependency should be included:
3. The extracted entities will provide info like what is the offer, who is providing offer and offer valid date etc.

## Create NLU service
<img src="images/nlu/Create nlu service-1.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-2.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-3.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-4.png" width="800" height="350" align="center">
<img src="images/nlu/Create nlu service-5.png" width="800" height="350" align="center">

![](doc/source/images/nlu/create_nlu_service-1.png)
![](doc/source/images/nlu/create_nlu_service-2.png)
![](doc/source/images/nlu/create_nlu_service-3.png)
![](doc/source/images/nlu/create_nlu_service-4.png)
![](doc/source/images/nlu/create_nlu_service-5.png)

## Create WKS model and deploy the model to nlu
1. Create wks service instance
<img src="images/wks/Dashboard.png" width="800" height="350" align="center">
<img src="images/wks/Creating knowledge studio service-1.png" width="800" height="350" align="center">
<img src="images/wks/Creating knowledge studio service-2.png" width="800" height="350" align="center">
![](doc/source/images/wks/dashboard.png)
![](doc/source/images/wks/creating_knowledge_studio_service-1.png)
![](doc/source/images/wks/creating_knowledge_studio_service-2.png)

2. Create workspace
<img src="images/wks/knowledge studio launch page.png" width="800" height="350" align="center">
<img src="images/wks/Workspace page.png" width="800" height="350" align="center">
<img src="images/wks/Create new workspace.png" width="800" height="350" align="center">
<img src="images/wks/Workspace creation done.png" width="800" height="350" align="center">
![](doc/source/images/wks/knowledge_studio_launch_page.png)
![](doc/source/images/wks/workspace_page.png)
![](doc/source/images/wks/create_new_workspace.png)
![](doc/source/images/wks/workspace_creation_done.png)

3. Import Type System navigating to wks-resources/types-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.json
<img src="images/wks/Type System-4- upload type system entry.png" width="800" height="350" align="center">
<img src="images/wks/Type System-5- entries.png" width="800" height="350" align="center">
<img src="images/wks/Type System-6- create relation entry.png" width="800" height="350" align="center">
<img src="images/wks/Type System-7- relation entries.png" width="800" height="350" align="center">
![](doc/source/images/wks/type_system-4-_upload_type_system_entry.png)
![](doc/source/images/wks/type_system-5-_entries.png)
![](doc/source/images/wks/type_system-6-_create_relation_entry.png)
![](doc/source/images/wks/type_system-7-_relation_entries.png)

4. Import Corpus Documents navigating to wks-resources/corpus-a6850330-3aeb-11e7-bf5c-f98dfa3ddf29.zip
<img src="images/wks/Documents-4-upload corpus documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-5-upload corpus documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-6-upload corpus documents.png" width="800" height="350" align="center">
<img src="images/wks/Documents-6-rename.png" width="800" height="350" align="center">
<img src="images/wks/Documents-7-rename.png" width="800" height="350" align="center">
![](doc/source/images/wks/documents-4-upload_corpus_documents.png)
![](doc/source/images/wks/documents-6-rename.png)
![](doc/source/images/wks/documents-7-rename.png)

5. Create Annotation Set
<img src="images/wks/Documents-9-create annotation set.png" width="800" height="350" align="center">
<img src="images/wks/Documents-10-create annotation set.png" width="800" height="350" align="center">
![](doc/source/images/wks/documents-9-create_annotation_set.png)
![](doc/source/images/wks/documents-10-create_annotation_set.png)

6. Create Task for Human Annotation
<img src="images/wks/Task-1-page.png" width="800" height="350" align="center">
<img src="images/wks/Task-2-create task.png" width="800" height="350" align="center">
![](doc/source/images/wks/task-1-page.png)
![](doc/source/images/wks/task-2-create_task.png)

### Select Annotation Set for this task
<img src="images/wks/Task-3-create task-select annotation set.png" width="800" height="350" align="center">
<img src="images/wks/Task-4-task created.png" width="800" height="350" align="center">
<img src="images/wks/Task-5-list of annotation set for this task.png" width="800" height="350" align="center">
<img src="images/wks/Task-6-list of documents within annotation set chosen.png" width="800" height="350" align="center">
<img src="images/wks/Task-7-start annotation-ground truth editor.png" width="800" height="350" align="center">
![](doc/source/images/wks/task-3-create_task-select_annotation_set.png)
![](doc/source/images/wks/task-4-task_created.png)
![](doc/source/images/wks/task-5-list_of_annotation_set_for_this_task.png)
![](doc/source/images/wks/task-6-list_of_documents_within_annotation_set_chosen.png)
![](doc/source/images/wks/task-7-start_annotation-ground_truth_editor.png)

### Start Human Annotation clicking Annotate button
<img src="images/wks/Task-8-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-9-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-10-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-11-annotation-ground truth editor.png" width="800" height="350" align="center">
<img src="images/wks/Task-12-annotation-documents status.png" width="800" height="350" align="center">
<img src="images/wks/Task-13-annotation-documents status.png" width="800" height="350" align="center">
![](doc/source/images/wks/task-8-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-9-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-10-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-11-annotation-ground_truth_editor.png)
![](doc/source/images/wks/task-12-annotation-documents_status.png)
![](doc/source/images/wks/task-13-annotation-documents_status.png)

7. Submit Annotation Set for which human annotation is complete
<img src="images/wks/Task-14-annotation-submit annotated documents.png" width="800" height="350" align="center">
<img src="images/wks/Task-15-annotation-documents completed status.png" width="800" height="350" align="center">
<img src="images/wks/Task-16-annotation-green-status completed.png" width="800" height="350" align="center">
<img src="images/wks/Task-17-annotation-annotation set -submitted status.png" width="800" height="350" align="center">
<img src="images/wks/Task-18-annotation-annotation set -accept.png" width="800" height="350" align="center">
<img src="images/wks/Task-19-annotation-annotation set -accept.png" width="800" height="350" align="center">
<img src="images/wks/Task-20-annotation-annotation set -accept-status completed.png" width="800" height="350" align="center">
![](doc/source/images/wks/task-14-annotation-submit_annotated_documents.png)
![](doc/source/images/wks/task-15-annotation-documents_completed_status.png)
![](doc/source/images/wks/task-16-annotation-green-status_completed.png)
![](doc/source/images/wks/task-17-annotation-annotation_set_-submitted_status.png)
![](doc/source/images/wks/task-18-annotation-annotation_set_-accept.png)
![](doc/source/images/wks/task-19-annotation-annotation_set_-accept.png)
![](doc/source/images/wks/task-20-annotation-annotation_set_-accept-status_completed)

8. Create model, train and evaluate
<img src="images/wks/Model Training and Evaluation-1.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-2.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-3.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-4-training in progress.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-5-training and evaluation completed.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-6-logs.png" width="800" height="350" align="center">
<img src="images/wks/Model Training and Evaluation-7-multiple training and evaluation completed.png" width="800" height="350" align="center">
![](doc/source/images/wks/model_training_and_evaluation-1.png)
![](doc/source/images/wks/model_training_and_evaluation-2.png)
![](doc/source/images/wks/model_training_and_evaluation-3.png)
![](doc/source/images/wks/model_training_and_evaluation-4-training_in_progress.png)
![](doc/source/images/wks/model_training_and_evaluation-5-training_and_evaluation_completed.png)
![](doc/source/images/wks/model_training_and_evaluation-6-logs.png)
![](doc/source/images/wks/model_training_and_evaluation-7-multiple_training_and_evaluation_completed.png)

9. Deploy the Machine Learning model to NLU
<img src="images/wks/Model Deployment-1.png" width="800" height="350" align="center">
<img src="images/wks/Model Deployment-2.png" width="800" height="350" align="center">
<img src="images/wks/Model Deployment-3.png" width="800" height="350" align="center">
<img src="images/wks/Model Deployment-4.png" width="800" height="350" align="center">
![](doc/source/images/wks/model_deployment-1.png)
![](doc/source/images/wks/model_deployment-2.png)
![](doc/source/images/wks/model_deployment-3.png)
![](doc/source/images/wks/model_deployment-4.png)
