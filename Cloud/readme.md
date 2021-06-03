For model deployment we use Cloud Storage buckets to manage relevant ML objects and load them using Tensorflow Keras library in main code.

For API deployment we use Flask and App Engine to handle incoming requests and send JSON responses.

Here's how to replicate what we did in the Cloud:
1. Set up the project region & zone
2. Upload your ML & other objects onto Cloud Storage buckets
3. Open Cloud Shell, create a new folder, in it create three files and paste the content: app.yaml; main.py; requirements.txt 
4. Fine tune your instance configuration, scaling, & more in app.yaml (For more information: https://cloud.google.com/appengine/docs/standard/python3/config/appref)
5. Load your objects blob using Cloud Client library in main.py (Sample: https://github.com/GoogleCloudPlatform/python-docs-samples/blob/master/appengine/standard/migration/storage/main.py)
6. Finally, run "gcloud app deploy" to deploy your instance. You can run "gcloud app browse" to see your application URL
7. Congrats! Your mobile clients can now send requests to specified route and get responses
