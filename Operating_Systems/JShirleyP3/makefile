CC=gcc
CFLAGS=-I.
OBJ = writer.o reader.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

compall: $(OBJ)
	$(CC) -o writer writer.c
	$(CC) -o reader reader.c


clean:
	rm -f *.o *~ core reader writer 
	
