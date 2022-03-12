CC=gcc
CFLAGS=-I.
DEPS = bubbleSort.h 
OBJ = main.o bubbleSort.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)
	
bubsort: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS)

clean:
	rm -f *.o bubsort
