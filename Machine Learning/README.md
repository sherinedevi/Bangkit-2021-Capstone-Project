# Machine Learning
This is the Machine Learning process of Parkhere: <br>
 1. Acquiring dataset and preprocessing (can be found [here](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/tree/main/Machine%20Learning/Preprocessing)).
2. Model training and validating (can be found [here](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/tree/main/Machine%20Learning/Models)).
3. Building the main program to predict new data from video input (code in this [notebook](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/blob/main/Machine%20Learning/main.ipynb)).


## Main program
This program consist of two main parts: getting the intended frame and predict the empty slots and counts. <br>

To get the intended frame, call the get_frame() function with the path to the video and hour, minute, and second of the intended frame. For example, this will get the frame on 2:11 of afternoon.mp4 video:
``` python
frame = get_frame('afternoon.mp4', hour=0, minute=2, second=11)
```
Load the model by specifying the path to the model (this project use vgg16):
``` python
model = load_model('vgg16.h5')
```
Setting up the coordinates:
``` python
coordinates = load_box('points_polresta_fixed.pkl')
```
Predict the earlier frame with the model and coordinates:
``` python
empty, prediction = predict(frame, model, coordinates)
```
The predict() function returns the number of empty slots (in empty) and the frame image with prediction boxes (in prediction). <br>

Here is the prediction result on afternoon video on timestamp 2:11:
```python
cv2_imshow(prediction)
```
![result](https://raw.githubusercontent.com/sherinedevi/Bangkit-2021-Capstone-Project/main/Machine%20Learning/result_prediction.png)
```python
print(empty) #outputs 38
```
