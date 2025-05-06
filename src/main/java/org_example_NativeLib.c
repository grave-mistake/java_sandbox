#include "org_example_NativeDemo.h"
#include <stdio.h>
#include <jni.h>

JNIEXPORT void JNICALL Java_org_example_NativeDemo_sayHello(JNIEnv *env, jclass clazz) {
    printf("Hello! This is a C function being called inside a Java program!\n");
}