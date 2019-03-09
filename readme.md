# SpaceAttack remake in libGDX. 

Original SpaceAttack is top-down space shooter I made during learning basic Java and OOP concepts. I used Processing framework and targeted desktop devices with keyboard & mouse steering. My friends found game nice and playable, with good dynamics and groove, so I'd like to make some further development of this idea and share it with others.

First SpaceAttack version suffers from some major issues: problems with closing Processing applet from Swing menu GUI and merge Swing with Processing itself. Also desktop isn't best target for small and simple games these days.

Now I'm going to remake this with libGDX in TDD methodology. Main target is Android, so steering will be smartphone friendly. At first iteration it will be single player offline game. I'm trying to save as many as possible original gameplay elements, although priority is to make it playable on smartphones. Major change is steering. I hope new varied mechanics of ships & missiles flight will grant as much fun as original game's dynamic mouse steering.

I'm using clean code & architecture principles, especially encapsulating all framework dependencies in one package (spaceattack.ext). Game logic classes has 0 imports from libgdx jars. Game is resolution independant.

Original Processing game: https://github.com/zagorskitomasz/SpaceAttack 
