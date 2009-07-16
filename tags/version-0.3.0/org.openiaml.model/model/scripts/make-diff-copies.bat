@echo off
rem -- copy all .gmfgens into .gmfgen.copy --
rem -- assumes that cygwin is installed --

cd ..
mkdir copies
cp *.gmfgen copies
cd scripts