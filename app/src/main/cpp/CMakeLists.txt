cmake_minimum_required(VERSION 3.10.1)
project("headpose_android")

set(ANDROID_ABI "arm64-v8a")

#include  包含opencv和onnxruntime的头文件
set(Include_DIR "E:/aabb/Include")
include_directories(${Include_DIR})

# OpenCV
set(OpenCV_DIR "E:/aabb/opencv-4.7.0-android-sdk/OpenCV-android-sdk/sdk/native/jni")
find_package(OpenCV REQUIRED)

add_library(libonnxruntime STATIC IMPORTED)
set_target_properties(libonnxruntime PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../../../libs/${ANDROID_ABI}/libonnxruntime.so)


set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fopenmp")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fopenmp")
if(DEFINED ANDROID_NDK_MAJOR AND ${ANDROID_NDK_MAJOR} GREATER 20)
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -static-openmp")
endif()



add_library( # Sets the name of the library.
        headpose_android
        SHARED
        hp.cpp
        native-lib.cpp
)


find_library( # Sets the name of the path variable.
        log-lib
        log)

target_link_libraries( # Specifies the target library.
        headpose_android
        ${log-lib}
        ${OpenCV_LIBS}
        libonnxruntime
)