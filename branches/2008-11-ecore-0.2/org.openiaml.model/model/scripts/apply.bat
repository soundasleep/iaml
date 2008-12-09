@echo off
rem -- apply the diff for %1 model .gmfgen --

cd ..
patch %1.gmfgen diff/%1.gmfgen.diff
cd scripts