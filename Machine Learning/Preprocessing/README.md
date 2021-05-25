## Dataset
[CNRPARK](http://cnrpark.it/): A Dataset for Visual Occupancy Detection of Parking Lots <br>

This project use the [CNR-EXT-Patches-150x150.zip](http://cnrpark.it/dataset/CNR-EXT-Patches-150x150.zip) version (449.5 MB).

### Split
The dataset is split to train and validation with splits defined [here](http://cnrpark.it/dataset/splits.zip), we used:
 - CNRPark-EXT/train.txt
 - CNRPark-EXT/val.txt

Splitting process can be seen in [this IPython notebook](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/blob/main/Machine%20Learning/Preprocessing/split_dataset.ipynb). Final split dataset result can be accessed [here](https://drive.google.com/file/d/18qP1RZCtZ_IyLNs1LFfv0CaQP6QNXzMr/view?usp=sharing).

## Annotate test data
For test data and local deployment, we took several videos from [this](https://rtsp.me/embed/y4z3haiE/) real-time CCTV footage.  <br>
To get the coordinates of the parking slots, we annotate the parking layout with [this program](https://gist.github.com/sainimohit23/8b41569d04bbc6dbafaf83c51ed76740#file-set_regions-py). <br>
The final coordinates are stored in [this pickle file](https://github.com/sherinedevi/Bangkit-2021-Capstone-Project/blob/main/Machine%20Learning/Preprocessing/points_polresta_fixed.pkl). 
