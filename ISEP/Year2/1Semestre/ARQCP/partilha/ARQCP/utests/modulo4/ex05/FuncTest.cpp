#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h> 
#include <string.h> 
#include "asm.h"   
}

//START: testGroup
TEST_GROUP(dec_square)
{
    int  expected;  
    int  expected_dec; 

    void setup()
    {
        expected =0;  
	expected_dec=0; 
    }
    void teardown()
    {
    }
    void expect(int num ,int num2)
    {
        expected = num; 
	expected_dec=num2; 
    }
    void given(int x ,int y )
    { 
	int result; 
	int num_to_dec=y; 
	result=dec_square(&num_to_dec,x); 
        LONGS_EQUAL(expected, result);
	LONGS_EQUAL(expected_dec,num_to_dec);  
    }
};
//END: testGroup

TEST(dec_square, Zero)
{
    expect(0,0);	// square , decremented number 
    given(0,1);		// base, number to decrement 
}

TEST(dec_square, One)
{
    expect(1,1);
    given(1,2);
}


TEST(dec_square, MinusOne)
{
    expect(1,-2);
    given(-1,-1);
}

TEST(dec_square, MinusOneB)
{
    expect(1,-3);
    given(-1,-2);
}
TEST(dec_square,Two)
{
    expect(4,-1);
    given(2,0);
}

TEST(dec_square, Three)
{
    expect(9,3);
    given(3,4);
}



TEST(dec_square, Five)
{
    expect(25,4);
    given(5,5);
}
