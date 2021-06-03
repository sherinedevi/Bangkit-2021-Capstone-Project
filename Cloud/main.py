from flask import Flask, jsonify, send_file
from flask_restful import Api, Resource
from datetime import datetime

import cv2
from tensorflow.keras import models
import pickle
import numpy as np

from google.cloud import storage

def get_frame(video_path, hour=0, minute=0, second=0):
    video=cv2.VideoCapture(video_path)

    total_frame=video.get(cv2.CAP_PROP_FRAME_COUNT)
    framerate=video.get(cv2.CAP_PROP_FPS)

    hour=abs(hour)
    minute=abs(minute)
    second=abs(second)

    intented_frame= int((hour*3600+minute*60+second)*framerate)

    if intented_frame==0:
        intented_frame=1
    if intented_frame>total_frame:
      return 0

    video.set(1, intented_frame)
    ret, frame = video.read()
    return frame

def load_model(model_path):
  model = models.load_model(model_path)
  return model

def load_box(box_path):
  file = open(box_path, 'rb')
  coordinates = pickle.load(file)
  file.close()
  return coordinates

def predict(frame, model, coordinates):
  frame_bersih = frame.copy()
  empty = 0

  for i in range(len(coordinates)):
    points = coordinates[i]
    x,y,w,h = cv2.boundingRect(points)
    crop = frame[y:y+h, x+1:x+w]

    test = cv2.resize(crop, (300,300))
    test = np.expand_dims(test, axis=0)
    pred = model.predict(test)

    if (pred < 0.9):
      empty += 1
      points = coordinates[i].reshape((-1, 1, 2))
      frame_bersih = cv2.polylines(frame_bersih, [points], True, color=(0,255,0), thickness=2)
  
  height = frame_bersih.shape[1]
  frame_bersih = frame_bersih[50:y+height, :]
  return empty, frame_bersih

client = storage.Client()

# bucket_name = SET BUCKET NAME HERE USING CLOUD CLIENT
bucket = client.bucket(bucket_name)

# model_blob = SET MODEL BLOB HERE USING CLOUD CLIENT
model = load_model(model_blob)

# coordinates_blob = SET COORDINATES BLOB HERE USING CLOUD CLIENT
coordinates = load_box(coordinates_blob)


app = Flask(__name__)
api = Api(app)

#IN-MEMORY DATASTORE FOR MVP PURPOSES, USE RELATIONAL DATABASE FOR MORE SCALABLE APPLICATION
locations = {
    "polrestabdl": {
        "name": "Polresta Bandar Lampung",
        "street": "Jalan Mayjen MT Haryono",
        "address": "Jl. Mayjen MT Haryono, Gotong Royong, Kec. Tj. Karang Pusat, Kota Bandar Lampung, Lampung 35119",
        "description": "Kantor Polresta Provinsi Bandar Lampung melayani laporan & pengaduan masyarakat. Buka 07.00-14.30.",
        "available": 0,
        "capacity": 53,
        "image_parkinglot": "",
        "image_thumbnail": "",
        "url": "https://virtual-ego-312809.et.r.appspot.com/location/polrestabdl"
    },

    "maliowilut": {
        "name": "Malioboro Wilayah Utara",
        "street": "Jalan Malioboro",
        "address": "Jl. Malioboro, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55271",
        "description": "Wisata Yogyakarta tujuan utama turis dari dalam dan luar negeri. Buka 24 jam.",
        "available": 0,
        "capacity": 250,
        "image_parkinglot": "",
        "image_thumbnail": "",
        "url": "https://virtual-ego-312809.et.r.appspot.com/location/maliowilut"
    },

    "stasiunyog": {
        "name": "Stasiun Yogyakarta",
        "street": "Jalan Margo Utomo",
        "address": "Jl. Margo Utomo, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55271",
        "description": "Stasiun Kereta Api yang menjadi pusat transportasi di Yogyakarta. Buka 24 jam.",
        "available": 0,
        "capacity": 80,
        "image_parkinglot": "",
        "image_thumbnail": "",
        "url": "https://virtual-ego-312809.et.r.appspot.com/location/stasiunyog"
    },
}


# Locations handler lists all available locations on the datastore/database
class Locations(Resource):
    def get(self):
        places = []

        for location in locations:
            info = locations[location]
            places.append({"name": info.get("name"), "street": info.get("street"), "thumbnail_base64": info.get("image_thumbnail"), "detail_url": info.get("url")})
        
        response = {"status": "success", "data": places}
        return jsonify(response)

# Place Information handler list all the details on specified location.
# available and image_parkinglot, the two fundamental practical use case of this application got their results from implemented Machine Learning model.
# keep in mind both Malioboro and Stasiun Yogya use the same Video blob because the CCTV livestream in Indonesia are very limited thus we use the same one for MVP.
# as more and more location streamed their parking lot, we can develop more business logic in the PlaceInformation handler.

class PlaceInformation(Resource):
    def get(self, name):
        
        # video_blob = PUT YOUR VIDEO / IMAGE STREAM BLOB HERE USING CLOUD CLIENT
        frame = get_frame(video_blob)
        
        place = locations[name]
        place["available"], place["image_parkinglot"] = predict(frame, model, coordinates)

        response = {"status": "success", "data": place}
        return jsonify(response)

api.add_resource(Locations, "/locations")      
api.add_resource(PlaceInformation, "/location/<string:name>")

if __name__ == "__main__" :
    app.run(debug = True)
