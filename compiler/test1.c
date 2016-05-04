int a;

int foo(void) {
	return 1;
}

int main(void) {
	int b;
	a = 5;
	b = a + 1;
	a = b - a / a * 3 + 4;
	b = foo();
	
	if (a == 0) {
		a = 1;
	} else {
		b = 5;
	}
	
	while (b > 1) {		
		b = b - 1;
	}
	
	b = 10;
	
	return 0;
}