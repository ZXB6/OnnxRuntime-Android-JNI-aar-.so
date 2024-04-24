# OnnxRuntime-Android-JNI-aar-.so
onnxruntime-android JNI开发 build动态库 aar包

上述为onnxruntime 1.16.3  头部姿态pitch row yaw检测
包下载见RELEASE部分

COMMAND:

./build.sh --android --android_sdk_path /home/user/Android/Sdk --android_ndk_path /home/user/Android/Sdk/ndk/21.4.XXXX --android_abi arm64-v8a --android_api 28 --use_nnapi --parallel --build_shared_lib --config MinSizeRel

参考：https://onnxruntime.ai/docs/build/android.html

DETAILS:

–android表示build android版
android_sdk_path： 你的Sdk文件夹
android_ndk_path：Sdk下面的ndk文件夹(到版本文件夹下)，但是具体要看你的项目用的是哪个版本
android_api：看你的项目是哪个版本，具体在哪里可以查看参考官方build for android文档
parallel: 并行编译
build_shared_lib: 使用就是编译动态库，不使用就是静态库
config: 有4种选项[“Debug”, “MinSizeRel”, “Release”, “RelWithDebInfo”]，参考 https://zhuanlan.zhihu.com/p/411887386
--build_shared_lib 有就是动态.so 无就是静态.a
build出来的.so文件会在onnxruntime/build/Android/MinSizeRel里，
把它copy到项目放动态库的文件夹里，
修改相关设置，CMakeLists.txt.



EXAMPLES:
https://github.com/Rohithkvsp/OnnxRuntimeAndorid

ATTENTION:

JNI开发中 c++部分使用ONNX模型得转成ORT模型，应使用对应Python版本的onnxruntime：
 使用命令行：
   python -m onnxruntime.tools.convert_onnx_model_to_ort   yolov8n.onnx 
