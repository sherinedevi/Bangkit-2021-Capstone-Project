### Bangkit 2021 Capstone Project
This is the Capstone Project of team B21-CAP0138 for Bangkit 2021

# Parkhere – Your smart parking solution
## Overview
One of smart city foundations lies on ease of mobility, including parking. But many times,  drivers enter parking lots wasting time searching for empty space to eventually find out there is none left. Therefore, a smart parking system is needed to improve parking operations in Indonesia. We propose an application called Parkhere where users can see counts of empty and occupied parking lots space based on CCTV footages. We will develop an effective and efficient parking app by finding out the suitable Machine Learning model, design that provides the best UX, and utilizing Cloud to store, process, and manage data. Every path on our team has done the following:
- Machine Learning: Acquiring dataset and preprocessing them (splitting, annotating the parking space coordinates), building several models with TensorFlow then training and validating them to predict parking occupancy, then saving the best model (in .h5 format) to be deployed. Creating the main pipeline that gets the frame of a specific time, loads the model and coordinates points, and finally predicts the occupancy of each parking spot and adds the occupancy status of each spot with colored boxes on the frame.
- Android: Creating the user app to show information of the places. By using the REST API built by the cloud team we are able to get necessary information through our backend infrastructure with ease and then show it in our application in real time.
- Cloud: Building REST API to communicate between backend core logic and mobile clients, leveraging Google App Engine to dynamically scale server instances & Cloud Storage buckets to manage objects

Our team has successfully built this application based on our planned execution. This application can show all the public places, show the available parking spots left, provide some information about those places, and present the CCTV footage image with empty parking spots denoted by green (occupied) color boxes. We used recorded CCTV footage from Polresta Bandar Lampung to simulate our local deployment.

## App Demo

<img src="./Media/Demo%20Screen.gif" width="60%"/>