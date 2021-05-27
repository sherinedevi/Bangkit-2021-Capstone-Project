# ML Model
<h3> This directory contains all about our models exploration. </h3>
<br> We had trained for about 6 models. The training process uses train and validation set, while for the testing process, we apply them on the test set taken from the CNRPark dataset. Here are the results: <br>
  1. Inception with 97% accuracy on val set <br>
  2. EfficientNetB3 with 98% accuracy on val set <br>
  3. EfficientNetB4 with 98.8% accuracy on val set <br>
  4. Mobile Net with 95% accuracy on val set <br>
  5. VGG16 with 98.4% accuracy on val set <br>
  6. Xception with 97.5% accuracy on val set <br>
  7. CNN with 96.9% accuracy on val set <br>

## Saved Model <br>
Due to GitHub file size limitation, some of the files won't be pushed here, instead we will give the Google Drive link access. <br>
Saved Model can be accessed through this link: https://drive.google.com/drive/folders/1chNc4iyEDLxls5hnLXQEJA4KquQFc6vi?usp=sharing. All models are in .h5 format. </br>

## Result on Real Implementation <br>
Moreover, we applied all our models to the real implementation using Indonesian CCTV footage on a police station's parking lot. The result (can be found [here](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/tree/main/Machine%20Learning/Models/Result))

## Further Result
For further analysis and results, we apply two main models to our local deployment data with different conditions (morning, afternoon, evening) to take into account the accuracy. 
1. The first model is CNN, which stands as our baseline model. The results (can be found [here](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/tree/main/Machine%20Learning/Models/CNN%20Results)), 
2. The last model in VGG16, which stands as our proposed and improved model. The results (can be found [here](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/tree/main/Machine%20Learning/Models/VGG16%20Results))
