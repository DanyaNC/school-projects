DESCRIPTION:
This is my take on the SmallWorld assignment.
My project is the creation of a small dungeon with 3-5 rooms.
These rooms are one of 4 types:

There is a 25% chance of a room being an item room, in which there is a 70% chance of there being an hp potion,
and a 30% chance of there being a sword.
An hp potion heals for 0-50 hp.
A sword increases the hero's attack damage by 0-25 attack damage.

There is a 25% chance of a room being a merchant room, in which there is always one item, 50% chance of hp potion,
50% chance of a sword. They are identical to their item room correspondents.
The hero will always attempt to purchase the item, however they might not
have enough gold. This is also a simple 50% chance.

There is a 50% chance of a room being an enemy room, which has one of three enemies: A dragon (20%), a spider(40%), or a goblin(40%).
Here are the stats for each of them:
Dragon: 
HP: 75
Damage: 30
Spider:
HP:35
Damage: 15
Goblin:
HP: 50
Damage: 20

Finally, there is a boss room.
HP: 100
Damage: 40

The map of the dungeon prints out in the format : 
Map of dungeon:
* * * * * 
* * B * * 
* * | * * 
* * ? * * 
* * | * * 
* * X * * 
* * | * * 
* * X * * 
* * | * * 

at the start of the simulation.

COMPILATION INSTRUCTIONS:
Either compile and run SmallDungeon.java through an IDE or through a console.

EXECUTION INSTRUCTIONS:
None.

WRITTEN DISCUSSION:
Personally, I was not a fan of the SmallWorld assignment as to me it seemed like a lot of gibberish with a significant amount of repeated actions,
so the length was long, but there wasn't much going on. I wanted to do something a little bit creative so I came up with making a small rogue-like-ish
dungeon that gets generated. The fact that there's some randomization to it and a map is something I personally found entertaining.
I based the majority of the interactions between objects by Utility, Thing, Mob, and Item. My utility classes were things that handled the logistics of the 
dungeon. Thing was a parent class that several other classes inherited, providing a name and a description to the class, much in the same vein to the
SmallWorld example. The Mob interface was there for the interaction between the fact that the room would have a random thing in it, (which I would know
is a mob through the stored type of room), this allowed me to tell the enemy to attack, without me knowing what kind of enemy it was (dragon/goblin, etc.)
The Item interface was there for the same reason as the mob interface, I would not know what item spawned in the dungeon, therefore I needed some way
to call for the item's effect without me knowing which object's method to call, therefore both the potion and the sword implement the item interface.
The real challenge was planning it out, and making sure I wasn't making stupid mistakes and ending up in a stack overflow error. It was also quite challenging
keeping up with the interfaces and parents classes as well as the interactions between all of them as I would frequently change on thing that would result
in an error. However, it was a gradual process, and maintaining the interfaces and parent class helped me a lot as they guided my process. I also changed them
sometimes as I kept coding and changing my vision for what the classes that implement them are supposed to do (i.e. changing the return type or adding another
required method).

Note: It is rather difficult for the hero to win! The hero also has a random name out of a set few.
EXAMPLE RUNS (A Victory and a Game Over):
Map of dungeon:
* * * * * 
* * B * * 
* * | * * 
* * X * * 
* * | * * 
* * M * * 
* * | * * 
* * ? * * 
* * | * * 

The dungeon contains: 
1 item rooms. ?
1 enemy rooms. X
1 merchants. M
And one boss room. B

