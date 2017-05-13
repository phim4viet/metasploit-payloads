//
//  main.m
//

#import <UIKit/UIKit.h>
#import "AppDelegate.h"

#import <AudioToolbox/AudioServices.h>

#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include <log.h>
#include <pthread.h>
#include "mettle.h"

void * start_mettle_thread(void * args) {
    
    fprintf(stderr, "entering\n");
    
    /*
     * Disable SIGPIPE process aborts.
     */
    //signal(SIGPIPE, SIG_IGN);
    
    /*
     * Allocate the main dispatcher
     */
    struct mettle *m = mettle();
    if (m != NULL) {
        log_set_level(2);
        log_init_file(stderr);
        log_init_flush_thread();
        mettle_add_transport_uri(m, "tcp://192.168.1.2:4444");
        mettle_start(m);
        mettle_free(m);
    } else {
        log_error("could not initialize");
    }
    return 0;
}

int main(int argc, char * argv[]) {
    
    //AudioServicesPlaySystemSound(kSystemSoundID_Vibrate);
    
    pthread_t pth1;
    pthread_create(&pth1, NULL, start_mettle_thread, NULL);
    
    //@autoreleasepool {
      //  start_mettle_thread(0);
    //}
   
    @autoreleasepool {
        return UIApplicationMain(argc, argv, nil, NSStringFromClass([AppDelegate class]));
    }
    return 0;
}
