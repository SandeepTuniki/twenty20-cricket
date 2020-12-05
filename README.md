# Twenty 20 Cricket

A console based twenty20 cricket game, written in Java.

## Dependencies

* Java

**Note**: This program was written back in 2014 in Java 7. It hasn't been updated since then. So compatibility with present versions isn't guaranteed.


## Instructions to run

* Clone the repository.

* Compile all the `.java` files in the folder.

* Run the program. Pass the team info in a file as an argument.

  * `java GamePlay team_info.txt`

  * A sample file is provided for reference. Look at `sample_input.txt`

* The program will run a Twenty 20 cricket game with the teams and generate the final score card to `score.txt`


## Rules of the game

1. You should read from a file with player names on it in the format below.


```
Team1Name(Player1, Player2, Player3,  … Player11)

Team2Name(Player1, Player2, Player3,  … Player11)
```

2. A toss has to be made as to decide who has to bat first.

3. Now for each ball bowled, generate a random number from -1 to 6.

```
-1 : Batsman is Out

5 : Batsman is Out and the batsman have crossed. Ie, the Non Striker takes
strike.

0, 1, 2, 3, 4, 6 – Standard cricket rules apply
```

4. Maintain a score card for Batting ONLY. Consider cases like End of an Over
where in the non-Striker takes Strike.

5. After the End of the Innings, calculate Score and the opposition starts
batting. The above rules apply again.

6. Decide a winner at the end of the Match and the Highest Scorer is the Man
of the Match.

7. The output has to be written to a file in the Following Format

```
Team1

Player1, 0,1,2,3,4,6,2,1,OUT,19

Player2,OUT,0

.
.
.
.

Total,100

Team2

Player1, 0,1,2,3,4,6,2,1,OUT,19

Player2,OUT,0

.
.
.
.

Total,101

Team2 WINS

MOM,Team2,Player1
```
