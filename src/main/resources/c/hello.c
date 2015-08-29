#include <stdio.h>
#include <stdlib.h>

void hello() {
	printf("Hello, World!\n");
}

char *allocate(int size) {
	return malloc(size);
}

void set(char *buffer, int index, char value) {
	buffer[index] = value;
}

int get(char *buffer, int index) {
	return buffer[index];
}
