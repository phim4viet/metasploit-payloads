cmake_minimum_required(VERSION 3.0.0 FATAL_ERROR)

################### Variables. ####################
# Change if you want modify path or other values. #
###################################################

set(PROJECT_NAME jpeg)

# Output Variables
set(OUTPUT_DEBUG ../../../output/Debug/)
set(OUTPUT_RELEASE ../../../output/Release/)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${OUTPUT_RELEASE})

############## CMake Project ################
#        The main options of project        #
#############################################

# Folders files
set(CPP_DIR_1 ../../source/jpeg-8)
set(HEADER_DIR_1 ../../source/jpeg-8)


############## CMake Project ################
#        The main options of project        #
#############################################

project(${PROJECT_NAME} C)

# Definition of Macros
add_definitions(
   -DNDEBUG 
   -D_WINDOWS 
   -D_LIB 
   -DUSE_DLL 
   -DMETERPRETER_EXPORTS 
   -D_CRT_SECURE_NO_WARNINGS
   -D__GNUC__
)


############## Artefacts Output #################
# Defines outputs , depending Debug or Release. #
#################################################

if(CMAKE_BUILD_TYPE STREQUAL "Debug")
  set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/${OUTPUT_DEBUG}")
  set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/${OUTPUT_DEBUG}")
  set(CMAKE_EXECUTABLE_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/${OUTPUT_DEBUG}")
else()
  set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${OUTPUT_RELEASE}")
  set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY "${OUTPUT_RELEASE}")
  set(CMAKE_EXECUTABLE_OUTPUT_DIRECTORY "${OUTPUT_RELEASE}")
endif()

# Define Release by default.
if(NOT CMAKE_BUILD_TYPE)
  set(CMAKE_BUILD_TYPE "Release")
  message(STATUS "Build type not specified: Use Release by default.")
endif(NOT CMAKE_BUILD_TYPE)

################# Flags ################
# Defines Flags for Windows and Linux. #
########################################
SET(CMAKE_SYSTEM_NAME Windows)
include(CMakeForceCompiler)
IF("${GNU_HOST}" STREQUAL "")
    SET(GNU_HOST x86_64-w64-mingw32)
ENDIF()
# Prefix detection only works with compiler id "GNU"
CMAKE_FORCE_C_COMPILER(${GNU_HOST}-gcc GNU)
# CMake doesn't automatically look for prefixed 'windres', do it manually:
SET(CMAKE_RC_COMPILER ${GNU_HOST}-windres)

set( _MSC_VER 1910 )
SET(CMAKE_C_COMPILER clang)

if(MSVC)
   set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} /W3 /EHsc")
   set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} /W3 /EHsc")
endif(MSVC)
if(NOT MSVC)
   set(CMAKE_LIBRARY_ARCHITECTURE x64 CACHE STRING "" FORCE)
   set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -target x86_64-w64-windows-gnu -Wfatal-errors -fmsc-version=${_MSC_VER} -fms-extensions -fms-compatibility -fdelayed-template-parsing ")
   set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -target x86_64-w64-windows-gnu")
endif(NOT MSVC)


################ Files ################
#   --   Add files to project.   --   #
#######################################

file(GLOB SRC_FILES
	${CPP_DIR_1}/jaricom.c
	${CPP_DIR_1}/jcapimin.c
	${CPP_DIR_1}/jcapistd.c
	${CPP_DIR_1}/jcarith.c
	${CPP_DIR_1}/jccoefct.c
	${CPP_DIR_1}/jccolor.c
	${CPP_DIR_1}/jcdctmgr.c
	${CPP_DIR_1}/jchuff.c 
	${CPP_DIR_1}/jcinit.c 
	${CPP_DIR_1}/jcmainct.c
	${CPP_DIR_1}/jcmarker.c
	${CPP_DIR_1}/jcmaster.c
	${CPP_DIR_1}/jcomapi.c
	${CPP_DIR_1}/jcparam.c
	${CPP_DIR_1}/jcprepct.c
	${CPP_DIR_1}/jcsample.c
	${CPP_DIR_1}/jctrans.c
	${CPP_DIR_1}/jdapimin.c
	${CPP_DIR_1}/jdapistd.c
	${CPP_DIR_1}/jdarith.c
	${CPP_DIR_1}/jdatadst.c
	${CPP_DIR_1}/jdatasrc.c
	${CPP_DIR_1}/jdcoefct.c
	${CPP_DIR_1}/jdcolor.c
	${CPP_DIR_1}/jddctmgr.c
	${CPP_DIR_1}/jdhuff.c
	${CPP_DIR_1}/jdinput.c
	${CPP_DIR_1}/jdmainct.c
	${CPP_DIR_1}/jdmarker.c
	${CPP_DIR_1}/jdmaster.c
	${CPP_DIR_1}/jdmerge.c
	${CPP_DIR_1}/jdpostct.c
	${CPP_DIR_1}/jdsample.c
	${CPP_DIR_1}/jdtrans.c
	${CPP_DIR_1}/jerror.c
	${CPP_DIR_1}/jfdctflt.c
	${CPP_DIR_1}/jfdctfst.c
	${CPP_DIR_1}/jfdctint.c
	${CPP_DIR_1}/jidctflt.c
	${CPP_DIR_1}/jidctfst.c
	${CPP_DIR_1}/jidctint.c
	${CPP_DIR_1}/jmemmgr.c
	${CPP_DIR_1}/jmemnobs.c
	${CPP_DIR_1}/jquant1.c
	${CPP_DIR_1}/jquant2.c
	${CPP_DIR_1}/jutils.c

	${HEADER_DIR_1}/jconfig.h
	${HEADER_DIR_1}/jdct.h
	${HEADER_DIR_1}/jerror.h
	${HEADER_DIR_1}/jinclude.h
	${HEADER_DIR_1}/jmemsys.h
	${HEADER_DIR_1}/jmorecfg.h
	${HEADER_DIR_1}/jpegint.h
	${HEADER_DIR_1}/jpeglib.h
	${HEADER_DIR_1}/jversion.h
)

include_directories( ${HEADER_DIR_1} )

# Add library to build.
add_library(${PROJECT_NAME} STATIC
   ${SRC_FILES}
)

