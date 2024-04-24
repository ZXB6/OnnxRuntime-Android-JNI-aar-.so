#include <jni.h>
#include <string>
#include "hp.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_headpose_1android_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_headpose_1android_MainActivity_net_1run(JNIEnv *env, jobject thiz) {
    // TODO: implement net_run()

    Mat src_img = cv::imread("/storage/emulated/0/test.jpg");
//cv::cvtColor(src_img, src_img, cv::COLOR_);
    Head_Pose mynet("/storage/emulated/0/directmhp_300wlp_m_finetune_post_768x1280.ort", 0.5);
    vector<BoxInfo> faceboxes = mynet.detect(src_img);
    drawPred(src_img, faceboxes);
    cv::imwrite("/storage/emulated/0/res_9.jpg",src_img);

}