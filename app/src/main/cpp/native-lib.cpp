#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_apiauthentication_App_APIKey(
        JNIEnv* env,
        jobject /* this */) {
    std::string apiKey = "AIzaSyA_GN7ua7SqHZ2Uh-ssujeS49WoFTMv3yk";
    return env->NewStringUTF(apiKey.c_str());
}