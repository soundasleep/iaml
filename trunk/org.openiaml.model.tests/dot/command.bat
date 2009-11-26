@rem Use one of: circo dot fdp neato nop nop1 nop2 osage patchwork sfdp twopi
rem dot all-dependencies.dot.txt -O -Kneato -Tpng && start all-dependencies.dot.txt.png
rem dot local-dependencies.dot.txt -O -Kdot -Tpng && start local-dependencies.dot.txt.png
dot local-dependencies-notests.dot.txt -O -Ksfdp -Tpng && start local-dependencies-notests.dot.txt.png