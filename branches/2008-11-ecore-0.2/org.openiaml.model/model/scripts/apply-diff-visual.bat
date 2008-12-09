@echo off
rem -- apply the diff for visual model .gmfgen --

cd ..
patch visual.gmfgen diff/visual.gmfgen.diff
