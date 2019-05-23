
echo "Building commmon"
cd common
rm -rf CMakeBuild && mkdir CMakeBuild && cd CMakeBuild && cmake .. && make && cd ..
echo "Building ReflectiveDLLInjection"
cd ../ReflectiveDLLInjection
rm -rf CMakeBuild && mkdir CMakeBuild && cd CMakeBuild && cmake .. && make && cd ..
echo "Building metsrv"
cd ../metsrv
rm -rf CMakeBuild && mkdir CMakeBuild && cd CMakeBuild && cmake .. && make && cd ..
echo "Building stdapi"
cd ../ext_server_stdapi
rm -rf CMakeBuild && mkdir CMakeBuild && cd CMakeBuild && cmake .. && make && cd ..
cd ..
echo "Built"

