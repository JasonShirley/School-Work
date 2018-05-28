CC=g++
CFLAGS=-I.
DEPS = Enemy.h GameBoard.h Goblin.h Troll.h Orc.h Enemy.h
OBJ = main.o Enemy.o GameBoard.o Goblin.o Troll.o Orc.o Enemy.o

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -c -o $@ $< 
	
main: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS)

clean:
	rm -f *.o main
