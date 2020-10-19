#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h> 
#include <string.h> 
#include "asm.h"   
}

//START: testGroup
TEST_GROUP(smallest)
{
    int  expected;
    void setup()
    {
        expected = 0; 
    }
    void teardown()
    {
    }
    void expect(int num )
    {
        expected = num;
    }
    void given(int x ,int y ,int z)
    { 
	int result; 
	result=smallest(x,y,z); 
        LONGS_EQUAL(expected, result);
    }
};
//END: testGroup

TEST(smallest, Zero)
{
    expect(0);
    given(0,0,0);
}

TEST(smallest, One)
{
    expect(-1);
    given(0,1,-1);
}


TEST(smallest, MinusOne)
{
    expect(-3);
    given(-1,-2,-3);
}

TEST(smallest, MinusOneB)
{
    expect(-3);
    given(-3,-2,-1);
}
TEST(smallest,Two)
{
    expect(0);
    given(0,2,1);
}

TEST(smallest, Three)
{
    expect(2);
    given(2,3,2);
}



TEST(smallest, Five)
{
    expect(-200);
    given(-200,3,5);
}
