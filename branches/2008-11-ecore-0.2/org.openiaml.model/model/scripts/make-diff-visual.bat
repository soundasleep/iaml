@echo off
rem -- create diff's for visual model .gmfgen --

cd ..

mkdir diff

diff -u visual.gmfgen copies/visual.gmfgen > diff/visual.gmfgen.diff