@echo off
rem -- create diff's for %1 model .gmfgen --

cd ..
mkdir diff
diff -u -w %1.gmfgen copies/%1.gmfgen > diff/%1.gmfgen.diff
cd scripts