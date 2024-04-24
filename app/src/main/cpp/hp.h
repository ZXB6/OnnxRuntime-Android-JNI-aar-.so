#ifndef HEADPOSE_ANDROID_HP_H
#define HEADPOSE_ANDROID_HP_H

#include <iostream>
#include <string>
#include <iomanip>
#include <vector>
#include <fstream>
#include <string>
#include <math.h>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
//#include <cuda_provider_factory.h>  ///nvidia-cuda����
#include <onnxruntime_cxx_api.h>

#define PAI 3.141592653589793

using namespace cv;
using namespace std;
using namespace Ort;

typedef struct BoxInfo
{
    float xmin;
    float ymin;
    float xmax;
    float ymax;
    float score;
    float pitch;
    float yaw;
    float roll;
} BoxInfo;

void drawPred(Mat frame, vector<BoxInfo> faceboxes);

class Head_Pose
{
public:
    Head_Pose(string modelpath, float confThreshold);
    vector<BoxInfo> detect(Mat srcimg);
    void run();
private:
    vector<float> input_image_;
    int inpWidth;
    int inpHeight;
    float confThreshold;

    Env env = Env(ORT_LOGGING_LEVEL_ERROR, "Head Pose Estimation");
    Ort::Session *ort_session = nullptr;
    SessionOptions sessionOptions = SessionOptions();
    vector<char*> input_names;
    vector<char*> output_names;
    vector<vector<int64_t>> input_node_dims; // >=1 outputs
    vector<vector<int64_t>> output_node_dims; // >=1 outputs
};





#endif //HEADPOSE_ANDROID_HP_H
