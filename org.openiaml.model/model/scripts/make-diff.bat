@echo off
rem -- create diff's for %1 model .gmfgen --

cd ..
rem mkdir diff
diff -U 3 -w %1.gmfgen copies/%1.gmfgen > diff/%1.gmfgen.diff
svn revert %1.gmfgen
cd scripts
@echo on