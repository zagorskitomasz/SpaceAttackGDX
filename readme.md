# SpaceAttack remake in libGDX. 

Original game made in Processing: https://github.com/zagorskitomasz/SpaceAttack 

I'm going to remake this with libGDX in TDD methodology. Main target is Android, so steering will be smartphone friendly. At first iteration it will be single player offline game. Since it's impossible to transfer original mouse+keyboard game dynamics into touch screen, I'm gooing to make gampley more focused on strategy and mind battle vs AI than on fast action.

I'm using clean code & architecture principles, especially encapsulating all framework dependencies in one package (spaceattack.ext). Game logic classes has 0 imports from libgdx jars.

During early development I'm using desktop launcher, so it will require more or less painfull migration to Android when desktop alpha is ready.

# TODO:

-> power ups (hp, battery, special weapons);

-> special weapons implementations;

-> other ships implementations;

-> boss ship;

-> improving AI module;

-> more stages;