The hero enters the dungeon.
Emily walks into the room and notices an item on the floor.
A small glass bottle containing a strange green liquid. Doesn't look particularly tasty.
Emily drinks the potion and feels healthier. They gained 41 hp.
Emily now has 141 hp.
Emily moves on to the next room.
Emily enters the room and inspects the figure siting down in front of a blanket with wares.
A humble merchant with something to sell.
The merchant has a sword for sale.
Emily purchases the item.
Emily receives a new sword and inspects it
The hero equips the new sword and feels as if they are stronger. They gained 21 attack damage.
The hero moves on to the next room.
Emily goes into the room and is met with an enemy. In front of them stands : A small green creature half your height who glares at the hero with a menacing gaze
Emily begins combat with the enemy.
Emily attacked for 54
The Goblin now has -4 hp.
The enemy has fallen. Emily successfully defeated the Goblin!
Emily moves on to the next room.
Emily goes into the final room and is met with the boss. In front of them stands : Artorias A knight clad in dark armor covered by a purple aura, wielding a sword easily the size of their own body.
Emily begins combat with Artorias.
Emily attacked for 46
The Artorias now has 54 hp.
Artorias swings his blade and lands an attack dealing 40 damage.
Emily now has 101 hp.
Emily attacked for 43
The Artorias now has 11 hp.
Artorias swings his blade and lands an attack dealing 40 damage.
Emily now has 61 hp.
Emily attacked for 55
The Artorias now has -44 hp.
The enemy has fallen. Emily successfully defeated the Artorias!
Emily has beaten the dungeon!

Process finished with exit code 0




Map of dungeon:
* * * * * 
* * B * * 
* * | * * 
* * ? * * 
* * | * * 
* * X * * 
* * | * * 
* * X * * 
* * | * * 

The dungeon contains: 
1 item rooms. ?
2 enemy rooms. X
0 merchants. M
And one boss room. B

The hero enters the dungeon.
Evan goes into the room and is met with an enemy. In front of them stands : A small green creature half your height who glares at the hero with a menacing gaze
Evan begins combat with the enemy.
Evan attacked for 26
The Goblin now has 24 hp.
The goblin stabs the hero with a dagger, dealing 20 damage.
Evan now has 80 hp.
Evan attacked for 34
The Goblin now has -10 hp.
The enemy has fallen. Evan successfully defeated the Goblin!
Evan moves on to the next room.
Evan goes into the room and is met with an enemy. In front of them stands : A menacing black spider, whose height reaches up to hero's knees.
Evan begins combat with the enemy.
Evan attacked for 27
The Spider now has 8 hp.
The spider lunged forwards and attacks for 15 damage.
Evan now has 65 hp.
Evan attacked for 26
The Spider now has -18 hp.
The enemy has fallen. Evan successfully defeated the Spider!
Evan moves on to the next room.
Evan walks into the room and notices an item on the floor.
A small glass bottle containing a strange green liquid. Doesn't look particularly tasty.
Evan drinks the potion and feels healthier. They gained 12 hp.
Evan now has 77 hp.
Evan moves on to the next room.
Evan goes into the final room and is met with the boss. In front of them stands : Artorias A knight clad in dark armor covered by a purple aura, wielding a sword easily the size of their own body.
Evan begins combat with Artorias.
Evan attacked for 24
The Artorias now has 76 hp.
Artorias swings his blade and lands an attack dealing 40 damage.
Evan now has 37 hp.
Evan attacked for 16
The Artorias now has 60 hp.
Artorias swings his blade and lands an attack dealing 40 damage.
Evan now has -3 hp.
Evan has fallen to a Artorias
Game Over.

Process finished with exit code 0



FUTURE WORK:
I would have liked to add randomness to the enemies attacks as well as adding a special attack to the hero and the enemies. While adding randomness to the 
enemies would have been simple by using the same code as the hero's, I couldn't quite figure out an elegant way to achieve this through inheritance and implementation.
I also would have liked to add more variety of rooms and events or perhaps even multiple levels to the dungeon. I also would have liked to create a GUI, or some sort of visual
aspect to the project, but as we did not quite go over that I will learn how to over the summer.
However, I believe I left the project off in an approachable manner to myself in the future if I wished to continue
developing it. The difficulties I do see are in adding extra content that interacts with the already set interfaces, and that they might sometimes need a change (which would 
result in me having to change the rest of my classes that implement them). However, I believe that is just part of the trouble in expanding one's vision to the project.
There are definitely other ways I could have refactored the code, as it is still quite messy.
