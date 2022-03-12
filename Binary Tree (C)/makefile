CC=g++
CFLAGS=-I.
DEPS = BinaryTree.h Node.h
OBJ = main.o BinaryTree.o Node.o

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -c -o $@ $< 
	
bst: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS)

clean:
	rm -f *.o bst
