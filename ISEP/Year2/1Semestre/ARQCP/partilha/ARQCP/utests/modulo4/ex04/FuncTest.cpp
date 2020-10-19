#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h> 
#include <string.h> 
#include "asm.h"   
}

//START: testGroup
TEST_GROUP(sub_greater)
{
    int  expected;  
    int  expected_s; 

    void setup()
    {
        expected = 0;  
	expected_s=0; 
    }
    void teardown()
    {
    }
    void expect(int num ,int smaller)
    {
        expected = num; 
	expected_s=smaller; 
    }
    void given(int x ,int y )
    { 
	int result; 
	int minor; 
	result=sub_greater(x,y,&minor); 
        LONGS_EQUAL(expected, result);
	LONGS_EQUAL(expected_s,minor) 
    }
};
//END: testGroup

TEST(sub_greater, Zero)
{
    expect(0,0);
    given(0,0);
}

TEST(sub_greater, One)
{
    expect(-1,0);
    given(0,1);
}


TEST(sub_greater, MinusOne)
{
    expect(-1,-2);
    given(-2,-1);
}

TEST(sub_greater, MinusOneB)
{
    expect(-1,-3);
    given(-3,-2);
}
TEST(sub_greater,Two)
{
    expect(2,2);
    given(4,2);
}

TEST(sub_greater, Three)
{
    expect(3,3);
    given(6,3);
}



TEST(sub_greater, Five)
{
    expect(100,5);
    given(105,5);
}
