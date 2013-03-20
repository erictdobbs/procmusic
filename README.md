ProcMusic
=========

ProcMusic is a procedural music generator. 

Setup and Requirements
----------------------

This program requires Java (free download at http://www.java.com). Once Java is installed, you can launch this program with the command 

    java -jar ProcMusic.jar

once you navigate to the directory that you've downloaded the file to.

How To Use
----------

Once the program starts, hit the button marked "Play" to get four bars of randomly generated music. If you check the box marked "Loop," the program will generate another four bars as soon as the previous phrase finishes playing. The slider at the top allows you to set the speed for the next phrase.

The window at the bottom shows you the chord progression and rhythms that have been created for the current phrase. 

Planned Features
----------------

I'll be working on this project on and off for a while, and I have a fairly good idea of the direction I'll be taking it.

<dl>
  <dt>Randomly generated harmony lines</dt>
  <dd>Bass and counter-melodies should be generated alongside the main melody, playing off of the generated motifs and rhythms.</dd>
  <dt>Improved rhythm generation</dt>
  <dd>The current method essentially pulls a pre-written set of notes from a large sample pool, then scales it to fit the current chord. Ideally, the program will eventually be able to generate these lines on its own, allowing for a much broader set of possibilites.</dd>
  <dt>Broader song planning</dt>
  <dd>ProcMusic works on a phrase-by-phrase basis, generating a few bars of music at a time. I plan to add the ability for generated phrases to affect how later parts of the song are composed, adding repeating motifs and better overall structure to songs.</dd>
  <dt>More control</dt>
  <dd>One of the main goals of ProcMusic is to be able to have a fully composed song on demand based on a small set of parameters. Very-long-term, the software could be built into a simple game engine, taking small bits of information such as player health, number of nearby enemies, type of terrain, time of day, and so on. Short-term, that means adding sliders for sparseness of the melody, amount of syncopation in the rhythms, and changes to the underlying bass line.</dd>
</dl>